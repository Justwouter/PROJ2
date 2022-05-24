package com.logic;

import java.util.ArrayList;
import java.util.Calendar;

public class User {
    private String naam;
    private int rank;
    private Point point = new Point();
    private boolean isAdmin = false;
    private Calendar c;
    private boolean weeklyPointsObtained = false;

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


    public void addWeeklyPoints(){
/*
*Elke maandag krijg je 1000 punten mits je inlogt. Dat is maar één keer in de week.  Daarna wordt boolean
*weeklyPointsObtained op true gezet zodat je niet nog een keer de punten kan krijgen.
*Op zondag wordt je weeklypointsObtained op false gezet.
*Alleen op maandag kan je je wekelijke punten krijgen.
*/
        c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_WEEK);
        if(!weeklyPointsObtained){
            if(day == 2){
                point.addPoints(1000);
                weeklyPointsObtained = true;
            }
        }

        if(day == 1){
            weeklyPointsObtained = false;
        }
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
            if(uitstoot/28==1){
                output += "wat 1 boom per jaar opneemt";
            } else {
                output += "wat " + (uitstoot / 28) + " bomen per jaar opnemen";
            }
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
