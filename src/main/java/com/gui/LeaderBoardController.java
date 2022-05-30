package com.gui;

import com.logic.Leaderboard;
import com.logic.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.skin.TableHeaderRow;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LeaderBoardController extends AController implements Initializable {

    @FXML
    public Button dbButton1;
    private User user;

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
        fillBoard();
        colourBoard();
        preventRearranging();
    }

    /**
     * Vult het boord met de data.
     */
    private void fillBoard(){
        rankKolom.setCellValueFactory(new PropertyValueFactory<>("rank"));
        namesKolom.setCellValueFactory(new PropertyValueFactory<>("naam"));
        puntenKolom.setCellValueFactory(new PropertyValueFactory<>("points"));
        ObservableList<User> data = FXCollections.observableArrayList(Leaderboard.getUsers());
        leaderboard.setItems(data);
    }

    /**
     * Voorkomt dat de gebruiker de kolommen kan verplaatsen.
     */
    private void preventRearranging(){
        leaderboard.widthProperty().addListener((ov, t, t1) -> {
            // Get the table header
            TableHeaderRow header = (TableHeaderRow) leaderboard.lookup("TableHeaderRow");
            header.reorderingProperty().addListener((observable, oldValue, newValue) -> header.setReordering(false));
        });
    }

    /**
     * Geeft de kleuren aan het leaderboard afhankelijk van het aantal punten per rij.
     */
    private void colourBoard(){
        leaderboard.setRowFactory(tv -> new TableRow<>() {
            @Override
            protected void updateItem(User item, boolean empty) {
                super.updateItem(item, empty);
                getStyleClass().removeAll();
                if (item == null || item.getPoints() == null)
                    getStyleClass().add("");
                else if (item.getPoints() > 500)
                    getStyleClass().add("good");
                else if (item.getPoints() > 0)
                    getStyleClass().add("warning");
                else if (item.getPoints() < 0)
                    getStyleClass().add("bad");
                else
                    getStyleClass().add("error");
            }
        });
    }

    /**
     * Als een kolom gesorteerd wordt, zorgt dit ervoor dat de kleuren opnieuw komen.
     */
    @FXML
    private void onSort(){
        leaderboard.refresh();
        colourBoard();
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
    
    public void setUser(User user){
        this.user = user;
    }

    @Override
    public void setPoints(User user){} //just here because of the implementation

    @Override
    public void setPresets(User user){} //just here because of the implementation
}
