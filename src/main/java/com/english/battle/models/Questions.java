package com.english.battle.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Data
@Table(name="questions")
public class Questions {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String IdQuestion;
    @NotNull
    private String DetailQuestion;
    @NotNull
    private String AnswerA;
    @NotNull
    private String AnswerB;
    @NotNull
    private String AnswerC;
    @NotNull
    private String AnswerD;
    private Boolean IsHide = false;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_correct", referencedColumnName = "IdCorrectAnswer")
    private CorrectAnswer AnswerCorrect;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_question", referencedColumnName = "IdTypeOfQuestion")
    private TypeOfQuestion TypeOfQuestion;
}
