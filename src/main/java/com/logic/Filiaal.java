package com.logic;

import java.util.ArrayList;

public class Filiaal {
    public String naam;

    public static ArrayList<Filiaal> filialen = new ArrayList<>();

    public Filiaal(String naam) {
        this.naam = naam;
        filialen.add(this);
    }    

    public String getNaam() {
        return naam;
    }

    public ArrayList<Filiaal> getFilialen(){
        return filialen;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }
}
