package com.gui;

import com.logic.User;
import com.save.ISave;
import com.save.SaveManager;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

/*
 * Hoofd controller, wordt gebruikt om alle switch statements te implementeren.
 * De setUser, setPoint zijn er zodat elke controller een vaste start method heeft.
 */
@SuppressWarnings("deprecation")
public abstract class AController implements Observer {

    @FXML
    public Label points = new Label();

    public ISave saveManager = new SaveManager(true);

    public User user;

    public void setUser(User user){
        this.user = user;
    }

    //All switch statements (if you want a new fxml file add the switch method here!)
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
    @FXML 
    public void switchToAdmin() throws IOException{
        Main.show("admin", user);
    }
    public void switchToAdminUserAdd() throws IOException {
        Main.show("adminUserAdd", user);
    }
    @FXML 
    public void switchToInstellingenPassword() throws IOException{
        Main.show("instellingenPassword", user);
    }
    @FXML 
    public void switchToInstellingenName() throws IOException{
        Main.show("instellingenName", user);
    } 
    @FXML
    private void switchToLogin() throws IOException {
        Main.show("login", user);
    }

    @Override
    public void update(Observable o, Object arg) {
        points.setText(user.getPoint().getPointsString());
    }

}