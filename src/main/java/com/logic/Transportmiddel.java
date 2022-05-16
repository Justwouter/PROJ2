package com.logic;

import java.util.ArrayList;

public class Transportmiddel {
    private final String NAAM;
    private int uitstoot;
    private int kosten;

    private static final ArrayList<Transportmiddel> transportmiddelen = new ArrayList<>();

    public Transportmiddel(String naam, int uitstoot, int kosten) {
        this.NAAM = naam;
        this.uitstoot = uitstoot;
        this.kosten = kosten;
        transportmiddelen.add(this);
    }

    public void setUitstoot(int uitstoot){
        this.uitstoot = uitstoot;
    }

    public void setKosten(int kosten){
        this.kosten = kosten;
    }

    public String getNaam(){
        return this.NAAM;
    }

    public int getUitstoot(){
        return this.uitstoot;
    }

    public int getKosten(){
        return this.kosten;
    }

    public static ArrayList<Transportmiddel> getTransportmiddelen(){
        return transportmiddelen;
    }

    public int berekenReispunten(int Afstand){
        return ((Afstand)*(this.kosten));
    }
}
