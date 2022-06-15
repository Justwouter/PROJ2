package com.gui;

import com.logic.SaveManagerForTests;
import com.logic.User;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestLoginController extends AGUITests{

    static LoginController controller = new LoginController();

    @BeforeAll
    public static void spoofSaveManager(){
        controller.saveManager = new SaveManagerForTests();
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
