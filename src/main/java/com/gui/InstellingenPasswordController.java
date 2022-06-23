package com.gui;

import java.net.URL;
import java.util.ResourceBundle;

import com.logic.User;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class InstellingenPasswordController extends AController implements Initializable {

    @FXML
    private Label ErrorLabel;

    @FXML
    private TextField HuidigeWW;

    @FXML
    private TextField NieuweWW;

    @FXML
    private TextField HerhalingWW;

    @FXML
    private void setLabel(){
        if (!checkPasswordDubbel(NieuweWW.getText(), HerhalingWW.getText()) || !checkPasswordCorrect(HuidigeWW.getText(), user.getPassword())){
            ErrorLabel.setText("Wachtwoorden komen niet overeen.");
        }
        else{
            ErrorLabel.setText("Het nieuw wachtwoord is hetzelfde als het oude wachtwoord");
        }
    }

    @FXML
    private void passwordButton(){
        String huidig = HuidigeWW.getText();
        String nieuw = NieuweWW.getText();
        String herhaling = HerhalingWW.getText();  
        if (checkPasswordDubbel(nieuw, herhaling) && checkPasswordCorrect(huidig, user.getPassword()) && checkNewPassword(nieuw)){
            user.setPassword(nieuw);
            saveManager.saveState();
            ErrorLabel.setText("Wachtwoord is aangepast.");
            HuidigeWW.clear();
            NieuweWW.clear();
            HerhalingWW.clear();
        }else{
            setLabel();
        }
    }

    //checks if the two passwords match
    public boolean checkPasswordDubbel(String nieuw, String herhaling){
        return (!nieuw.equals(herhaling) && !nieuw.equals("") && !herhaling.equals(""));
    }

    //checks if the correct password has been given
    public boolean checkPasswordCorrect(String huidig, String user){
        return (huidig.equals(user) && !huidig.equals(""));
    }
    
    /*Checks if the new password is not the old password */
    public boolean checkNewPassword(String nieuw){
        return (nieuw.equals(user.getPassword()));
    }

    @Override
    public void setUser(User user){
        this.user = user;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
