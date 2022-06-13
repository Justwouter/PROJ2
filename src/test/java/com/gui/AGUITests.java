package com.gui;

import org.junit.jupiter.api.BeforeAll;

import javafx.application.Platform;

/*
 * A abstract class used to enforce a BeforeAll in all GUI tests.<P>
 * Loads JavaFX Runtime 
 */
public abstract class AGUITests {

    /**Ensures the JavaFX runtime is loaded before running tests */
    @BeforeAll
    public static void init(){
        try{
            Platform.startup(new Runnable() {

                @Override
                public void run() {
                    System.out.println("JavaFX toolkit is running =]");
                }
                
            });
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
}
