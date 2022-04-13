package com.numeratorx.adaptor.questionseventsreader.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "questions_papers")
public class QuestionsPaper extends DateAudit {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String generatedId;
    private String userId;
    private String channelId;
    private String tags;
    private String heading;
    @OneToMany(mappedBy="questionsPaper", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<MultipleChoiceQuestion> multipleChoiceQuestions;
}
