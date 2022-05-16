package com.gui;

import com.logic.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private User user;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user = new User("Dashboard User");
    }

    @FXML
    public void switchToDashboard() throws IOException {
        Main.showDashView(user);
    }
}
