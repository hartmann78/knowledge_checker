package com.epam.assessment.knowledge.persistence;

import com.epam.assessment.knowledge.model.KnowledgeBank;

public interface KnowledgeBankPersistence {
    KnowledgeBank load();

    void save(KnowledgeBank knowledgeBank);
}
