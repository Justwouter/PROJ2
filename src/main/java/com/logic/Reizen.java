package com.logic;

import java.util.ArrayList;

public class Reizen {

    private String naam;
    private Transportmiddel transportmiddel;
    private Integer kilometers;

    private static final ArrayList<Reizen> reizen = new ArrayList<>();


    public Reizen(String naam, Transportmiddel transportmiddel, int kilometers) {
        this.naam = naam;
        this.transportmiddel = transportmiddel;
        this.kilometers = kilometers;
        reizen.add(this);
    }
    
    public String getNaamReis() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public Transportmiddel getTransportmiddel() {
        return transportmiddel;
    }

    public void setTransportmiddel(Transportmiddel transportmiddel) {
        this.transportmiddel = transportmiddel;
    }

    public Integer getKilometers() {
        return kilometers;
    }

    public void setKilometers(int kilometers) {
        this.kilometers = kilometers;
    }

    public static ArrayList<Reizen> getReis(){
        return reizen;
    }

    

}
