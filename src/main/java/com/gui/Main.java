package com.gui;

import com.logic.User;
import com.save.SaveManager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static Stage primaryStage;

    public static void seed(){
        //just here incase users and transportmiddelen gets corrupted
        //in that case copy the setup from the README
        new SaveManager(true).loadAllFiles();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage ps) throws Exception {
        primaryStage = ps;
        primaryStage.getIcons().add(new Image("file:src\\main\\resources\\com\\gui\\Images\\co2wegermeePictogram.png"));
        seed();
        show("login", null);
    }

    public static void show(String fxml, User user) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource(fxml + ".fxml"));
        AnchorPane mainLayout = loader.load();

        AController controller = loader.getController();
        controller.setUser(user);
        if (user != null){
            user.getPoint().getObservableAdapter().addObserver(controller);
        }
        Scene scene = new Scene(mainLayout, 480, 640);
        primaryStage.setScene(scene);
        String c = fxml.substring(0, 1).toUpperCase();
        String title = c + fxml.substring(1);
        primaryStage.setTitle(title);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
