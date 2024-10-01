package com.english.battle.models;


import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class QuestionAfterCheck {
    @ManyToOne(fetch = FetchType.EAGER)
    private Questions question;
    private String answerUserChose;
    private int pointGet;
}
