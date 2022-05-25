package com.logic;

import java.util.Calendar;

class PuntMutatie{
    private Integer puntVerandering;
    private Calendar datum;

    public PuntMutatie(Integer puntVerandering, Integer puntTotaal){
        this.puntVerandering = puntVerandering;
        datum = Calendar.getInstance();   
    }

    public Calendar getDatum(){
        return this.datum;
    }

    public Integer getPuntVerandering(){
        return this.puntVerandering;
    }

}
