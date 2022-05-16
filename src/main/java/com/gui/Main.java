package com.gui;

import com.logic.Transportmiddel;
import com.logic.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static Stage primaryStage;
    private static AnchorPane mainLayout;

        public static void initialize(){
        User user = new User("Testnaam Eric");
        user.getPoint().setPoints(20000);
        user = new User("Testnaam Burton");
        user.getPoint().setPoints(20);
        new User("Testnaam Damnn...Daniël");
        new Transportmiddel("Benzine auto", 200, 50);
        new Transportmiddel("Diesel auto", 250, 75);
        new Transportmiddel("Electrische auto", 100, 25);
        new Transportmiddel("Openbaar Vervoer", 50, 10);
        new Transportmiddel("Fiets/Lopen", 0, 0);
    }

    public static void main(String[] args) {
        initialize();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        showLoginView();
    }

    public static void show(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource(fxml + ".fxml"));
        mainLayout = loader.load();
        Scene scene = new Scene(mainLayout, 480, 640);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void showLoginView() throws IOException {
        Main.show("login");
    }

    public static void showRankView() throws IOException {
        Main.show("leaderboard");
    }

    public static void showDashView(User user) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("dashboard.fxml"));
        mainLayout = loader.load();

        DashController dc = loader.getController();
        dc.setUser(user);

        Scene scene = new Scene(mainLayout, 480, 640);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void showReisGegevens(User user) throws IOException { // Taking the user-object as an argument from LoginViewController
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("reisgegevens.fxml"));
        mainLayout = loader.load();

        ReisGegevensController cvc = loader.getController(); // This did the "trick"
        cvc.setUser(user); // Passing the user-object to the ClientViewController
        cvc.setPointValue(user.getPoint().getPointsValue()); // Setting pointvalue

        Scene scene = new Scene(mainLayout, 480, 640);
        primaryStage.setScene(scene);
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