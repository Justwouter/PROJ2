package com.logic;

public class Point {

    public int points;

    final transient private ObservableAdapter observableAdapter = new ObservableAdapter();

    public ObservableAdapter getObservableAdapter(){
        return observableAdapter;
    }
    
    public void setPoints(int amount){
        this.points = amount;
        observableAdapter.alertAll();
    }

    public int getPoints(){
        return points;
    }

    public void addPoints(int amount){
        //is able to handle negative values for int amount
        points += amount;
        observableAdapter.alertAll();
    }

    public String getPointsString(){
        return "" + points;
    }

    public boolean enoughBalance(int amount){
        return amount <= points;
    }
}
