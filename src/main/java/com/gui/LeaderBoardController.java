package com.gui;

import com.logic.Filiaal;
import com.logic.Leaderboard;
import com.logic.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.skin.TableHeaderRow;

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

    @FXML
    private ComboBox<String> filiaal;

    @FXML
    private CheckBox bestuserbutton; //Gaat checken of er punten van de beste users van laatste maand moet worden laten zien

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillBoard("");
        colourBoard();
        preventRearranging();
        addFilialen();
    }

    /**
     * Deze methode voegt alle aanwezige fililalen toe aan de ComboBox zodat deze geselecteerd kunnen worden.
     */
    private void addFilialen() {
        for (Filiaal f : Filiaal.filialen) {
            filiaal.getItems().add(f.getNaam());
        }
    }

    /**
     * Vult het boord met de data.
     */
    private void fillBoard(String userFilter){
        rankKolom.setCellValueFactory(new PropertyValueFactory<>("rank"));
        namesKolom.setCellValueFactory(new PropertyValueFactory<>("naam"));
        puntenKolom.setCellValueFactory(new PropertyValueFactory<>("totalCO2"));
        ObservableList<User> data = FXCollections.observableArrayList(Leaderboard.getUsers(userFilter));
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
                if (item == null || item.getTotalCO2() == null)
                    getStyleClass().add("");
                else if (item.getTotalCO2() <= 20)
                    getStyleClass().add("good");
                else if (item.getTotalCO2() <= 40)
                    getStyleClass().add("warning");
                else if (item.getTotalCO2() > 40)
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
    public void filterLeaderboard(){
        // herlaad het leaderboard en vult het met de gefilterde users
        leaderboard.getItems().clear();
        leaderboard.refresh();
        String s = filiaal.getValue();
        fillBoard(s);
    }

    @FXML
    public void onButtonSelect(){
        leaderboard.getItems().clear();
        leaderboard.refresh();
        if(bestuserbutton.isSelected()){
            fillBoard("beste4usersvandemaand");
        } else {
            fillBoard("");
        }
    }
}
