package com.logic;

import com.gui.LeaderBoardController;

public class User {
    private String naam;
    private int points;
    private Point point = new Point();

    public User(String naam){
        this.naam = naam;
        this.points = 2000;
        point.setPoints(1000);
        LeaderBoardController.addUser(this);
    }

    public String getNaam(){
        return naam;
    }

    public Integer getPoints(){
        return Integer.parseInt(point.getPointsValue());
    }

    public Point getPoint(){
        return point;
    }
}
