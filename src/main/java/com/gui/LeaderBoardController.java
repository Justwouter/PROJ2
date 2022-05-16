package com.gui;

import com.logic.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LeaderBoardController implements Initializable {

    private static final List<User> users = new ArrayList<>();

    @FXML
    private TableView<User> ranking;

    @FXML
    private final TableColumn<Object, Object> namesKolom = new TableColumn<>();

    @FXML
    private final TableColumn<Object, Object> puntenKolom = new TableColumn<>();

    public static void addUser(User user){
        users.add(user);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        namesKolom.setCellValueFactory(new PropertyValueFactory<>("naam"));
        puntenKolom.setCellValueFactory(new PropertyValueFactory<>("points"));
        ObservableList<User> data = FXCollections.observableArrayList(users);
        ranking.setItems(data);
        ranking.sort();
        namesKolom.setSortable(false);
    }
}
