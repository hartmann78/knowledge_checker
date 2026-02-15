package com.epam.assessment.knowledge.persistence;

import com.epam.assessment.knowledge.model.KnowledgeBank;
import org.springframework.stereotype.Component;

@Component
public class JsonPersistence implements KnowledgeBankPersistence {

    public JsonPersistence(String dataFilePath) {
    }

    @Override
    public KnowledgeBank load() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void save(KnowledgeBank knowledgeBank) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
