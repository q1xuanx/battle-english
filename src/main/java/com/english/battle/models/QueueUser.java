package com.english.battle.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Queue;


@Entity
@Getter
@Setter
public class QueueUser {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idUser;
    @ManyToOne
    private User user;
}
