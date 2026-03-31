package com.epam.assessment.knowledge.logic.sort;

import com.epam.assessment.knowledge.model.KnowledgeBank;
import com.epam.assessment.knowledge.model.KnowledgeElement;
import com.epam.assessment.knowledge.persistence.JsonPersistence;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class WeightSortTest {
    @Test
    public void testWeightSort() {
        // Given
        String testFilePath = "test_data/knowledge.json";
        JsonPersistence persistence = new JsonPersistence(testFilePath);

        // Act
        KnowledgeBank bank = persistence.load();
        List<KnowledgeElement> elements = bank.getElements();

        // Sort
        elements.sort(Comparator.comparingInt(WeightSort::weightSort));

        // --- First KnowledgeElement ---
        KnowledgeElement first = elements.get(0);
        assertAll("KnowledgeElement 1: " + first,
                () -> assertEquals(3, first.getId(), "id"),
                () -> assertEquals("Who is the director and writer of movie Star Wars 'A New Hope'?", first.getQuestion(), "question"),
                () -> assertEquals("George Lucas", first.getAnswer(), "answer"),
                () -> assertEquals("Culture", first.getTopic(), "topic"),
                () -> assertNotNull(first.getLastAsked(), "lastAsked"),
                () -> assertEquals(1, first.getHistory().size(), "history size"),
                () -> assertEquals(Boolean.FALSE, first.getHistory().get(0), "history first element")
        );

        // --- Second KnowledgeElement ---
        KnowledgeElement second = elements.get(1);
        assertAll("KnowledgeElement 2: " + second,
                () -> assertEquals(1, second.getId(), "id"),
                () -> assertEquals("What is the capital of Germany?", second.getQuestion(), "question"),
                () -> assertEquals("Berlin", second.getAnswer(), "answer"),
                () -> assertEquals("Geography", second.getTopic(), "topic"),
                () -> assertNull(second.getLastAsked(), "lastAsked"),
                () -> assertEquals(0, second.getHistory().size(), "history size")
        );

        // --- Third KnowledgeElement ---
        KnowledgeElement third = elements.get(2);
        assertAll("KnowledgeElement 3: " + third,
                () -> assertEquals(2, third.getId(), "id"),
                () -> assertEquals("When did Christopher Columbus discover the Americas", third.getQuestion(), "question"),
                () -> assertEquals("1492", third.getAnswer(), "answer"),
                () -> assertEquals("History", third.getTopic(), "topic"),
                () -> assertNotNull(third.getLastAsked(), "lastAsked"),
                () -> assertEquals(3, third.getHistory().size(), "history size"),
                () -> assertEquals(Boolean.FALSE, third.getHistory().get(0), "history first element")
        );
    }
}
