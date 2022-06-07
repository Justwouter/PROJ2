package com.gui;

import com.logic.User;
import com.logic.Filiaal;
import com.logic.Leaderboard;
import com.logic.SaveManager;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
    
    private ArrayList<Filiaal> filialen;

    
    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        users = Leaderboard.getUsers("");
        rankBestMonthlyUsers.setCellValueFactory(new PropertyValueFactory<>("rank"));
        naamBestMonthlyUsers.setCellValueFactory(new PropertyValueFactory<>("naam"));
        newpointsBestMonthlyUsers.setCellValueFactory(new PropertyValueFactory<>("PuntMutatiesAsInteger"));
        ObservableList<User> data = FXCollections.observableArrayList(users);
        bestMonthlyUsers.setItems(data);
        addFilialen();
    }

    /**
     * Deze methode voegt alle aanwezige fililalen toe aan de ComboBox zodat deze geselecteerd kunnen worden.
     */
    private void addFilialen() {
        filialen = Filiaal.getFilialen();
        for (Filiaal f : filialen) {
            filiaal.getItems().add(f.getNaam());
        }
    }

    //just here because implementations
    @Override
    void setPoints(User user) {}

    @FXML
    public void makeUser() throws IOException {
       new User(naam.getText(), false, username.getText(), password.getText(), filiaal.getValue());
       SaveManager.saveState();
    }
    
    @FXML
    public void makeAdmin() throws IOException {
        new User(naam.getText(), true, username.getText(), password.getText(), filiaal.getValue());
       SaveManager.saveState();
    }
}
