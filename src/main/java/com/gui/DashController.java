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

public class DashController implements Initializable {

    @FXML
    private User user;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    private void switchToReisGegevens() throws IOException {
        Main.showRankView();
    }

    public void setUser(User user){
        this.user = user;
    }
}
