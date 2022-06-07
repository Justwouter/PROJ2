package com.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.logic.User;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;


public class ShopController extends AController {

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

    @Override
    void setPoints(User user) {
        //points.setText(user.getPoint().getPointsString());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
                
    }
    
}
