package com.gui;

import com.logic.Leaderboard;
import com.logic.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LeaderBoardController extends AController implements Initializable {

    @FXML
    private TableView<User> leaderboard;

    @FXML
    private final TableColumn<Object, Object> rankKolom = new TableColumn<>();

    @FXML
    private final TableColumn<Object, Object> namesKolom = new TableColumn<>();

    @FXML
    private final TableColumn<Object, Object> puntenKolom = new TableColumn<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rankKolom.setCellValueFactory(new PropertyValueFactory<>("rank"));
        namesKolom.setCellValueFactory(new PropertyValueFactory<>("naam"));
        puntenKolom.setCellValueFactory(new PropertyValueFactory<>("points"));
        ObservableList<User> data = FXCollections.observableArrayList(Leaderboard.getUsers());
        leaderboard.setItems(data);
        namesKolom.setSortable(false);
            leaderboard.setRowFactory(tv -> new TableRow<>() {
            @Override
            protected void updateItem(User item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || item.getPoints() == null)
                    setStyle("");
                else if (item.getPoints() > 500)
                    setStyle("-fx-background-color: green;");
                else if (item.getPoints() > 0)
                    setStyle("-fx-background-color: orange;");
                else if (item.getPoints() < 0)
                    setStyle("-fx-background-color: red;");
                else
                    setStyle("-fx-background-color: blue");
            }
        });
    }

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

    @Override
    public void setPoints(User user){} //just here because of the implementation

}
