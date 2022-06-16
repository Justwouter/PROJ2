package com.gui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.logic.User;
import com.save.SaveManagerForTests;

public class TestInstellingenController extends AGUITests{

    static User testUser;
    static InstellingenController controller = new InstellingenController();

    @BeforeAll
    public static void spoofSaveManager(){
        controller.saveManager = new SaveManagerForTests();
    }


    @BeforeAll
    public static void start(){
        testUser = new User("TestGebruiker", false, "Username", "Password", null);
    }    

    @Test
    public void testPasswordSet() {
        assertEquals("", controller.passwordSet(""));
        assertEquals("xxxx", controller.passwordSet("test"));
        assertEquals("xxxxxxxx", controller.passwordSet(testUser.getPassword()));
    }
}