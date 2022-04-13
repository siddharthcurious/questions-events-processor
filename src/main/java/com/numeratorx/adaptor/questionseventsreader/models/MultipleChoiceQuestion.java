package com.numeratorx.adaptor.questionseventsreader.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "multiple_choice_questions")
public class MultipleChoiceQuestion extends DateAudit {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private int gsn;
    private String questionText;
    private String options;
    private String correctChoice;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="questionsPaper_id", nullable=false)
    private QuestionsPaper questionsPaper;
}
