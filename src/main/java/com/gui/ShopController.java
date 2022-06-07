package com.gui;

import java.net.URL;
import java.util.ResourceBundle;

import com.logic.User;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;


public class ShopController extends AController  implements Initializable {

    private User user;

    @FXML
    private Label points;
    
    @Override
    void setUser(User user) {
        this.user = user;
    }

    //just here because of the implementation
    @Override
    void setPoints(User user) {}
    @Override
    void setPresets(User user) {}
    @Override
    public void initialize(URL location, ResourceBundle resources) {}
}
