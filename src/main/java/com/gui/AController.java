package com.gui;

import java.io.IOException;

import com.logic.User;

import javafx.fxml.FXML;

public abstract class AController {

    public User user;

    abstract void setUser(User user);
    abstract void setPoints(User user);
    abstract void setPresets(User user);

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
    
    //idk what this is
    @FXML //TODO: kan iemand die goed is in fxml hier ff extra naar kijken?!
    public void switchToAdminActions() throws IOException{
        Main.show("adminaction", user);
    } //er moet dus nog een adminaction.fxml komen
}
