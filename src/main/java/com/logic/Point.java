package com.logic;

public class Point {
    public Integer points;

    public void setPoints(int amount){
        points = amount;
    }

    public int getPoints(){
        return points;
    }

    public void addPoints(int amount){
        //is able to handle negative values for int amount
        points += amount;
    }

    public String getPointsString(){
        return "" + points;
    }

    public boolean enoughBalance(int amount){
        return amount <= points;
    }
}
