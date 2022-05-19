package com.logic;

public class User {
    private String naam;
    private int rank;
    private Point point = new Point();

    public User(String naam){
        this.naam = naam;
        point.setPoints(1000);
        Leaderboard.addUser(this);
    }

    public String getNaam(){
        return naam;
    }

    public Integer getPoints(){
        return point.getPoints();
    }

    public Point getPoint(){
        return point;
    }

    public void setRank(int rank){
        this.rank = rank;
    }

    public int getRank(){
        return this.rank;
    }

    public String vergelijkPuntMetUitstoot(){
        long uitstoot = 1000-point.getPoints();
        String output = "";
        if(uitstoot <= 1){
            output =  "Je hebt nauwelijks CO2 uitgestoten!";
        }
        if(uitstoot < 9){
            output = "Je stoot gemiddeld per kilometer net zo veel uit als een vrachtwagen.";
        }
        if(uitstoot < 28){
            output = "";
        }
        if(uitstoot < 20459){
            if ((uitstoot / 28) > 1){
                output = "Je uitstoot wordt door " + (uitstoot / 28) + " bomen in een jaar opgenomen.";
            }else{
                output = "Je CO2 uitstoot wordt door één boom in een jaar opgenomen.";
            }
        }
        if(uitstoot < 122000000){
            output = "Je stoot gemiddeld per kilometer net zo veel uit als de Ever Given per " + (uitstoot / 20459) + " kilometer uitstoot.";
        }else{
            output = "Je CO2 uitstoot komt overeen met " + (uitstoot/122000000) + " keer zoveel dan wat de gemeente Amsterdam uitstootte in 2020";
        }
        return output;
    }
}
