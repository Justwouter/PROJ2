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

    //made by BarmanTurbo
    public String duurzaamsteUsers(){
        ArrayList<User> topUsers = Leaderboard.getUsers();
        Integer maxPoints = topUsers.get(1).getPoints();
        String highestRankingUsers = "";
        for(User u : topUsers){
            if(u.getPoints()==maxPoints){
                highestRankingUsers += u.getNaam();
                if(topUsers.get(topUsers.indexOf(u)+1).getPoints() == maxPoints){
                    highestRankingUsers += ", ";
                }else{
                    if(topUsers.indexOf(u)>0){
                        highestRankingUsers += " stoten het minste CO2 uit. Zij hebben elk " + maxPoints + " punten.";
                    }else{
                        highestRankingUsers += " stoot het minste CO2 uit. Hij/Zij heeft " + maxPoints + " punten.";
                    }
                }
            }else{
                break; //saves runtime, zodat je alleen over de users met de hoogste punten loopt.
            }
        }

        return highestRankingUsers;
    }

    //made by BarmanTurbo
    public void addWeeklyPoints(){
        c = Calendar.getInstance();                 //Elke maandag krijg je 1000 punten mits je inlogt. Dat is maar één keer in de week.
        int day = c.get(Calendar.DAY_OF_WEEK);      //Daarna wordt boolean weeklyPointsObtained op True gezet zodat je niet nog een keer
        if(!weeklyPointsObtained){                  //de punten kan krijgen. Op zondag wordt je weeklyPoitnsObtained op false gezet.
            if(day == 2){                           //Je kan je wekelijkse punten alleen op maandag krijgen.
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
