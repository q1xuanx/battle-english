package com.english.battle.models;


import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idRoom;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> currentUser;
    private int maxUserInRoom;
    private String statusRoom;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Questions> listQuestions;
}
