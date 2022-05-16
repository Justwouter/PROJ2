package com.logic;
public class Point {
    private static int points;

    public static void setPoints(int amount){
        points = amount;
    }

    public static int getPoints(){
        return points;
    }

    public static void addPoints(int amount){
        points += amount;
    }

    public static void subtractPoints(int amount){
        points -= amount;
    }

    public static String getPointsValue(){
        return "" + points;
    }
}
