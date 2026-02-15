package com.epam.assessment.knowledge.logic.strategies;

import com.epam.assessment.knowledge.model.KnowledgeElement;

import java.util.List;

public class FixedStrategy implements QuestionSelectionStrategy {

    public FixedStrategy(int questionIndex) {
    }

    @Override
    public KnowledgeElement selectQuestion(List<KnowledgeElement> elements) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
