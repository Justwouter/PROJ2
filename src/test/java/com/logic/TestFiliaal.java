package com.logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestFiliaal {

    @BeforeEach
    public void before(){
    }    
    
    @Test
    public void testFilialenArray(){
        Filiaal expected = new Filiaal("Amsterdam");
        Filiaal actual = Filiaal.getFilialen().get(0);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void TestCheckFiliaal(){

    }
}
