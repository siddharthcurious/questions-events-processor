package com.numeratorx.adaptor.questionseventsreader.response;

import lombok.Data;

import java.util.List;

@Data
public class QuestionPaperResponse {
    private String id;
    private String createdAt;
    private String updatedAt;
    private String userId;
    private String heading;
    private List<String> tags;
    private List<MultipleChoiceQuestionResponse> multipleChoiceQuestionsResponse;
}
