package com.numeratorx.adaptor.questionseventsreader.response;

import lombok.Data;

import java.util.List;

@Data
public class MultipleChoiceQuestionResponse {
    private String id;
    private int gsn;
    private String questionText;
    private List<String> options;
}
