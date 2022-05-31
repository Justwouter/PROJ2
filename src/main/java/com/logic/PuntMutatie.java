package com.logic;

import java.util.Calendar;

public class PuntMutatie{
    public Integer puntVerandering;
    public Calendar datum;

    public PuntMutatie(Integer puntVerandering){
        //geeft datum van vandaag
        this.puntVerandering = puntVerandering;
        datum = Calendar.getInstance();   
    }

    public PuntMutatie(Integer puntVerandering, Calendar datum){
        this.puntVerandering = puntVerandering;
        this.datum = datum;   
    }

    public Calendar getDatum(){
        return this.datum;
    }

    public Integer getPuntVerandering(){
        return this.puntVerandering;
    }

    /**
    * Er hoeft hier niks meegegeven te worden omdat hij de datum opvraagt van het meegegeven object
    */
    public boolean isFromLast4Weeks(){
        Calendar datumCheck = Calendar.getInstance();
        //check for last month date
        if(datumCheck.get(Calendar.YEAR)==datum.get(Calendar.YEAR)){
            if(datumCheck.get(Calendar.DAY_OF_YEAR)-datum.get(Calendar.DAY_OF_YEAR)<28){
                return true;
            } else {
                return false;
            }
        }else{
            if(datum.get(Calendar.DAY_OF_YEAR)-datumCheck.get(Calendar.DAY_OF_YEAR)>328&&datumCheck.get(Calendar.YEAR)-datum.get(Calendar.YEAR)==1){
                //Stel, het is nu 3 januari 2040 en de entry om te checken komtvan 28 december 2039, dan keurt dit het alsnog goed.
                //Zo niet, returnt het false.
                return true;
            }else{
                return false;
            }
        }
    }

    public boolean isFromLastYear(){
        Calendar datumCheck = Calendar.getInstance();
        if(
        (datumCheck.get(Calendar.YEAR)==datum.get(Calendar.YEAR))
        ||
        (datum.get(Calendar.MONTH)-datumCheck.get(Calendar.MONTH)<=12&&datumCheck.get(Calendar.YEAR)-datum.get(Calendar.YEAR)==1)){
            return true;
        }else{
            return false;
        }
    }

}
