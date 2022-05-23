package com.logic;

import java.util.ArrayList;

public class User {
    private String naam;
    private int rank;
    private Point point = new Point();
    private boolean isAdmin = false;

    private ArrayList<Reis> PreSets = new ArrayList<>();

    public User(String naam){
        this.naam = naam;
        point.setPoints(1000);
        Leaderboard.addUser(this);
        for (int i = 0; i < 5; i++) {
            PreSets.add(new Reis(null, null, null));
        }
    }

    public void setReis(int index, Reis reis){
        PreSets.set(index, reis);
    }

    public ArrayList<Reis> getReizen(){
        return PreSets;
    }

    public Reis getReis(int index){
        return PreSets.get(index);
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
    
    //Made By BarmanTurbo
    public String vergelijkPuntMetUitstoot(){ 
        long uitstoot = 1000-point.getPoints();
        if(uitstoot <= 1){
            return "Je hebt nauwelijks CO2 uitgestoten!";
        }
        String output = "Je CO2 uitstoot komt overeen met ";
        if(uitstoot<9){
            output += "wat een vrachtwagen per km uitstoot";
            return output;
        }
        if(uitstoot <28){
            output += "wat een vrachtwagen uitstoot per " + (uitstoot / 9)+ " km";
            return output;
        }
        if(uitstoot <20459){
            //ja er kan staan "wat 1 bomen", en dat is niet heel mooi, maar ik heb geen zin in veel extra code
            output += "wat " + (uitstoot / 28) + " bomen per jaar opnemen";
            return output;
        }
        if(uitstoot <122000000){
            output += "wat de Ever Given uitstoot per " + (uitstoot / 20459) + " km";
        }else{
            output += ""+(uitstoot/122000000)+" keer zoveel dan wat de gemeente Amsterdam uitstootte in 2020";
        }

        return output;
    }

}
