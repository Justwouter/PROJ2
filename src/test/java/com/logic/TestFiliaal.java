package com.logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestFiliaal {

    public Filiaal testFiliaal;

    @BeforeEach
    public void before(){
        testFiliaal = new Filiaal("Amsterdam");
    }    
    
    @Test
    public void testFilialenArray(){
        Filiaal expected = testFiliaal.getFilialen().get(0);
        Filiaal actual = testFiliaal.getFilialen().get(0);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testCheckFiliaalNaam(){
        String expected = "Amsterdam";
        String actual = testFiliaal.getFilialen().get(0).getNaam();
        Assertions.assertEquals(expected, actual);
    }
}
