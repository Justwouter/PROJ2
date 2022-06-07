package com.gui;

import com.logic.User;
import com.logic.Leaderboard;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminSettingsController extends AController implements Initializable {

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
    public void initialize(URL location, ResourceBundle resources) {
        users = Leaderboard.getUsers("");
        rankBestMonthlyUsers.setCellValueFactory(new PropertyValueFactory<>("rank"));
        naamBestMonthlyUsers.setCellValueFactory(new PropertyValueFactory<>("naam"));
        newpointsBestMonthlyUsers.setCellValueFactory(new PropertyValueFactory<>("PuntMutatiesAsInteger"));
        ObservableList<User> data = FXCollections.observableArrayList(users);
        bestMonthlyUsers.setItems(data);
    }



    @Override
    void setPoints(User user) {}
}
