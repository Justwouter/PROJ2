package com.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestPuntMutatie {
    PuntMutatie testPunten;

    @BeforeEach
    public void start(){
        testPunten = new PuntMutatie(10, Calendar.getInstance());
    }

    @Test
    public void testGetDatum() {
        Calendar datum = Calendar.getInstance(); 
        assertEquals(datum.get(Calendar.DAY_OF_YEAR), testPunten.getDatum().get(Calendar.DAY_OF_YEAR));
    }

    @Test
    public void testGetPuntVerandering() {
        assertEquals(10, testPunten.getPuntVerandering());
    }

    @Test
    public void testIsFromLast4Weeks() {
        assertEquals(true, testPunten.isFromLast4Weeks());
        //assertNotEquals(true, testPunten.isFromLast4Weeks());
    }
}
