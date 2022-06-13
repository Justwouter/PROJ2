package com.gui;

import java.net.URL;
import java.util.ResourceBundle;

import com.logic.User;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class InstellingenNameController extends AController implements Initializable {

    @FXML
    private TextField gebruikersnaam;

    @FXML
    private Label gebruikersnaamLabel;

    @FXML
    private TextField naam;

    @FXML
    private Label naamLabel;

    @FXML
    public void usernameButton(){
        if(!gebruikersnaam.getText().isBlank()){
            user.username = gebruikersnaam.getText();
            saveManager.saveState();
            gebruikersnaamLabel.setText("Gebruikersnaam aangepast naar " + user.getUsername() + ".");
            gebruikersnaam.clear();
        }
    }

    @FXML
    public void nameButton(){
        if(!naam.getText().isBlank()){
            user.naam = naam.getText();
            saveManager.saveState();
            naamLabel.setText("Naam aangepast naar " + user.getNaam() + ".");
            naam.clear();
        }
    }

    @Override
    public void setUser(User user){
        this.user = user;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
