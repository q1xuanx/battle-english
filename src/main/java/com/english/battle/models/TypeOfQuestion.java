package com.english.battle.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Data
@Table(name = "type_of_question")
@RequiredArgsConstructor
public class TypeOfQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String IdTypeOfQuestion;
    @NotNull
    private String NameType;
    private boolean IsHide = false;
}
