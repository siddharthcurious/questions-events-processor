package com.numeratorx.adaptor.questionseventsreader.services;

import com.numeratorx.adaptor.questionseventsreader.models.MultipleChoiceQuestion;
import com.numeratorx.adaptor.questionseventsreader.models.QuestionsPaper;
import com.numeratorx.adaptor.questionseventsreader.repositories.QuestionsPaperRepository;
import com.numeratorx.adaptor.questionseventsreader.response.MultipleChoiceQuestionResponse;
import com.numeratorx.adaptor.questionseventsreader.response.QuestionPaperResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuestionPaperService {

    @Autowired
    private QuestionsPaperRepository questionsPaperRepository;

    public QuestionPaperResponse getQuestionPaperById(String id){
        Optional<QuestionsPaper> questionsPaper = questionsPaperRepository.findById(id);
        return questionsPaper.map(this::questionPaperBuilder).orElse(null);
    }

    public QuestionPaperResponse getQuestionPaperByUserIdAndHeading(String userId, String heading){
        Optional<QuestionsPaper> questionsPaper = questionsPaperRepository.findByUserIdAndHeading(userId, heading);
        return questionsPaper.map(this::questionPaperBuilder).orElse(null);
    }

    private QuestionPaperResponse questionPaperBuilder(QuestionsPaper questionsPaper){
        QuestionPaperResponse questionPaperResponse = new QuestionPaperResponse();
        questionPaperResponse.setId(questionsPaper.getId());
        questionPaperResponse.setUserId(questionsPaper.getUserId());
        questionPaperResponse.setHeading(questionsPaper.getHeading());
        questionPaperResponse.setTags(Arrays.asList(questionsPaper.getTags().split("#")));
        questionPaperResponse.setCreatedAt(questionsPaper.getCreatedAt().toString());
        questionPaperResponse.setUpdatedAt(questionsPaper.getUpdatedAt().toString());
        List<MultipleChoiceQuestionResponse> mcqResponseList = new LinkedList<>();

        for(MultipleChoiceQuestion mcq: questionsPaper.getMultipleChoiceQuestions()){
            MultipleChoiceQuestionResponse mcqResponse = new MultipleChoiceQuestionResponse();
            mcqResponse.setId(mcq.getId());
            mcqResponse.setGsn(mcq.getGsn());
            mcqResponse.setQuestionText(mcq.getQuestionText());
            mcqResponse.setOptions(Arrays.asList(mcq.getOptions().split("#")));
            mcqResponseList.add(mcqResponse);
        }
        questionPaperResponse.setMultipleChoiceQuestionsResponse(mcqResponseList);
        return questionPaperResponse;
    }
}
