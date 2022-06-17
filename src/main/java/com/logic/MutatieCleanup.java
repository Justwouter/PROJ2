package com.logic;

import java.util.Calendar;

public abstract class MutatieCleanup {
    private MutatieCleanup(){}
    
    protected static void userMonthlyPuntMutatieCleanup(){
        for (User u : Leaderboard.getUsers("")){
            for (PuntMutatie pm : u.puntVerandering){
                if(!pm.isFromLastYear()){
                    u.userMonthlyPointStorage.remove(u.puntVerandering.indexOf(pm));
                }
            }
        }
    }

    protected static void user4weekPuntMutatieCleanUp() {
        // Zorgt ervoor dat alleen puntmutaties van de laatste 4 weken opgeslagen blijven.
        Integer puntenOuderDan4Weken = 0;
        Integer Co2 = 0;
        int monthOfPuntenOuderDan4Weken = 13;
        for (User u: Leaderboard.getUsers("")){
            for (PuntMutatie pm : u.puntVerandering) {
                if (!pm.isFromLast4Weeks()) {
                    puntenOuderDan4Weken += pm.getPuntVerandering();
                    Co2 = pm.getCO2();
                    monthOfPuntenOuderDan4Weken = pm.getDatum().get(Calendar.MONTH);
                    u.puntVerandering.remove(u.puntVerandering.indexOf(pm));
                }
            }
        
            if(monthOfPuntenOuderDan4Weken<13){
                u.monthlyPuntStorageAdd(puntenOuderDan4Weken,Co2, monthOfPuntenOuderDan4Weken);
            }
        }
    }

    public static void userPuntMutatieCleanupALLES(){
        user4weekPuntMutatieCleanUp();
        userMonthlyPuntMutatieCleanup();
    }
}
