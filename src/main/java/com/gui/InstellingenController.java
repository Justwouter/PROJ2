package com.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.logic.User;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class InstellingenController extends AController  implements Initializable {

    private User user;

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
    void setUser(User user) {
        // TODO Auto-generated method stub
        
    }

    @Override
    void setPoints(User user) {
        // TODO Auto-generated method stub
        
    }

    @Override
    void setPresets(User user) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}
