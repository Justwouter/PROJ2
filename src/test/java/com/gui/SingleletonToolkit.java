package com.gui;

import javafx.application.Platform;

public class SingleletonToolkit {
    private static SingleletonToolkit instance;
    boolean isRunning = false;

    public SingleletonToolkit getInstance(){
        if (instance == null) {
            instance = new SingleletonToolkit();
        }
        return instance;
    }

    public void start(){
        if(!isRunning){
            try{
                Platform.startup(new Runnable() {
    
                    @Override
                    public void run() {
                        System.out.println("JavaFX toolkit is running =]");
                    }
                    
                });
                isRunning = true;
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
    }
}
