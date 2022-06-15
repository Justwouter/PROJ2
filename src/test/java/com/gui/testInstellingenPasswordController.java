package com.gui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.logic.User;
import com.save.SaveManagerForTests;

public class testInstellingenPasswordController {

    static User testUser;
    static InstellingenPasswordController controller = new InstellingenPasswordController();

    @BeforeAll
    public static void spoofSaveManager(){
        controller.saveManager = new SaveManagerForTests();
    }

    @BeforeAll
    public static void start(){
        testUser = new User("TestGebruiker", false, "Username", "Password", null);
    }    

    @Test
    public void testCheckPasswordCorrect() {
        boolean test = controller.checkPasswordCorrect(testUser.getPassword(), "Password");
        boolean test2 = controller.checkPasswordCorrect(testUser.getPassword(), "FoutPassword");
        assertEquals(true, test);
        assertEquals(false, test2);
    }

    @Test
    public void testCheckPasswordDubbel() {
        boolean test = controller.checkPasswordDubbel("Password", "Password");
        boolean test2 = controller.checkPasswordDubbel("Password", "FoutPassword");
        assertEquals(true, test);
        assertEquals(false, test2);
    }
}
