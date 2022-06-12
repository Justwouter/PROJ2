package com.gui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.logic.User;

public class testInstellingenController {

    static User testUser;
    InstellingenController testController = new InstellingenController();


    @BeforeAll
    public static void start(){
        testUser = new User("TestGebruiker", false, "Username", "Password", null);
    }    

    @Test
    public void testPasswordSet() {
        assertEquals("", testController.passwordSet(""));
        assertEquals("xxxx", testController.passwordSet("test"));
        assertEquals("xxxxxxx", testController.passwordSet(testUser.getPassword()));
    }
}
