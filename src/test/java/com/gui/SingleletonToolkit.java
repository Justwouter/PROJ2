package com.gui;

import javafx.application.Platform;

public class SingleletonToolkit {
    private static SingleletonToolkit instance;

    public SingleletonToolkit getInstance(){
        if (instance == null) {
            instance = new SingleletonToolkit();
        }
        return instance;
    }

    public void start(){
        try{
            Platform.startup(new Runnable() {

                @Override
                public void run() {
                    System.out.println("JavaFX toolkit is running =]");
                }
                
            });
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
}
