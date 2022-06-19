package com.logic;

import javafx.scene.image.ImageView;


public class Item {
    public final String name;
    public int price;

    /**
     * Image mag maar maximaal 100 bij 80 zijn.
     */
    public final String imageURL;


    public Item (String imageURL, String name, int price){
        this.imageURL = imageURL;
        this.name = name;
        this.price = price;

    }

    public String getName(){
        return this.name;
    }

    public int getPrice(){
        return this.price;
    }

    /**
     * Wordt gebruikt bij ShopController voor het ophalen van de afbeelding.
     * @return ImageView
     */
    public ImageView getImage(){
        return new ImageView(imageURL);
    }

    public void setPrice(int price){
        this.price = price;
    }
}
