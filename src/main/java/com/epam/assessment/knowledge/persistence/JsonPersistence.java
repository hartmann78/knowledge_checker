package com.epam.assessment.knowledge.persistence;

import com.epam.assessment.knowledge.model.KnowledgeBank;
import com.epam.assessment.knowledge.model.KnowledgeElement;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
public class JsonPersistence implements KnowledgeBankPersistence {
    private final String dataFilePath;
    private final ObjectMapper objectMapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();

    public JsonPersistence(@Value("${data.file}") String dataFilePath) {
        this.dataFilePath = dataFilePath;
    }

    @Override
    public KnowledgeBank load() {
        try {
            String content = Files.readString(Path.of(dataFilePath));
            List<KnowledgeElement> elements = objectMapper.readValue(content, new TypeReference<>() {
            });

            return KnowledgeBank.builder().elements(elements).build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(KnowledgeBank knowledgeBank) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(dataFilePath), knowledgeBank.getElements());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
