package com.epam.assessment.knowledge.logic.strategy;

import com.epam.assessment.knowledge.logic.strategies.RandomStrategy;
import com.epam.assessment.knowledge.model.KnowledgeElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class RandomStrategyTest {
    private List<KnowledgeElement> elements;

    @BeforeEach
    public void setUp() {
        elements = List.of(
                new KnowledgeElement(1, "Geography",
                        "What is the capital of Germany?", "Berlin"));
    }

    @Test
    public void testSelectRandomQuestionIndex() {
        // Given
        Random random = new Random();
        RandomStrategy strategy = new RandomStrategy(random);

        // When
        KnowledgeElement element = strategy.selectQuestion(elements);

        // Then
        assertNotNull(element);
        assertEquals(1, element.getId(), "Should select the first question");
    }

    @Test
    public void testSelectFromEmptyKnowledgeBank() {
        // Given
        List<KnowledgeElement> elements = Collections.emptyList();
        Random random = new Random();
        RandomStrategy strategy = new RandomStrategy(random);

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> strategy.selectQuestion(elements));
    }
}
