package com.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestUser {
    
    User user;
    
    @BeforeEach
    public void seed(){
        user = new User("TestGebruiker", "Username", "Password");
    }
    
    @Test
    public void testVergelijkPuntMetUitstoot1(){
        //arrange
        user.getPoint().setPoints(1000);
        //act
        String expected = "Je hebt nauwelijks CO2 uitgestoten!";
        String actual = user.vergelijkPuntMetUitstoot();
        //assert
        assertEquals(expected,actual);
    }

    @Test
    public void testVergelijkPuntMetUitstoot2(){
        //arrange
        user.getPoint().setPoints(992);
        //act
        String expected = "Je CO2 uitstoot komt overeen met wat een vrachtwagen per km uitstoot";
        String actual = user.vergelijkPuntMetUitstoot();
        //assert
        assertEquals(expected,actual);
    }

    @Test
    public void testVergelijkPuntMetUitstoot3(){
        //arrange
        user.getPoint().setPoints(991);
        //act
        String expected = "Je CO2 uitstoot komt overeen met wat een vrachtwagen uitstoot per 1 km";
        String actual = user.vergelijkPuntMetUitstoot();
        //assert
        assertEquals(expected,actual);
    }

    @Test
    public void testVergelijkPuntMetUitstoot4(){
        //arrange
        user.getPoint().setPoints(972);
        //act
        String expected = "Je CO2 uitstoot komt overeen met wat 1 boom per jaar opneemt";
        String actual = user.vergelijkPuntMetUitstoot();
        //assert
        assertEquals(expected,actual);
    }
    @Test
    public void testVergelijkPuntMetUitstoot5(){
        //arrange
        user.getPoint().setPoints(973);
        //act
        String expected = "Je CO2 uitstoot komt overeen met wat een vrachtwagen uitstoot per 3 km";
        String actual = user.vergelijkPuntMetUitstoot();
        //assert
        assertEquals(expected,actual);
    }
    @Test
    public void testVergelijkPuntMetUitstoot6(){
        //arrange
        user.getPoint().setPoints(1000-20459);
        //act
        String expected = "Je CO2 uitstoot komt overeen met wat de Ever Given uitstoot per 1 km";
        String actual = user.vergelijkPuntMetUitstoot();
        //assert
        assertEquals(expected,actual);
    }
    @Test
    public void testVergelijkPuntMetUitstoot7(){
        //arrange
        user.getPoint().setPoints(1000-20458);
        //act
        String expected = "Je CO2 uitstoot komt overeen met wat 730 bomen per jaar opnemen";
        String actual = user.vergelijkPuntMetUitstoot();
        //assert
        assertEquals(expected,actual);
    }
    @Test
    public void testVergelijkPuntMetUitstoot8(){
        //arrange
        user.getPoint().setPoints(1000-122000000);
        //act
        String expected = "Je CO2 uitstoot komt overeen met 1 keer zoveel dan wat de gemeente Amsterdam uitstootte in 2020";
        String actual = user.vergelijkPuntMetUitstoot();
        //assert
        assertEquals(expected,actual);
    }

    @Test
    public void testVergelijkPuntMetUitstoot9(){
        //arrange
        user.getPoint().setPoints(1000-121999999);
        //act
        String expected = "Je CO2 uitstoot komt overeen met wat de Ever Given uitstoot per 5963 km";
        String actual = user.vergelijkPuntMetUitstoot();
        //assert
        assertEquals(expected,actual);
    }

    @Test
    public void testCheckPasswordCorrect(){
        String attempt = "Password";
        Assertions.assertTrue(user.checkPassword(attempt));
    }

    @Test
    public void testCheckPasswordIncorrect(){
        String attempt = "Not Correct";
        Assertions.assertFalse(user.checkPassword(attempt));
    }
}
