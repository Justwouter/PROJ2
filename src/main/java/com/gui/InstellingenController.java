package com.gui;

import java.net.URL;
import java.util.ResourceBundle;

import com.logic.User;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class InstellingenController extends AController implements Initializable {

    @FXML
    private Label name;

    @FXML
    private Label password;

    @FXML
    private Label username;

    public void start(){
        name.setText(user.getUsername());
        username.setText(user.getNaam());
        password.setText(user.getPassword());
    }

    @Override
    public void setUser(User user){
        this.user = user;
        name.setText("value");
        username.setText("value");
        password.setText("value");
        start();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {}
}
