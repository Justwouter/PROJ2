package com.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public abstract class Leaderboard {

    private static ArrayList<User> users = new ArrayList<>();

    public static void addUser(User user){
        users.add(user);
    }

    public static ArrayList<User> getUsers(){
        comparator();
        updateRanking();
        return users;
    }

    private static void comparator(){
        Comparator<User> userComparator = Comparator.comparing(User::getPoints);
        Collections.sort(users, userComparator);
        Collections.reverse(users);
        updateRanking();
    }

    private static void updateRanking(){
        for (int i = 0; i < users.size(); i++) {
            users.get(i).setRank(i+1);
        }
    }
}
