package com.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Admin extends User{
    public Admin(String naam, String username, String password) {
        super(naam, true, username, password);
    }

    //made by BarmanTurbo
    public ArrayList<User> duurzaamsteUsers(){
        ArrayList<User> allUsers = Leaderboard.getUsers();
        ArrayList<User> topUsers= new ArrayList<User>();
        if(getIsAdmin()){
            Integer maxPoints = allUsers.get(0).getPoints();
            for(User u : allUsers){
                if(u.getPoints()==maxPoints){
                    topUsers.add(u);
                }else{
                    break; //saves runtime, zodat je alleen over de users met de hoogste punten loopt.
                }
            }
        } else {
            throw new IllegalArgumentException("Error in Admin.duurzaamsteUsers: Niet de juiste rechten om dit te bekijken");
        }
        return topUsers;
    }

    public Comparator<User> BesteUsersVanDeMaand(){
        ArrayList<User> allUsers = Leaderboard.getUsers();
        Comparator<User> vergelijker = Comparator.comparing(User::getUserPuntMutatiesAsInteger);
        Collections.sort(allUsers, vergelijker);
        return vergelijker;
    }    
}
