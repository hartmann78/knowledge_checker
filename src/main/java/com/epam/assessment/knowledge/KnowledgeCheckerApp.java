package com.epam.assessment.knowledge;

import com.epam.assessment.knowledge.config.AppConfig;
import com.epam.assessment.knowledge.logic.AppLogic;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class KnowledgeCheckerApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        AppLogic app = context.getBean(AppLogic.class);

        app.greetings();
        app.askQuestion();
        app.saveData();
        app.showPerformance();
    }
}

/*
1. Запустить приложение
2. Загрузить файл с вопросами
3. Приветствовать пользователя. Указать кол-во вопросов в приветствии
4. Выбрать стратегию на основе properties
5. Задать вопрос
6. Ждать ответа от пользователя
    6.1 Если пользователь выбрал exit
        6.1.1 Вывести его успеваемость
    6.2 Если пользователь ответил на вопрос
        6.2.1 Если правильно, то поздравить его
        6.2.2 Если неправильно, то показать правильный ответ
    6.3 Сохранить данные в объект
7. Сохранить данные вопросов в файле
8. Остановить работу приложения
 */
