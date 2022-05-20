package com.logic;

import java.util.ArrayList;

public class User {
    private String naam;
    private int rank;
    private Point point = new Point();

    private ArrayList<Reis> PreSets = new ArrayList<>();

    public User(String naam){
        this.naam = naam;
        point.setPoints(1000);
        Leaderboard.addUser(this);
        for (int i = 0; i < 5; i++) {
            PreSets.add(new Reis(null, null, null));
        }
    }

    public void setReis(int index, Reis reis){
        PreSets.set(index, reis);
    }

    public ArrayList<Reis> getReizen(){
        return PreSets;
    }

    public Reis getReis(int index){
        return PreSets.get(index);
    }

    public String getNaam(){
        return naam;
    }

    public Integer getPoints(){
        return point.getPoints();
    }

    public Point getPoint(){
        return point;
    }

    public void setRank(int rank){
        this.rank = rank;
    }

    public int getRank(){
        return this.rank;
    }
    
    

}
