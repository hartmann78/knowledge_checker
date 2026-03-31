package com.epam.assessment.knowledge.logic.strategies;

import com.epam.assessment.knowledge.model.KnowledgeElement;

import java.util.List;
import java.util.Random;

public class RandomStrategy implements QuestionSelectionStrategy {
    private final Random random;

    public RandomStrategy(Random random) {
        this.random = random;
    }

    @Override
    public KnowledgeElement selectQuestion(List<KnowledgeElement> elements) {
        if (elements.isEmpty()) {
            throw new IllegalArgumentException("List is empty!");
        }

        return elements.get(random.nextInt(elements.size()));
    }
}
