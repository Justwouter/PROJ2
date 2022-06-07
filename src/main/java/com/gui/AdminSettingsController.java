package com.gui;

import com.logic.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminSettingsController extends AController {

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

    @FXML //TODO: kan iemand die goed is in fxml hier ff extra naar kijken?!
    public void switchToAdminActions() throws IOException{
        Main.show("adminaction", user);
    } //er moet dus nog een adminaction.fxml komen

    @Override
    void setPoints(User user) {
        
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}
