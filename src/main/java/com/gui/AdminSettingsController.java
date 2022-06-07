package com.gui;

import com.logic.User;
import javafx.fxml.FXML;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.logic.Leaderboard;
import com.logic.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminSettingsController extends AController implements Initializable {

    private User user;
    private ArrayList<User> users;

    @FXML
    private TableView<User> bestMonthlyUsers;

    @FXML
    private final TableColumn<Object, Object> rankBestMonthlyUsers = new TableColumn<>();

    @FXML
    private final TableColumn<Object, Object> naamBestMonthlyUsers = new TableColumn<>();

    @FXML
    private final TableColumn<Object, Object> newpointsBestMonthlyUsers = new TableColumn<>();

    @FXML
    private void switchToReisGegevens() throws IOException {
        Main.show("reisgegevens", user);
    }

    @FXML
    private void switchToLeaderboard() throws IOException {
        Main.show("leaderboard", user);
    }

    @FXML
    public void switchToDashboard() throws IOException {
        Main.show("dashboard", user);
    }

    @FXML
    public void switchToInstellingen() throws IOException {
        Main.show("instellingen", user);
    }

    @FXML
    public void switchToShop() throws IOException {
        Main.show("shop", user);
    }

    @FXML //TODO: kan iemand die goed is in fxml hier ff extra naar kijken?!
    public void switchToAdminActions() throws IOException{
        Main.show("adminaction", user);
    } //er moet dus nog een adminaction.fxml komen

    @Override
    void setPoints(User user) {
        
    }
  
    @Override
    void setPresets(User user) {
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        users = Leaderboard.getUsers();
        rankBestMonthlyUsers.setCellValueFactory(new PropertyValueFactory<>("rank"));
        naamBestMonthlyUsers.setCellValueFactory(new PropertyValueFactory<>("naam"));
        newpointsBestMonthlyUsers.setCellValueFactory(new PropertyValueFactory<>("PuntMutatiesAsInteger"));
        ObservableList<User> data = FXCollections.observableArrayList(users);
        bestMonthlyUsers.setItems(data);
    }
}
