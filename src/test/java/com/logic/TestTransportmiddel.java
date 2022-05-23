package com.logic;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestTransportmiddel {


    // Maakt de arraylist leeg zodat nieuwe tests niet tegen elkaar ingaan.
    @AfterEach
    public void exit(){
        Transportmiddel.getTransportmiddelen().clear();
    }

    // Test of een nieuw transportmiddel goed wordt toegevoegd aan de arraylist.
    @Test
    public void testTransportmiddelArray(){
        Transportmiddel expected = new Transportmiddel("Naam", 100, 200);
        Transportmiddel actual = Transportmiddel.getTransportmiddelen().get(0);
        Assertions.assertEquals(expected,actual);
    }

}
