package com.gui;

import com.logic.Item;
import com.logic.User;

import javafx.application.Platform;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestShopController {

    ShopController controller = new ShopController();
    User user;
    Item item;

    /**Ensures the JavaFX runtime is loaded before running tests */
    @BeforeAll
    public static void init(){
        try{
            Platform.startup(new Runnable() {

                @Override
                public void run() {
                    System.out.println("Im working =]");
                }
            });
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

   

    @BeforeEach
    public void start(){
      user = new User("Eric", false, "Eric", "Bull", "Rotterdam HQ");
      controller.setUser(user);
    }

    @Test
    public void testPurchaseItem(){
        item = new Item("image", "Radio", 600);
        controller.purchaseItem(item);
        int expected = 400;
        int actual = user.getPoints();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testPurchaseItemJustSuccess(){
        item = new Item("image", "Radio", 1000);
        controller.purchaseItem(item);
        int expected = 0;
        int actual = user.getPoints();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testPurchaseItemJustFailed(){
        item = new Item("image", "Radio", 1001);
        controller.purchaseItem(item);
        int expected = 1000;
        int actual = user.getPoints();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testPurchaseItemFailed(){
        item = new Item("image", "Dure Radio", 2000);
        controller.purchaseItem(item);
        int expected = 1000;
        int actual = user.getPoints();
        Assertions.assertEquals(expected, actual);
    }
}
