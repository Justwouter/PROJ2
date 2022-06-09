package com.gui;

import com.logic.User;
import com.logic.Filiaal;
import com.logic.Leaderboard;
import com.logic.SaveManager;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminSettingsController extends AController implements Initializable {

    private ArrayList<User> users;

    @FXML
    private TableView<User> bestMonthlyUsers = new TableView<>();

    @FXML
    private final TableColumn<Object, Object> rankBestMonthlyUsers = new TableColumn<>();

    @FXML
    private final TableColumn<Object, Object> naamBestMonthlyUsers = new TableColumn<>();

    @FXML
    private final TableColumn<Object, Object> newpointsBestMonthlyUsers = new TableColumn<>();

    @FXML
    private Label points;

    @FXML
    private TextField naam;

    @FXML
    private ComboBox<String> filiaal;
    
    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField passwordRepeat;

    @FXML
    private Label foutmeldingWW;

    @FXML
    private Button makeUser;

    @FXML
    private Button makeAdmin;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        users = Leaderboard.getUsers("");
        rankBestMonthlyUsers.setCellValueFactory(new PropertyValueFactory<>("rank"));
        naamBestMonthlyUsers.setCellValueFactory(new PropertyValueFactory<>("naam"));
        newpointsBestMonthlyUsers.setCellValueFactory(new PropertyValueFactory<>("PuntMutatiesAsInteger"));
        ObservableList<User> data = FXCollections.observableArrayList(users);
        bestMonthlyUsers.setItems(data);
        addFilialen();
        ToevoegenOpties(false);
    }

    private void ToevoegenOpties(boolean setVisible) {
        naam.setVisible(setVisible);
        filiaal.setVisible(setVisible);
        username.setVisible(setVisible);
        password.setVisible(setVisible);
        passwordRepeat.setVisible(setVisible);
        makeUser.setVisible(setVisible);
        makeAdmin.setVisible(setVisible);
    }

    /**
     * Deze methode voegt alle aanwezige fililalen toe aan de ComboBox zodat deze geselecteerd kunnen worden.
     */
    private void addFilialen() {
        for (Filiaal f : Filiaal.filialen) {
            filiaal.getItems().add(f.getNaam());
        }
    }

    public void showToevoegOpties(){
        ToevoegenOpties(true);
    }

    @FXML
    public void makeUser() throws IOException {
        if(password.getText().equals(passwordRepeat.getText())){
            new User(naam.getText(), false, username.getText(), password.getText(), filiaal.getValue());
            SaveManager.saveState();
            foutmeldingWW.setVisible(true);
        }else{
            foutmeldingWW.setVisible(false);
        }
    }

    @FXML
    public void makeAdmin() throws IOException {
        if (password.getText().equals(passwordRepeat.getText())) {
            new User(naam.getText(), true, username.getText(), password.getText(), filiaal.getValue());
            SaveManager.saveState();
        } else {
            foutmeldingWW.setVisible(false);
        }
    }
}
