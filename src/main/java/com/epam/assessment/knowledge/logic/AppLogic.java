package com.epam.assessment.knowledge.logic;

import com.epam.assessment.knowledge.logic.strategies.QuestionSelectionStrategy;
import com.epam.assessment.knowledge.model.KnowledgeBank;
import com.epam.assessment.knowledge.model.KnowledgeElement;
import com.epam.assessment.knowledge.persistence.JsonPersistence;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Component
public class AppLogic {
    private final QuestionSelectionStrategy strategy;
    private final JsonPersistence jsonPersistence;
    private final KnowledgeBank knowledgeBank;

    public AppLogic(JsonPersistence jsonPersistence, QuestionSelectionStrategy strategy) {
        this.jsonPersistence = jsonPersistence;
        this.knowledgeBank = jsonPersistence.load();
        this.strategy = strategy;
    }

    public void greetings() {
        System.out.println("Welcome to the Knowledge Bank Application!");
        System.out.println("This application will test your knowledge on the following topics: " +
                "Geography, History, Culture, Science, Food & Cooking, Art, Nature");
        System.out.println("Total questions in the knowledge bank: " + knowledgeBank.getElements().size());
        System.out.println("You will be asked questions from various categories.");
        System.out.println("If you want to exit, type '/exit' as your answer.\n");
    }

    public void askQuestion() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            KnowledgeElement question = strategy.selectQuestion(knowledgeBank.getElements());

            System.out.println("Question: " + question.getQuestion());
            updateQuestionLastAsked(question);

            System.out.println("Your answer: ");
            String answer = scanner.nextLine();

            if (answer.equals("/exit")) {
                break;
            }

            if (answer.equals(question.getAnswer())) {
                System.out.println("Correct! Well done.");
                updateQuestionHistory(question, true);
            } else {
                System.out.println("Incorrect. The correct answer is: " + question.getAnswer());
                updateQuestionHistory(question, false);
            }
        }
    }

    public void updateQuestionLastAsked(KnowledgeElement question) {
        question.setLastAsked(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        knowledgeBank.getElements().set(question.getId() - 1, question);
    }

    public void updateQuestionHistory(KnowledgeElement question, boolean check) {
        if (question.getHistory() == null) {
            question.setHistory(new ArrayList<>());
        }

        question.getHistory().add(check);
        knowledgeBank.getElements().set(question.getId() - 1, question);
    }

    public void saveData() {
        jsonPersistence.save(knowledgeBank);
    }

    public void showPerformance() {
        List<KnowledgeElement> performance = knowledgeBank.getElements();

        List<String> topics = performance.stream()
                .map(KnowledgeElement::getTopic)
                .distinct()
                .toList();

        System.out.println("Your performance:");

        for (String topic : topics) {
            List<Boolean> history = new ArrayList<>();

            for (KnowledgeElement element : performance) {
                if (element.getTopic().equals(topic) && element.getHistory() != null) {
                    history.addAll(element.getHistory());
                }
            }

            long asked = history.size();
            long correct = history.stream().filter(x -> x == true).count();
            long percentage = Math.round((float) 100 * correct / asked);

            System.out.printf("%s - Asked: %d, Correct - %d, Percentage: %d%%%n",
                    topic, asked, correct, percentage);
        }
    }
}

