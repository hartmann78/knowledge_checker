package com.epam.assessment.knowledge.logic.strategy;

import com.epam.assessment.knowledge.logic.strategies.AdaptiveStrategy;
import com.epam.assessment.knowledge.model.KnowledgeBank;
import com.epam.assessment.knowledge.model.KnowledgeElement;
import com.epam.assessment.knowledge.persistence.JsonPersistence;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class AdaptiveStrategyTest {

    @Test
    public void testAdaptiveStrategy() {
        // Given
        String testFilePath = "test_data/knowledge.json";
        JsonPersistence persistence = new JsonPersistence(testFilePath);
        Random random = new Random();
        AdaptiveStrategy strategy = new AdaptiveStrategy(random);

        // Act
        KnowledgeBank bank = persistence.load();
        List<KnowledgeElement> elements = bank.getElements();
        KnowledgeElement element = strategy.selectQuestion(elements);

        // When & Then
        assertNotNull(element);
        assertTrue(elements.contains(element));
    }
}
