package com.epam.assessment.knowledge.logic.strategies;

import com.epam.assessment.knowledge.logic.sort.WeightSort;
import com.epam.assessment.knowledge.model.KnowledgeElement;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class AdaptiveStrategy implements QuestionSelectionStrategy {
    private final Random random;

    public AdaptiveStrategy(Random random) {
        this.random = random;
    }

    @Override
    public KnowledgeElement selectQuestion(List<KnowledgeElement> elements) {
        if (elements.size() < 5) {
            return elements.get(random.nextInt(elements.size()));
        }

        elements.sort(Comparator.comparingInt(WeightSort::weightSort));
        return elements.get(random.nextInt(5));
    }
}
