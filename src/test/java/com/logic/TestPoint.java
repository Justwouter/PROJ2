package com.logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestPoint {
    private User user;

    @BeforeEach
    public void before(){
        user = new User("Testnaam", false, "Username", "Password", null);
    }

    // Test of de punten goed toegevoegd worden.
    @Test
    public void testAddPoints(){
        int expected = 1050;
        user.getPoint().addPoints(50);
        int actual = user.getPoints();
        Assertions.assertEquals(expected,actual);
    }

    // Test of de punten juist opgehaald worden als String.
    @Test
    public void testGetPointsValue(){
        String expected = "1000";
        String actual = user.getPoint().getPointsString();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testEnoughBalanceTrue(){
        int price = 100;
        Assertions.assertTrue(user.getPoint().enoughBalance(price));
    }

    @Test
    public void testEnoughBalanceBarelyTrue(){
        int price = 1000;
        Assertions.assertTrue(user.getPoint().enoughBalance(price));
    }

    @Test
    public void testEnoughBalanceBarelyFalse(){
        int price = 1001;
        Assertions.assertFalse(user.getPoint().enoughBalance(price));
    }

    @Test
    public void testEnoughBalanceFalse(){
        int price = 5000;
        Assertions.assertFalse(user.getPoint().enoughBalance(price));
    }
}
