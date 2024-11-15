package com.english.battle.compare;

import com.english.battle.models.User;

import java.util.Comparator;

public class UserComparator implements Comparator<User> {
    @Override
    public int compare(User o1, User o2) {
        if (o1.getRatings() == o2.getRatings()){
            return o2.getUsername().compareTo(o1.getUsername());
        }
        return o1.getRatings() - o2.getRatings();
    }
}
