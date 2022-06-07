package com.gui;

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
    private Label points;

    @Override
    void setUser(User user) {
        this.user = user;
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

    //just here because of the implementation
    @Override
    void setPoints(User user) {     
    }
    @Override
    void setPresets(User user) {  
    }
}
