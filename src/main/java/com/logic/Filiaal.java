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

    public static Filiaal getFiliaal(int filiaal){
        return filialen.get(filiaal);
    }

    public static String getNaamFiliaal(int filiaal){
        return filialen.get(filiaal).getNaam();
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public static ArrayList<Filiaal> getFilialen(){
        return filialen;
    }
}
