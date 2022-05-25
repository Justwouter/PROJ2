package com.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Admin extends User{
    public Admin(String naam) {
        super(naam, true);
    }

    //made by BarmanTurbo
    public ArrayList<User> duurzaamsteUsers(){
        ArrayList<User> allUsers = Leaderboard.getUsers();
        if(getIsAdmin()){
            Integer maxPoints = allUsers.get(1).getPoints();
            ArrayList<User> topUsers= new ArrayList<User>();
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

        return allUsers;
    }

    public ArrayList<User> BesteUsersVanDeMaand(){
        ArrayList<User> allUsers = Leaderboard.getUsers();
        Comparator<User> vergelijker = Comparator.comparing(User::getPuntMutatiesAsInteger);
        Collections.sort(allUsers, vergelijker);
        return allUsers;
    }
    //TODO check of dit hier wel werkt!!
    
}
