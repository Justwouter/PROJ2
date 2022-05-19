package com.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class testUser {
    
    @Test
    public void testVergelijkPuntMetUitstoot1(){
        //arrange
        User Albert = new User("Albert");
        Albert.getPoint().setPoints(1000);
        //act
        String expected = "Je hebt nauwelijks CO2 uitgestoten!";
        String actual = Albert.vergelijkPuntMetUitstoot();
        //assert
        assertEquals(expected,actual);
    }

    @Test
    public void testVergelijkPuntMetUitstoot2(){
        //arrange
        User Dirk = new User("Dirk");
        Dirk.getPoint().setPoints(992);
        //act
        String expected = "Je CO2 uitstoot komt overeen met wat een vrachtwagen per km uitstoot";
        String actual = Dirk.vergelijkPuntMetUitstoot();
        //assert
        assertEquals(expected,actual);
    }

    @Test
    public void testVergelijkPuntMetUitstoot3(){
        //arrange
        User Jumbo = new User("Jumbo");
        Jumbo.getPoint().setPoints(991);
        //act
        String expected = "Je CO2 uitstoot komt overeen met wat een vrachtwagen uitstoot per 1 km";
        String actual = Jumbo.vergelijkPuntMetUitstoot();
        //assert
        assertEquals(expected,actual);
    }

    @Test
    public void testVergelijkPuntMetUitstoot4(){
        //arrange
        User Lidl = new User("Lidl");
        Lidl.getPoint().setPoints(972);
        //act
        String expected = "Je CO2 uitstoot komt overeen met wat 1 bomen per jaar opnemen";
        String actual = Lidl.vergelijkPuntMetUitstoot();
        //assert
        assertEquals(expected,actual);
    }
    @Test
    public void testVergelijkPuntMetUitstoot5(){
        //arrange
        User C1000 = new User("C1000");
        C1000.getPoint().setPoints(973);
        //act
        String expected = "Je CO2 uitstoot komt overeen met wat een vrachtwagen uitstoot per 3 km";
        String actual = C1000.vergelijkPuntMetUitstoot();
        //assert
        assertEquals(expected,actual);
    }
    @Test
    public void testVergelijkPuntMetUitstoot6(){
        //arrange
        User spar = new User("spar");
        spar.getPoint().setPoints(1000-20459);
        //act
        String expected = "Je CO2 uitstoot komt overeen met wat de Ever Given uitstoot per 1 km";
        String actual = spar.vergelijkPuntMetUitstoot();
        //assert
        assertEquals(expected,actual);
    }
    @Test
    public void testVergelijkPuntMetUitstoot7(){
        //arrange
        User superdeboer = new User("superdeboer");
        superdeboer.getPoint().setPoints(1000-20458);
        //act
        String expected = "Je CO2 uitstoot komt overeen met wat 730 bomen per jaar opnemen";
        String actual = superdeboer.vergelijkPuntMetUitstoot();
        //assert
        assertEquals(expected,actual);
    }
    @Test
    public void testVergelijkPuntMetUitstoot8(){
        //arrange
        User konmar = new User("konmar");
        konmar.getPoint().setPoints(1000-122000000);
        //act
        String expected = "Je CO2 uitstoot komt overeen met 1 keer zoveel dan wat de gemeente Amsterdam uitstootte in 2020";
        String actual = konmar.vergelijkPuntMetUitstoot();
        //assert
        assertEquals(expected,actual);
    }

    @Test
    public void testVergelijkPuntMetUitstoot9(){
        //arrange
        User hoogvliet = new User("hoogvliet");
        hoogvliet.getPoint().setPoints(1000-121999999);
        //act
        String expected = "Je CO2 uitstoot komt overeen met wat de Ever Given uitstoot per 5963 km";
        String actual = hoogvliet.vergelijkPuntMetUitstoot();
        //assert
        assertEquals(expected,actual);
    }
}
