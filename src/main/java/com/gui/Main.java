package com.gui;

import com.logic.Leaderboard;
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

        public static void initialize(){
        new User("Testnaam Eric").getPoint().setPoints(2000);
        new User("Testnaam Burton").getPoint().setPoints(20);
        new User("Testnaam Damnn...Daniël");
        new User("Wouter").getPoint().setPoints(2500);
        new User("Lucas").getPoint().setPoints(1500);
        new Transportmiddel("Benzine auto", 147, 50);
        new Transportmiddel("Diesel auto", 179, 75);
        new Transportmiddel("Elektrische auto", 87, 25);
        new Transportmiddel("Openbaar Vervoer", 1, 10);
        new Transportmiddel("Fiets/Lopen", 0, 0);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage ps) throws Exception {
        primaryStage = ps;
        primaryStage.getIcons().add(new Image("file:src/main/resources/com/gui/logo.jpg"));
        initialize();
        User user = new User("Main man");
        show("login", user);
    }

    public static void show(String fxml, User user) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource(fxml + ".fxml"));
        mainLayout = loader.load();

        IController controller = loader.getController();
        controller.setUser(user);
        controller.setPoints(user);

        Scene scene = new Scene(mainLayout, 480, 640);
        primaryStage.setScene(scene);
        String c = fxml.substring(0, 1).toUpperCase();
        String title = c + fxml.substring(1);
        primaryStage.setTitle(title);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}


//
//}

//    @Override
//    public void start(Stage stage) throws IOException {
//        scene = new Scene(loadFXML("dashboard"), 480, 640);
//
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(App.class.getResource("dashboard.fxml"));
//        BorderPane mainLayout = loader.load();
//        User user = new User("TestUser");
//        DashController dc = loader.getController();
//        dc.setUser(user);
//
//        stage.setScene(scene);
//        stage.setTitle("PROJ2");
//        stage.getIcons().add(new Image("file:src/main/resources/com/gui/logo.jpg"));
//        stage.setResizable(false);
//        stage.show();
//    }

//    static void setRoot(String fxml) throws IOException {
//        scene.setRoot(loadFXML(fxml));
//    }

//    private static Parent loadFXML(String fxml) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
//        return fxmlLoader.load();
//    }