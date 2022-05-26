package com.gui;

import com.logic.Reis;
import com.logic.Transportmiddel;
import com.logic.User;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static Stage primaryStage;
    private static AnchorPane mainLayout;

        public static User seed(){
        new User("Testnaam Eric", "Eric", "Bull").getPoint().setPoints(2000);
        new User("Testnaam Burton", "Burton","Braam").getPoint().setPoints(20);
        new User("Testnaam Damnn...Daniël", "Wessel", "Horsthuis");
        new User("Testnaam Wouter", "Wouter", "Swijnenburg").getPoint().setPoints(2500);
        new User("Testnaam Lucas", "Lucas", "Clavel").getPoint().setPoints(1500);
        new Transportmiddel("Benzine auto", 147, 50);       //147 g/km
        new Transportmiddel("Diesel auto", 179, 75);        //179 g/km
        new Transportmiddel("Elektrische auto", 87, 25);    //87 g/km
        new Transportmiddel("Openbaar Vervoer", 50, 10);    //50 g/km
        new Transportmiddel("Fiets/Lopen", 5, 0);           //5

        //voorbeeld inlog
//        User user = new User("Main man");
//        user.setReis(0, new Reis("Thuis", Transportmiddel.getTransportmiddelen().get(1), 25));
//        user.setReis(1, new Reis("Werk", Transportmiddel.getTransportmiddelen().get(3), 50));
//        user.setReis(2, new Reis("School", Transportmiddel.getTransportmiddelen().get(4), 75));
//        user.setReis(3, new Reis(null, null, null));
//        user.setReis(4, new Reis(null, null, null));
        return null;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage ps) throws Exception {
        primaryStage = ps;
        primaryStage.getIcons().add(new Image("file:src/main/resources/com/gui/Images/logo.jpg"));
        User user = seed();
        show("login", user);
    }

    public static void show(String fxml, User user) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource(fxml + ".fxml"));
        mainLayout = loader.load();

        IController controller = loader.getController();
        controller.setUser(user);
        controller.setPoints(user);
        controller.setPresets(user);

        Scene scene = new Scene(mainLayout, 480, 640);
        primaryStage.setScene(scene);
        String c = fxml.substring(0, 1).toUpperCase();
        String title = c + fxml.substring(1);
        primaryStage.setTitle(title);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}