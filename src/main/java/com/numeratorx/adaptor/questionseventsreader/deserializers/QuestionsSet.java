package com.numeratorx.adaptor.questionseventsreader.deserializers;

import lombok.Data;

import java.util.List;

@Data
public class QuestionsSet {
    private String userId;
    private String heading;
    private List<String> tags;
    private List<QuestionAnswers> qaList;
}
