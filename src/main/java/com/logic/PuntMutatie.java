package com.logic;

import java.util.Calendar;

public class PuntMutatie{
    public Integer puntVerandering;
    public Integer uitstootCO2;
    public Calendar datum;

    public PuntMutatie(Integer puntVerandering){
        //geeft datum van vandaag
        this.puntVerandering = puntVerandering;
        datum = Calendar.getInstance();   
    }

    public PuntMutatie(Integer puntVerandering, Integer uitstoot){
        this(puntVerandering);
        this.uitstootCO2 = uitstoot;
    }

    public PuntMutatie(Integer puntVerandering, Calendar datum){
        this.puntVerandering = puntVerandering;
        this.datum = datum;   
    }

    public PuntMutatie(Integer puntVerandering, Integer uitstoot, Calendar datum){
        this.puntVerandering = puntVerandering;
        this.datum = datum; 
        this.uitstootCO2 = uitstoot;
    }

    public Calendar getDatum(){
        return this.datum;
    }

    public Integer getPuntVerandering(){
        return this.puntVerandering;
    }
    
    public Integer getCO2(){
        return this.uitstootCO2;
    }

    public boolean isFromLast4Weeks(){
        Calendar datumCheck = Calendar.getInstance();
        //check for last month date
        return (checkDateFourWeeks1(datumCheck) || checkDateFourWeeks2(datumCheck));
    }

    private boolean checkDateFourWeeks1(Calendar datumCheck){
        return (datumCheck.get(Calendar.YEAR)==datum.get(Calendar.YEAR)) && (datumCheck.get(Calendar.DAY_OF_YEAR)-datum.get(Calendar.DAY_OF_YEAR)<28);
    }

    private boolean checkDateFourWeeks2(Calendar datumCheck){
        return (datum.get(Calendar.DAY_OF_YEAR)-datumCheck.get(Calendar.DAY_OF_YEAR)>328 && datumCheck.get(Calendar.YEAR)-datum.get(Calendar.YEAR)==1);
    }

    public boolean isFromLastYear(){ 
        Calendar datumCheck = Calendar.getInstance();
        return (datumCheck.get(Calendar.YEAR)==datum.get(Calendar.YEAR)) || (datum.get(Calendar.MONTH)-datumCheck.get(Calendar.MONTH)<=12 && datumCheck.get(Calendar.YEAR)-datum.get(Calendar.YEAR)==1);
    }
}
