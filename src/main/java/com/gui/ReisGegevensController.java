package com.gui;

import com.logic.Transportmiddel;
import com.logic.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReisGegevensController implements Initializable {

    @FXML
    private Label points;

    private User user;

    @FXML
    private TextField kilometers;

    @FXML
    private ComboBox<String> transportmiddel;

    private ArrayList<Transportmiddel> transportmiddelen;


    // Gaat terug naar het dashboard.
    @FXML
    private void switchToDash() throws IOException {
        Main.showDashView(user);
    }


    // Doet voorbereidende zaken.
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        transportmiddelen = Transportmiddel.getTransportmiddelen();
        for (Transportmiddel t : transportmiddelen) {
            transportmiddel.getItems().add(t.getNaam());
        }
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
            }
        }
    }

    public void setUser(User user){
        this.user = user;
    }

    public void setPointValue(String value){
        points.setText(value);
    }

    // TODO Maak check die invoerveld van kilometers limiteerd tot cijfers.
}