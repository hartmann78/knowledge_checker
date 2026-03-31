package com.epam.assessment.knowledge.logic.strategies;

import com.epam.assessment.knowledge.model.KnowledgeElement;

import java.util.List;

public class FixedStrategy implements QuestionSelectionStrategy {
    private final int questionIndex;

    public FixedStrategy(int questionIndex) {
        this.questionIndex = questionIndex;
    }

    @Override
    public KnowledgeElement selectQuestion(List<KnowledgeElement> elements) {
        if (questionIndex + 1 > elements.size()) {
            throw new IllegalArgumentException("Illegal argument");
        }

        return elements.get(questionIndex);
    }
}
