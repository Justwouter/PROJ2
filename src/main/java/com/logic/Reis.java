package com.logic;

public class Reis {

    private String naam;
    private Transportmiddel transportmiddel;
    private Integer kilometers;

    public Reis(String naam, Transportmiddel transportmiddel, Integer kilometers) {
        this.naam = naam;
        this.transportmiddel = transportmiddel;
        this.kilometers = kilometers;
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

    public void setKilometers(Integer kilometers) {
        this.kilometers = kilometers;
    }
}
