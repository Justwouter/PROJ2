package com.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public abstract class Leaderboard {

    private static ArrayList<User> users = new ArrayList<>();
    //heb een tijdelijke arraylist voor filters gemaakt zodat die het leaderboard gefilterd kan vullen
    private static ArrayList<User> usersFilterd = new ArrayList<>();
    private static ArrayList<User> usersBest = new ArrayList<>();

    public static void addUser(User user){
        users.add(user);
    }

    //heb een parameter filter toegevoegd zodat je users kan ophalen met welk filiaal filter dan ook
    public static ArrayList<User> getUsers(String filter){
        usersFilterd.clear(); 
        usersBest.clear();       
        for (User user : users) {
            if(filter.equals("")){
                comparator();
                updateRanking();
                return users;
            }
            if(user.getFiliaal().equals(filter)){
                usersFilterd.add(user);
                comparatorFilterd();
                updateRankingFilterd();
                return usersFilterd;
            }
            if(filter.substring(0,4).equals("best")){
                if (filter.equals("beste4usersvandemaand")){
                    if(users.lastIndexOf(user)<=3){
                        best(user);
                    }
                }else{
                    best(user);
                }
            }
            
        }
        return usersBest;
    }

    private static void best(User user){
        usersBest.add(user);
        comparatorBest();
        updateRankingBest(); 
    }

    private static void comparator(){
        Comparator<User> userComparator = Comparator.comparing(User::getTotalCO2);
        Collections.sort(users, userComparator);
        //Collections.reverse(users);
        updateRanking();
    }

    private static void updateRanking(){
        for (int i = 0; i < users.size(); i++) {
            users.get(i).setRank(i+1);
        }
    }
    
    //doet precies t zelfde als de normale versie alleen dan voor userFilterd
    private static void comparatorFilterd(){
        Comparator<User> userComparator = Comparator.comparing(User::getTotalCO2);
        Collections.sort(usersFilterd, userComparator);
        //Collections.reverse(usersFilterd);
        updateRanking();
    }

    //doet precies t zelfde als de normale versie alleen dan voor userFilterd
    private static void updateRankingFilterd(){
        for (int i = 0; i < usersFilterd.size(); i++) {
            usersFilterd.get(i).setRank(i+1);
        }
    }

    //doet precies t zelfde als de normale versie alleen dan voor userBest4
    private static void comparatorBest(){
        Comparator<User> userComparator = Comparator.comparing(User::getUserPuntMutatiesAsInteger);
        Collections.sort(usersBest, userComparator);
        Collections.reverse(usersBest);
        updateRanking();
    }

    //doet precies t zelfde als de normale versie alleen dan voor userBest4
    private static void updateRankingBest(){
        for (int i = 0; i < usersBest.size(); i++) {
            usersBest.get(i).setRank(i+1);
        }
    }
}
