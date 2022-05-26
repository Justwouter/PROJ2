package com.logic;

import java.util.ArrayList;

public class Admin extends User{
    public Admin(String naam, String username, String password) {
        super(naam, false, username, password);
    }

    //made by BarmanTurbo
    public String duurzaamsteUsersAsString(){
        if(getIsAdmin()){
            ArrayList<User> topUsers = Leaderboard.getUsers();
            Integer maxPoints = topUsers.get(1).getPoints();
            String highestRankingUsers = "";
            for(User u : topUsers){
                if(u.getPoints()==maxPoints){
                    highestRankingUsers += u.getNaam();
                    if(topUsers.get(topUsers.indexOf(u)+1).getPoints() == maxPoints){
                        highestRankingUsers += ", ";
                    }else{
                        if(topUsers.indexOf(u)>0){
                            highestRankingUsers += " stoten het minste CO2 uit. Zij hebben elk " + maxPoints + " punten.";
                        }else{
                            highestRankingUsers += " stoot het minste CO2 uit. Hij/Zij heeft " + maxPoints + " punten.";
                        }
                    }
                }else{
                    break; //saves runtime, zodat je alleen over de users met de hoogste punten loopt.
                }
            }

            return highestRankingUsers;
        } else {
            return "Requires non-mafklapper (also known as admin) rights.";
        }
    }
    
}
