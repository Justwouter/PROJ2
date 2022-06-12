package com.gui;

import java.net.URL;
import java.util.ResourceBundle;

import com.logic.SaveManager;
import com.logic.User;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class InstellingenNameController extends AController implements Initializable {

    private SaveManager saveManager = new SaveManager();

    @FXML
    private TextField gebruikersnaam;

    @FXML
    private Label gebruikersnaamLabel;

    @FXML
    private TextField naam;

    @FXML
    private Label naamLabel;

    //its a bit weird because username is actually the name
    @FXML
    public void usernameButton(){
        if(!gebruikersnaam.getText().isBlank()){
            user.naam = gebruikersnaam.getText();
            saveManager.saveState();
            gebruikersnaamLabel.setText("Gebruikersnaam aangepast naar " + user.getNaam() + ".");
            gebruikersnaam.clear();
        }
    }
    //its a bit weird because name is actually the username
    @FXML
    public void nameButton(){
        if(!naam.getText().isBlank()){
            user.username = naam.getText();
            saveManager.saveState();
            naamLabel.setText("Naam aangepast naar " + user.getUsername() + ".");
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
