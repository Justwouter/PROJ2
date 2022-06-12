package com.gui;

import java.net.URL;
import java.util.ResourceBundle;

import com.logic.User;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class InstellingenController extends AController implements Initializable {

    boolean passwordHide = true;

    @FXML
    private Label name;

    @FXML
    private Label password;

    @FXML
    private Label username;

    @FXML
    void showPassword() {
        if (passwordHide){
            password.setText(passwordSet(user.getPassword()));
            passwordHide = false;
        }else{
            password.setText(user.getPassword());
            passwordHide = true;
        }
    }

    //sets the right values according to the given user
    private void start(){
        name.setText(user.getUsername());
        username.setText(user.getNaam());
        password.setText(passwordSet(user.getPassword()));
    }

    public String passwordSet(String password) {
        String encryptedpassword = "";
        for (int i = 0; i < password.length(); i++){
            encryptedpassword += "x";
        }
        return encryptedpassword;
    }

    @Override
    public void setUser(User user){
        this.user = user;
        //labels need a value before they can be changed idk why
        name.setText("value");
        username.setText("value");
        password.setText("value");
        start();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {}
}
