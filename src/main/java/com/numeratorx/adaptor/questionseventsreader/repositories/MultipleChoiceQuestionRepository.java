package com.numeratorx.adaptor.questionseventsreader.repositories;

import com.numeratorx.adaptor.questionseventsreader.models.MultipleChoiceQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MultipleChoiceQuestionRepository extends JpaRepository<MultipleChoiceQuestion, String> {
}
