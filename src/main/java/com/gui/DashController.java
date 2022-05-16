package com.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.logic.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class DashController implements Initializable, IController{


    private User user;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void switchToReisGegevens() throws IOException {
        Main.show("reisgegevens", user);
    }

    @FXML
    private void switchToLeaderboard() throws IOException {
        Main.show("leaderboard", user);
    }

    public void setUser(User u){
        this.user = u;
    }

    @Override
    public void setPoints(User user) {

    }

}
