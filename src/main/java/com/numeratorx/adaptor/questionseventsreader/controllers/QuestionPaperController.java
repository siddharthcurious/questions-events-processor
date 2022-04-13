package com.numeratorx.adaptor.questionseventsreader.controllers;

import com.numeratorx.adaptor.questionseventsreader.response.QuestionPaperResponse;
import com.numeratorx.adaptor.questionseventsreader.response.Response;
import com.numeratorx.adaptor.questionseventsreader.response.StatusCode;
import com.numeratorx.adaptor.questionseventsreader.services.QuestionPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/numerator/v1")
@RestController
public class QuestionPaperController {

    @Autowired
    private QuestionPaperService questionPaperService;

    @GetMapping("/get/question/paper/{id}")
    public ResponseEntity<?> getQuestionPaperUsingId(@PathVariable("id") String id){
        QuestionPaperResponse response = questionPaperService.getQuestionPaperById(id);
        Map<String, Object> data = new HashMap<>();
        if(response != null) {
            data.put("questionsPaper", response);
            return new ResponseEntity<>(
                    new Response(LocalDateTime.now(),
                    "Question paper feched",
                    StatusCode.OK, data),
                    HttpStatus.OK
            );
        }
        return new ResponseEntity<>(
                new Response(LocalDateTime.now(),
                "Question paper not found: "+id,
                StatusCode.NOT_OK, data),
                HttpStatus.NOT_FOUND
        );
    }

    @GetMapping("/get/question/paper/{userId}/{heading}")
    public ResponseEntity<?> getQuestionPaperUsingUserIdAndHeading(@PathVariable("userId") String userId,
                                                                   @PathVariable("heading") String heading){
        QuestionPaperResponse response = questionPaperService.getQuestionPaperByUserIdAndHeading(userId, heading);
        Map<String, Object> data = new HashMap<>();
        if(response != null) {
            data.put("questionsPaper", response);
            return new ResponseEntity<>(
                    new Response(LocalDateTime.now(), "Question paper feched",
                    StatusCode.OK, data),
                    HttpStatus.OK
            );
        }
        return new ResponseEntity<>(
                new Response(LocalDateTime.now(), "Question paper not found: "+userId+", "+heading,
                StatusCode.NOT_OK, data),
                HttpStatus.NOT_FOUND
        );
    }
}
