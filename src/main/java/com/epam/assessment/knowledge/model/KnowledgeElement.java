package com.epam.assessment.knowledge.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@JsonPropertyOrder({"id", "question", "answer", "category", "lastAsked", "history"})
public class KnowledgeElement {
    private int id;

    @JsonProperty("category")
    private String topic;

    private String question;
    private String answer;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime lastAsked;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Boolean> history;

    public KnowledgeElement(int id, String topic, String question, String answer) {
        this.id = id;
        this.topic = topic;
        this.question = question;
        this.answer = answer;
    }
}
