package com.gui;

import org.junit.jupiter.api.BeforeAll;

/*
 * A abstract class used to enforce a BeforeAll in all GUI tests.<P>
 * Loads JavaFX Runtime 
 */
public abstract class AGUITests {
    
    /**Ensures the JavaFX runtime is loaded before running tests */
    @BeforeAll
    public static void init(){
        new SingleletonToolkit().getInstance().start();
    }
}
