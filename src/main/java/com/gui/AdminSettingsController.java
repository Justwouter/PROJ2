package com.gui;

import com.logic.User;
import com.logic.Leaderboard;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminSettingsController extends AController implements Initializable {

    @FXML
    private TableView<User> bestMonthlyUsers = new TableView<>();

    @FXML
    private final TableColumn<Object, Object> rankBestMonthlyUsers = new TableColumn<>();

    @FXML
    private final TableColumn<Object, Object> naamBestMonthlyUsers = new TableColumn<>();

    @FXML
    private final TableColumn<Object, Object> newpointsBestMonthlyUsers = new TableColumn<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rankBestMonthlyUsers.setCellValueFactory(new PropertyValueFactory<>("rank"));
        naamBestMonthlyUsers.setCellValueFactory(new PropertyValueFactory<>("naam"));
        newpointsBestMonthlyUsers.setCellValueFactory(new PropertyValueFactory<>("punten"));
        ObservableList<User> data = FXCollections.observableArrayList(Leaderboard.getUsers("best"));
        bestMonthlyUsers.setItems(data);
    }
}