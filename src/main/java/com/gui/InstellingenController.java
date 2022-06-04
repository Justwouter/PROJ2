package com.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.logic.User;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class InstellingenController extends AController implements Initializable {

    private User user;

    @FXML
    private Label points;

    @FXML
    private void switchToReisGegevens() throws IOException {
        Main.show("reisgegevens", user);
    }

    @FXML
    private void switchToLeaderboard() throws IOException {
        Main.show("leaderboard", user);
    }

    @FXML
    public void switchToDashboard() throws IOException {
        Main.show("dashboard", user);
    }

    @FXML
    public void switchToInstellingen() throws IOException {
        Main.show("instellingen", user);
    }

    @FXML
    public void switchToShop() throws IOException {
        Main.show("shop", user);
    }
    //hier staam de filialen al bij
    //TODO moet nog weg is tijdelijk voor functie testen
    @FXML
    public void makeUser() throws IOException {
       new User("Test", false, "Test", "Horsthuis"/*, "Den Haag"*/);
    }
    //TODO moet nog weg is tijdelijk voor functie testen
    @FXML
    public void makeAdmin() throws IOException {
       new User("Test", true, "Test", "Horsthuis"/*, "Amsterdam"*/);
    }
    
    @Override
    void setUser(User user) {
        this.user = user;
    }

    @Override
    void setPoints(User user) {
        
    }

    @Override
    void setPresets(User user) {
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}
