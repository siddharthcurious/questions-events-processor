package com.numeratorx.adaptor.questionseventsreader.services;

import com.google.gson.Gson;
import com.numeratorx.adaptor.questionseventsreader.deserializers.QuestionsSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageReceiver {

    private static final Logger log = LoggerFactory.getLogger(KafkaMessageReceiver.class);

    @Autowired
    private MultipleChoiceQuestionService multipleChoiceQuestionService;

    @KafkaListener(topics = "text-questions-processor", groupId = "text-questions")
    public void listenTextQuestions(String message){
        Gson g = new Gson();
        QuestionsSet s = g.fromJson(message, QuestionsSet.class);
        multipleChoiceQuestionService.createQuestions(s);
    }
}
