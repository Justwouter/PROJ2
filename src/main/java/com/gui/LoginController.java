package com.gui;

import com.logic.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable, IController {

    private User user;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    
    }

    @FXML
    public void switchToDashboard() throws IOException {
        Main.show("dashboard", user);
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void setPoints(User user) {

    }

    @Override
    public void setPresets(User user){
        
    }
}
