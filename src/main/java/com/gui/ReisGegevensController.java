package com.gui;

import com.logic.Transportmiddel;
import com.logic.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReisGegevensController implements Initializable, IController {

    @FXML
    private Label points;
    //private Label kostenTotaal;

    private User user;

    private int kosten;

    @FXML
    private TextField kilometers;

    @FXML
    private ComboBox<String> transportmiddel;

    private ArrayList<Transportmiddel> transportmiddelen;


    // Gaat terug naar het dashboard en past de punten aan.
    @FXML
    private void switchToDash() throws IOException {
        if (!kilometers.getText().isBlank()){
            int km = Integer.parseInt(kilometers.getText());
            user.getPoint().subtractPoints(km*kosten/10);
        }
        Main.show("dashboard", user);
    }

    // Gaat terug naar het dashboard en past de punten NIET aan.
    @FXML
    private void switchToDash2() throws IOException {
        Main.show("dashboard", user);
    }

    // Doet voorbereidende zaken.
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Sets vehicles in ComboBox
        transportmiddelen = Transportmiddel.getTransportmiddelen();
        for (Transportmiddel t : transportmiddelen) {
            transportmiddel.getItems().add(t.getNaam());
        }

        // Only allows numeric value's in Textfield
        addNumberLimiter();

        addTextLimiter(9);
    }

    public void addNumberLimiter(){
        kilometers.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                kilometers.setText(newValue.replaceAll("\\D", ""));
            }
        });
    }

    public void addTextLimiter(int maxLength) {
        kilometers.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (kilometers.getText().length() > maxLength) {
                    String s = kilometers.getText().substring(0, maxLength);
                    kilometers.setText(s);
                }
            }
        });
    }

    // Checkt geselecteerde transportmiddel.
    @FXML
    public void printItem(){
        String s = transportmiddel.getValue();
        for (Transportmiddel t : transportmiddelen){
            if (t.getNaam().equals(s)){
                /* TODO Berekeningen maken of doorgeven aan berekeningen.
                Dit is voorbereid zodat hiermee berekend kan worden.
                Er wordt hier de geselecteerde item naar voren gehaald
                 */
                kosten = t.getKosten();
            }
        }
    }

    public void setUser(User u){
        this.user = u;
    }

    @Override
    public void setPoints(User user) {
        points.setText(user.getPoint().getPointsString());
    }

    public void setKostenTotaal(){
        //TODO maak hier de berekening
    }
    // TODO Maak check die invoerveld van kilometers limiteerd tot cijfers.
}
