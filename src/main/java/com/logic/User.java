package com.logic;

import java.util.ArrayList;
import java.util.Calendar;

public class User {

    //public omdat het dan gelezen en veranderd kan worden in JSon
    public String naam;
    public int rank;
    public Point point = new Point();
    public boolean isAdmin = false;
    public ArrayList<PuntMutatie> puntVerandering = new ArrayList<PuntMutatie>();
    public Calendar c;
    public boolean weeklyPointsObtained = false;

    public ArrayList<Reis> PreSets = new ArrayList<>();

    public String username;
    public String password;
    public String filiaal;

    public User(String naam, boolean isAdmin, String username, String password, String filiaal){
        this.naam = naam;
        this.username = username;
        this.password = password;
        this.filiaal = filiaal;
        point.setPoints(1000);
        Leaderboard.addUser(this);
        for (int i = 0; i < 5; i++) {
            PreSets.add(new Reis(null, null, null));
        }
        SaveManager.writeToSave(this);
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

    public boolean getIsAdmin(){
        return isAdmin;
    }

    public String getUsername(){
        return username;
    }    

    public String getFiliaal() {
        return filiaal;
    }

    public void setFiliaal(String filiaal) {
        this.filiaal = filiaal;
    }

    public void addPuntMutatie(int amount){
        PuntMutatie p = new PuntMutatie(amount);
        puntVerandering.add(p);
    }
    public void addPuntMutatie(int amount, Calendar datum){
        PuntMutatie p = new PuntMutatie(amount, datum);
        puntVerandering.add(p);
    }

    public void puntMutatieCleanUp(){
        for(PuntMutatie pm : puntVerandering){
            if(!pm.isFromLast4Weeks()){
                puntVerandering.remove(puntVerandering.indexOf(pm));
            }
        }
    }

    public ArrayList<PuntMutatie> getPuntMutaties(){
        puntMutatieCleanUp();
        return this.puntVerandering;
    }

    public Integer getPuntMutatiesAsInteger(){
        puntMutatieCleanUp();
        Integer addedMutations = 0;
        for (PuntMutatie pm: puntVerandering){
            addedMutations += pm.getPuntVerandering();
        }
        return addedMutations;
    }

    //made by BarmanTurbo
    protected void addWeeklyPoints(){
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

    public boolean checkPassword(String attempt){
        return attempt.equals(password);
    }

}
