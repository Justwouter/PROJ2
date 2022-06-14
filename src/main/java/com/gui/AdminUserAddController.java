package com.gui;

import java.net.URL;
import java.util.ResourceBundle;

import com.logic.Filiaal;
import com.logic.User;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AdminUserAddController extends AController implements Initializable {
  
    @FXML
    private Label ErrorLabel;

    @FXML
    private CheckBox Admin;

    @FXML
    private ComboBox<String> filiaal;

    @FXML
    private TextField naam;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField passwordRepeat;

    @FXML
    private TextField username;

    @FXML
    void AddUser() {
        if (!naam.getText().isBlank() && !password.getText().isBlank() && !passwordRepeat.getText().isBlank() && !username.getText().isBlank() && !filiaal.getValue().isBlank()){
            if (Admin.selectedProperty().get()){
                new User(naam.getText(), true, username.getText(), password.getText(), filiaal.getValue());
            }else{
                new User(naam.getText(), false, username.getText(), password.getText(), filiaal.getValue());
            }
            naam.clear();
            password.clear();
            passwordRepeat.clear();
            username.clear();
            saveManager.saveState();
            ErrorLabel.setText("");
        }else{
            ErrorLabel.setText("Voer alle velden in.");
        }
    }


     //Deze methode voegt alle aanwezige fililalen toe aan de ComboBox zodat deze geselecteerd kunnen worden.
    private void addFilialen() {
        for (Filiaal f : Filiaal.filialen) {
            filiaal.getItems().add(f.getNaam());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addFilialen();
    }
}
