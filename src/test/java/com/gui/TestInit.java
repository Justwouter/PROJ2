package com.gui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javafx.application.Platform;

public class TestInit {
    /**Ensures the JavaFX runtime is loaded before running tests */
    @BeforeAll
    public static void init(){
        try{
            Platform.startup(new Runnable() {

                @Override
                public void run() {
                    System.out.println("Im working =]");
                }
                
            });
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    

    @Test
    public void testDummey(){
        assertEquals(true, true);
    }
    
}
