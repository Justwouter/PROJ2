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
        long punten = point.getPoints();
        String output = "Je CO2 uitstoot komt overeen met ";
        if(punten<9){
            output += "wat 1 vrachtwagen per km uitstoot";
            return output;
        }
        if(punten <28){
            output += "wat 1 vrachtwagen uitstoot per " + (punten / 9)+ " km";
            return output;
        }
        if(punten < 20459){
            
            output += "wat " + (punten / 28) + " bomen per jaar opnemen";
            return output;
        }
        if(punten < 122000000){
            output += "wat de Ever Given uitstoot per " + (punten / 20459) + " km";
            return output;
        }else{
            output += ""+(punten/122000000)+" keer zoveel dan wat de gemeente Amsterdam uitstootte in 2020";
        }
        
        return output;
    }
}
