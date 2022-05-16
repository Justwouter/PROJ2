package com.logic;

public class User {
    private String naam;
    private int rank;
    private Point point = new Point();

    public User(String naam){
        this.naam = naam;
        point.setPoints(1000);
        Leaderboard.addUser(this);
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
