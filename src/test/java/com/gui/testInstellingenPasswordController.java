package com.gui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import com.logic.User;

public class testInstellingenPasswordController {

    static User testUser;
    InstellingenPasswordController testController = new InstellingenPasswordController();

    @BeforeAll
    public static void start(){
        testUser = new User("TestGebruiker", false, "Username", "Password", null);
    }    
    @AfterAll
    public static void end(){
        //idfk of userList moet public of er moet een methode voor de clear komen wat jullie willen
    }
    @AfterAll
    public static void end(){
        //idfk of userList moet public of er moet een methode voor de clear komen wat jullie willen
    }

    @Test
    public void testCheckPasswordCorrect() {
        boolean test = testController.checkPasswordCorrect(testUser.getPassword(), "Password");
        boolean test2 = testController.checkPasswordCorrect(testUser.getPassword(), "FoutPassword");
        assertEquals(true, test);
        assertEquals(false, test2);
    }

    @Test
    public void testCheckPasswordDubbel() {
        boolean test = testController.checkPasswordDubbel("Password", "Password");
        boolean test2 = testController.checkPasswordDubbel("Password", "FoutPassword");
        assertEquals(true, test);
        assertEquals(false, test2);
    }
}
