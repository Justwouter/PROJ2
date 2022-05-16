package com.gui;

import com.logic.Point;
import com.logic.Transportmiddel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
/*
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
*/
import java.io.IOException;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("dashboard"), 480, 640);
        stage.setScene(scene);
        stage.setTitle("PROJ2");
        stage.getIcons().add(new Image("file:src/main/resources/com/gui/logo.jpg"));
        stage.setResizable(false);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        start();
        launch();
    }

    public static void start(){
        Point.setPoints(500);
        new Transportmiddel("Benzine auto", 200, 50);
        new Transportmiddel("Diesel auto", 250, 75);
        new Transportmiddel("Electrische auto", 100, 25);
        new Transportmiddel("Openbaar Vervoer", 50, 10);
        new Transportmiddel("Fiets/Lopen", 0, 0);
    }

}