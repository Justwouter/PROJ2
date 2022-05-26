package com.logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestPoint {
    private User user;

    @BeforeEach
    public void before(){
        user = new User("Testnaam", "Username", "Password");
    }

    // Test of de punten goed toegevoegd worden.
    @Test
    public void testAddPoints(){
        int expected = 1050;
        user.getPoint().addPoints(50);
        int actual = user.getPoints();
        Assertions.assertEquals(expected,actual);
    }

    // Test of de punten goed afgetrokken worden.
    @Test
    public void testSubtractPoints(){
        int expected = 950;
        user.getPoint().subtractPoints(50);
        int actual = user.getPoints();
        Assertions.assertEquals(expected, actual);
    }

    // Test of de punten juist opgehaald worden als String.
    @Test
    public void testGetPointsValue(){
        String expected = "1000";
        String actual = user.getPoint().getPointsString();
        Assertions.assertEquals(expected, actual);

    }
}
