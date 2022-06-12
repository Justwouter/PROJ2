package com.gui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.logic.User;

public class testInstellingenController {

    User testUser;
    InstellingenController testController = new InstellingenController();

    @BeforeAll
    public void start(){
        testUser = new User("TestGebruiker", false, "Username", "Password", null);
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
