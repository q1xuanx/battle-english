package com.english.battle.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class DetailsRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Room idRoom;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private User userSubmit;
    private Date timeSubmit;
    private int soccerOfUser;
}
