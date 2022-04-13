package com.numeratorx.adaptor.questionseventsreader.repositories;

import com.numeratorx.adaptor.questionseventsreader.models.QuestionsPaper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionsPaperRepository extends JpaRepository<QuestionsPaper, String> {
    List<QuestionsPaper> findByUserId(String userId);
    List<QuestionsPaper> findByChannelId(String channelId);
    Optional<QuestionsPaper> findByUserIdAndHeading(String userId, String heading);
}
