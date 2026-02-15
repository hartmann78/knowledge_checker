package com.epam.assessment.knowledge.logic.strategies;

import com.epam.assessment.knowledge.model.KnowledgeBank;
import com.epam.assessment.knowledge.model.KnowledgeElement;

import java.util.List;

public interface QuestionSelectionStrategy {
    KnowledgeElement selectQuestion(List<KnowledgeElement> elements);
}
