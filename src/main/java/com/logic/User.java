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
        if(uitstoot <1000-20459){
            
            output += "wat " + (uitstoot / 28) + " bomen per jaar opnemen";
            return output;
        }
        if(uitstoot <1000-122000000){
            output += "wat de Ever Given uitstoot per " + (uitstoot / 20459) + " km";
        }else{
            output += ""+(uitstoot/122000000)+" keer zoveel dan wat de gemeente Amsterdam uitstootte in 2020";
        }
        
        return output;
    }
}
