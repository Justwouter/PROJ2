package com.logic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

public class User {

    // public omdat het dan gelezen en veranderd kan worden in JSon
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

    public User(String naam, String username, String password) {
        this(naam, false, username, password);
    }

    public User(String naam, boolean isAdmin, String username, String password) {
        this.naam = naam;
        this.username = username;
        this.password = password;
        point.setPoints(1000);
        Leaderboard.addUser(this);
        for (int i = 0; i < 5; i++) {
            PreSets.add(new Reis(null, null, null));
        }
    }

    public User(String naam, Point point) {
        this.naam = naam;
        this.point = point;
    }

    public void setReis(int index, Reis reis) {
        PreSets.set(index, reis);
    }

    public ArrayList<Reis> getReizen() {
        return PreSets;
    }

    public Reis getReis(int index) {
        return PreSets.get(index);
    }

    public String getNaam() {
        return naam;
    }

    public Integer getPoints() {
        return point.getPoints();
    }

    public Point getPoint() {
        return point;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getRank() {
        return this.rank;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public String getUsername() {
        return username;
    }

    public void userAddPuntMutatie(int amount) {
        puntVerandering.add(new PuntMutatie(amount));
    }

    public void userAddPuntMutatie(int amount, Calendar datum) {
        puntVerandering.add(new PuntMutatie(amount, datum));
    }

    public void userPuntMutatieCleanUp() {
        // Zorgt ervoor dat alleen puntmutaties van de laatste 4 weken opgeslagen
        // blijven.
        for (PuntMutatie pm : puntVerandering) {
            if (!pm.isFromLast4Weeks()) {
                puntVerandering.remove(puntVerandering.indexOf(pm));
            }
        }
    }

    public ArrayList<PuntMutatie> getUserPuntMutaties() {
        userPuntMutatieCleanUp();
        return this.puntVerandering;
    }

    public Integer getUserPuntMutatiesAsInteger() {
        userPuntMutatieCleanUp();
        Integer addedMutations = 0;
        for (PuntMutatie pm : puntVerandering) {
            addedMutations += pm.getPuntVerandering();
        }
        return addedMutations;
    }

    // made by BarmanTurbo
    protected void addWeeklyPoints() {
        c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_WEEK);
        // Elke maandag krijg je 1000 punten mits je inlogt. Dat is maar één keer in de week.
        // Daarna wordt boolean weeklyPointsObtained op True gezet zodat je niet nog een keer
        // de punten kan krijgen. Op zondag wordt je weeklyPoitnsObtained op false gezet.
        if (!weeklyPointsObtained) {
            if (day == 1) {         
                point.addPoints(1000);
                weeklyPointsObtained = true;
            }
        }

        if (day == 0) {
            weeklyPointsObtained = false;
        }
    }

    // Made By BarmanTurbo
    public String vergelijkPuntMetUitstoot() {
        long uitstoot = 1000 - point.getPoints();
        if (uitstoot <= 1) {
            return "Je hebt nauwelijks CO2 uitgestoten!";
        }
        String output = "Je CO2 uitstoot komt overeen met ";
        if (uitstoot < 9) {
            output += "wat een vrachtwagen per km uitstoot";
            return output;
        }
        if (uitstoot < 28) {
            output += "wat een vrachtwagen uitstoot per " + (uitstoot / 9) + " km";
            return output;
        }
        if (uitstoot < 20459) {
            if (uitstoot / 28 == 1) {
                output += "wat 1 boom per jaar opneemt";
            } else {
                output += "wat " + (uitstoot / 28) + " bomen per jaar opnemen";
            }
            return output;
        }
        if (uitstoot < 122000000) {
            output += "wat de Ever Given uitstoot per " + (uitstoot / 20459) + " km";
        } else {
            output += "" + (uitstoot / 122000000) + " keer zoveel dan wat de gemeente Amsterdam uitstootte in 2020";
        }
        return output;
    }

    public boolean checkPassword(String attempt) {
        return attempt.equals(password);
    }

    // Admin methods hieronder
    // made by BarmanTurbo
    public ArrayList<User> duurzaamsteUsers() {
        ArrayList<User> allUsers = Leaderboard.getUsers();
        ArrayList<User> topUsers = new ArrayList<User>();
        if (getIsAdmin()) {
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

    
    // made by BarmanTurbo
    public Comparator<User> BesteUsersVanDeMaand() {
        if(isAdmin){
            ArrayList<User> allUsers = Leaderboard.getUsers();
            Comparator<User> vergelijker = Comparator.comparing(User::getUserPuntMutatiesAsInteger);
            Collections.sort(allUsers, vergelijker);
            return vergelijker;
        } else{
            throw new IllegalArgumentException(
                "Error in User.duurzaamsteUsers: Niet de juiste rechten om dit te bekijken");
        }
    }

}
