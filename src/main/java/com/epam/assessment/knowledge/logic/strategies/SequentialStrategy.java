package com.epam.assessment.knowledge.logic.strategies;

import com.epam.assessment.knowledge.model.KnowledgeElement;

import java.util.List;

public class SequentialStrategy implements QuestionSelectionStrategy {
    private int questionIndex;

    public SequentialStrategy() {
        this.questionIndex = 0;
    }

    @Override
    public KnowledgeElement selectQuestion(List<KnowledgeElement> elements) {
        if (questionIndex >= elements.size()) {
            questionIndex = 0;
        }

        if (questionIndex + 1 > elements.size()) {
            throw new IllegalArgumentException("Illegal argument");
        }

        KnowledgeElement element = elements.get(questionIndex);
        incrementIndex();

        return element;
    }

    private void incrementIndex() {
        questionIndex++;
    }
}
