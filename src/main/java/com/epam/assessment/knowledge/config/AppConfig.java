package com.epam.assessment.knowledge.config;

import com.epam.assessment.knowledge.logic.strategies.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Random;

@Configuration
@ComponentScan("com.epam.assessment.knowledge")
@PropertySource("classpath:application.properties")
public class AppConfig {
    @Bean
    public QuestionSelectionStrategy questionSelectionStrategy(@Value("${question.selection.strategy}") String strategy,
                                                               @Value("${fixedstrategy.questionIndex}") int questionIndex) throws Exception {
        return switch (strategy) {
            case "FIXED" -> new FixedStrategy(questionIndex);
            case "SEQUENTIAL" -> new SequentialStrategy();
            case "RANDOM" -> new RandomStrategy(new Random());
            case "ADAPTIVE" -> new AdaptiveStrategy(new Random());
            default -> throw new Exception("Found unknown strategy!");
        };
    }
}
