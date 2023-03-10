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

    /**
    * Er hoeft hier niks meegegeven te worden omdat hij de datum opvraagt van het meegegeven object
    */
    public boolean isFromLast4Weeks(){
        //Burton ik weet niet waar je deze formatting opgepikt hebt, maak het is irritant om te lezen.
        //Split zulke if's uit in kleine methods graag
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
        //Geld ook voor deze. Tf is dit
        Calendar datumCheck = Calendar.getInstance();
        return (datumCheck.get(Calendar.YEAR)==datum.get(Calendar.YEAR)) || (datum.get(Calendar.MONTH)-datumCheck.get(Calendar.MONTH)<=12 && datumCheck.get(Calendar.YEAR)-datum.get(Calendar.YEAR)==1);
    }
}
