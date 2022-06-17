package com.logic;

import java.util.ArrayList;
import java.util.Calendar;

public class User {

    // public omdat het dan gelezen en veranderd kan worden in JSon
    public String naam;
    public int rank;
    public Point point = new Point();
    public boolean isAdmin = false;
    public ArrayList<PuntMutatie> puntVerandering = new ArrayList<PuntMutatie>();
    public Long totalCO2 = getTotalCO2();
    public Calendar c;
    public boolean weeklyPointsObtained = false;
    public ArrayList<PuntMutatie> userMonthlyPointStorage = new ArrayList<PuntMutatie>();

    public ArrayList<Reis> PreSets = new ArrayList<>();

    public String username;
    public String password;
    public String filiaal;
    
    public User(String naam, boolean isAdmin, String username, String password, String filiaal){
        this.naam = naam;
        this.username = username;
        this.password = password;
        this.filiaal = filiaal;
        this.isAdmin = isAdmin;
        point.setPoints(1000);
        Leaderboard.addUser(this);
        for (int i = 0; i < 5; i++) {
            PreSets.add(new Reis(null, null, null));
        }
    }

    public void setReis(int index, Reis reis){
        PreSets.set(index, reis);
    }

    public void setPassword(String Password){
        this.password = Password;
    }

    public String getPassword(){
        return this.password;
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
        return this.isAdmin;
    }

    public String getUsername(){
        return this.username;
    }

    public String getFiliaal() {
        return this.filiaal;
    }

    public void setFiliaal(String filiaal) {
        this.filiaal = filiaal;
    }

    /**
     * Loops trough all the PuntMutaties tied to the user and adds them together.
     * @return all the CO2 values added together & divided by 1000 to convert G to KG.
     */
    public Long getTotalCO2(){
        Long output = (long)0;
        for(PuntMutatie pm : puntVerandering){
            if(pm.getCO2() != null){
                output += pm.getCO2()/1000;
            }
        }
        if(userMonthlyPointStorage != null && userMonthlyPointStorage.size() > 0){
            for(PuntMutatie pm : userMonthlyPointStorage){
                if(pm.getCO2() != null){
                    output += pm.getCO2()/1000;
                }        
            }
        }
        this.totalCO2 = output;
        return output;
    }

    public void userAddPuntMutatie(int amount){
        PuntMutatie p = new PuntMutatie(amount);
        puntVerandering.add(p);
    }

    public void userAddPuntMutatie(int amount, Calendar datum) {
        puntVerandering.add(new PuntMutatie(amount, datum));
    }

    public void userAddPuntMutatie(int amount, int uitstoot) {
        puntVerandering.add(new PuntMutatie(amount, uitstoot));
    }

    protected void monthlyPuntStorageAdd(Integer punten,Integer CO2, int maand){

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, maand, 0);
        userMonthlyPointStorage.add(new PuntMutatie(punten,CO2, calendar));
    }

    public ArrayList<PuntMutatie> getUserPuntMutaties() {
        MutatieCleanup.userPuntMutatieCleanupALLES();
        return this.puntVerandering;
    }

    public Integer getUserPuntMutatiesAsInteger() {
        MutatieCleanup.userPuntMutatieCleanupALLES();
        Integer addedMutations = 0;
        for (PuntMutatie pm : puntVerandering) {
            addedMutations += pm.getPuntVerandering();
        }
        return addedMutations;
    }

    // made by BarmanTurbo
    public void addWeeklyPoints() {
        c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_WEEK);
        // Elke maandag krijg je 1000 punten mits je inlogt. Dat is maar één keer in de week.
        // Daarna wordt boolean weeklyPointsObtained op True gezet zodat je niet nog een keer
        // de punten kan krijgen. Op zondag wordt je weeklyPoitnsObtained op false gezet.
        if (!weeklyPointsObtained) {
            if (day == 1) {
                point.addPoints(1000);
                userAddPuntMutatie(1000);
                weeklyPointsObtained = true;
            }
        }
        if (day == 0) {
            weeklyPointsObtained = false;
        }
    }

    // Made By BarmanTurbo
    public String vergelijkPuntMetUitstoot(){
        long uitstoot = 1000 - point.getPoints();
        String prefix = "Je CO2 uitstoot komt overeen met ";
        String output = "";
        if (uitstoot >= 122000000){
            output = prefix + (uitstoot / 122000000) + " keer zoveel dan wat de gemeente Amsterdam uitstootte in 2020";
        }
        if (uitstoot < 122000000) {
            output = prefix + "wat de Ever Given uitstoot per " + (uitstoot / 20459) + " km";
        }
        if (uitstoot < 20459) {
            if (uitstoot / 28 == 1) {
               output = prefix + "wat 1 boom per jaar opneemt";
            } else {
               output = prefix + "wat " + (uitstoot / 28) + " bomen per jaar opnemen";
            }
        }
        if (uitstoot < 28) {
            output = prefix + "wat een vrachtwagen uitstoot per " + (uitstoot / 9) + " km";
        }
        if (uitstoot < 9) {
             output = prefix + "wat een vrachtwagen per km uitstoot";
        }
        if (uitstoot <= 1) {
            output = "Je hebt nauwelijks CO2 uitgestoten!";
        }
        return output;
    }

    public boolean checkPassword(String attempt) {
        return attempt.equals(password);
    }

    // Admin methods hieronder
    // made by BarmanTurbo
    public ArrayList<User> duurzaamsteUsers() {
        ArrayList<User> allUsers = Leaderboard.getUsers("");
        ArrayList<User> topUsers = new ArrayList<User>();
        if (this.isAdmin) {
            Integer maxPoints = allUsers.get(0).getPoints();
            for (User u : allUsers) {
                if (u.getPoints() == maxPoints) {
                    topUsers.add(u);
                } else {
                    break; // saves runtime, zodat je alleen over de users met de hoogste punten loopt.
                }
            }
        } else {
            throw new IllegalArgumentException(
                    "Error in User.duurzaamsteUsers: Niet de juiste rechten om dit te bekijken");
        }
        return topUsers;
    }
}
