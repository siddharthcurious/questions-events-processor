package com.numeratorx.adaptor.questionseventsreader.services;

import com.numeratorx.adaptor.questionseventsreader.models.MultipleChoiceQuestion;
import com.numeratorx.adaptor.questionseventsreader.deserializers.QuestionAnswers;
import com.numeratorx.adaptor.questionseventsreader.deserializers.QuestionsSet;
import com.numeratorx.adaptor.questionseventsreader.models.QuestionsPaper;
import com.numeratorx.adaptor.questionseventsreader.repositories.MultipleChoiceQuestionRepository;
import com.numeratorx.adaptor.questionseventsreader.repositories.QuestionsPaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
public class MultipleChoiceQuestionService {

    @Autowired
    private MultipleChoiceQuestionRepository questionRepository;

    @Autowired
    private QuestionsPaperRepository questionsPaperRepository;

    @Transactional
    public void createQuestions(QuestionsSet questionsSet){
        String userId = questionsSet.getUserId();
        String heading = questionsSet.getHeading();
        QuestionsPaper questionsPaper = new QuestionsPaper();
        questionsPaper.setHeading(heading);
        questionsPaper.setTags(String.join("#", questionsSet.getTags()));
        questionsPaper.setUserId(userId);
        questionsPaper.setGeneratedId(userId.toLowerCase()+"__"+heading.replaceAll(" ", "_").toLowerCase());
        QuestionsPaper questionsPaperDb = questionsPaperRepository.saveAndFlush(questionsPaper);
        int sequence = 1;
        for(QuestionAnswers qa: questionsSet.getQaList()){
            MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion();
            multipleChoiceQuestion.setQuestionText(qa.getQuestionText());
            multipleChoiceQuestion.setOptions(String.join("#",qa.getAnsOptions().subList(0, qa.getAnsOptions().size()-1)));
            multipleChoiceQuestion.setCorrectChoice(qa.getAnsOptions().get(qa.getAnsOptions().size()-1));
            multipleChoiceQuestion.setQuestionsPaper(questionsPaperDb);
            multipleChoiceQuestion.setGsn(sequence);
            questionRepository.saveAndFlush(multipleChoiceQuestion);
            sequence = sequence + 1;
        }
    }
}
