package com.gui;

import com.logic.User;

import javafx.application.Platform;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestLoginController{

    LoginController controller = new LoginController();

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

    


    @BeforeAll
    public static void seed(){
        new User("Test Eric", false, "Eric", "Bull", null);
    }

    @Test
    public void testLoginCheckerTrue() throws IOException {
        String username = "Eric";
        String password = "Bull";
        Assertions.assertTrue(controller.loginChecker(username, password));
    }

    @Test
    public void testLoginCheckerFalsePassword() throws IOException {
        String username = "Eric";
        String password = "Stier";
        Assertions.assertFalse(controller.loginChecker(username, password));
    }

    @Test
    public void testLoginCheckerFalseUsername() throws IOException {
        String username = "Erik";
        String password = "Bull";
       Assertions.assertFalse(controller.loginChecker(username, password));
    }


}
