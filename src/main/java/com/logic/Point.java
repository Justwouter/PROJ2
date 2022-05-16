package com.logic;
public class Point {
    private Integer points;

    public void setPoints(int amount){
        points = amount;
    }

    public int getPoints(){
        return points;
    }

    public void addPoints(int amount){
        points += amount;
    }

    public void subtractPoints(int amount){
        points -= amount;
    }

    public String getPointsValue(){
        return "" + points;
    }
}
