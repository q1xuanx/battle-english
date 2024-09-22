package com.english.battle.models;


import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idRoom;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<User> currentUser;
    private int maxUserInRoom;
    private String statusRoom;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Questions> listQuestions;
}
