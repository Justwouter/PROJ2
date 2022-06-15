package com.logic;

import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("deprecation")
public class ObservableAdapter extends Observable {

    public void addObserver(Observer observer){
        super.addObserver(observer);
        alertAll();
    }

    public void alertAll(){
        this.setChanged();
        this.notifyObservers();
    }
}
