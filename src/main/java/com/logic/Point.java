package com.logic;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Point {

    public int points;

    final transient private PropertyChangeSupport PCS = new PropertyChangeSupport(this);

    public void addObserver(PropertyChangeListener l){
        PCS.addPropertyChangeListener("points", l);
        PCS.firePropertyChange("points", 0, points);
    }

    public void setPoints(int amount){
        int old = points;
        this.points = amount;
        PCS.firePropertyChange("points", old, points);
    }

    public int getPoints(){
        return points;
    }

    public void addPoints(int amount){
        //is able to handle negative values for int amount
        int old = points;
        points += amount;
        PCS.firePropertyChange("points", old, points);
    }

    public String getPointsString(){
        return "" + points;
    }

    public boolean enoughBalance(int amount){
        return amount <= points;
    }
}
