package com.epam.assessment.knowledge.logic.sort;

import com.epam.assessment.knowledge.model.KnowledgeElement;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WeightSort {
    public static int weightSort(KnowledgeElement elements) {
        List<Boolean> history = elements.getHistory();
        int historyWeight = 0;
        int timeWeight = 0;

        if (elements.getLastAsked() == null) {
            timeWeight = 1;
        }

        if (history != null && !history.isEmpty()) {
            historyWeight += history.get(history.size() - 1) ? -1 : 2;
        }

        if (history != null && history.size() >= 2) {
            historyWeight += history.get(history.size() - 2) ? -1 : 2;
        }

        return (historyWeight + timeWeight) * -1;
    }
}
