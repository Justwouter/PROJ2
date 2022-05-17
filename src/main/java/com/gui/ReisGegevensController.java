package com.gui;

import com.logic.Reizen;
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

    private User user;

    private int kosten;

    @FXML
    private TextField kilometers;

    @FXML
    private ComboBox<String> transportmiddel;

    private ArrayList<Transportmiddel> transportmiddelen;

    @FXML
    private ComboBox<String> pre_set;

    private ArrayList<Reizen> preSets;


    // Gaat terug naar het dashboard.
    @FXML
    private void switchToDash() throws IOException {
        if (!kilometers.getText().isBlank()){
            int km = Integer.parseInt(kilometers.getText());
            user.getPoint().subtractPoints(km*kosten/10);
        }
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

        // Sets preSets in ComboBox
        int tellerPreSet = 1;
        preSets = Reizen.getReis();
        for (Reizen r : preSets) {
            pre_set.getItems().add(tellerPreSet + ". " + r.getNaamReis());
            tellerPreSet ++;
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


    // TODO Maak check die invoerveld van kilometers limiteerd tot cijfers.


    //Geven de knoppen 1,2,3,4 en 5 een functie
    @FXML
    public void buttenOne(){
        invullenPreSet(0);
    }

    @FXML
    public void buttenTwo(){
        invullenPreSet(1);
    }

    @FXML
    public void buttenThree(){
        invullenPreSet(2);
    }

    @FXML
    public void buttenFor(){
        invullenPreSet(3);
    }

    @FXML
    public void buttenFive(){
        invullenPreSet(4);
    }

    //zet de opgeslagen waarde in de juiste vakken voor de berekening
    public void invullenPreSet(Integer button){
        if(button < preSets.size()){
            kilometers.setText(preSets.get(button).getKilometers().toString());
            transportmiddel.setValue(preSets.get(button).getTransportmiddel().getNaam());
        }else{
            kilometers.clear();
            transportmiddel.setValue(null);
        }   
    }

    @FXML
    public void toevoegenPreSet() {
        int gekozen = 1;
        switch (gekozen) {
            case (1):
                preSets.set(0, new Reizen("nieuw 1", Transportmiddel.getTransportmiddelen().get(1), 25));
                break;
            case (2):
                preSets.set(1, new Reizen("nieuw 2", Transportmiddel.getTransportmiddelen().get(1), 25));
                break;
            case (3):
                preSets.set(2, new Reizen("neiuw 3", Transportmiddel.getTransportmiddelen().get(1), 25));
                break;
            case (4):
                preSets.set(3, new Reizen("neiuw 4", Transportmiddel.getTransportmiddelen().get(1), 25));
                break;
            case (5):
                preSets.set(4, new Reizen("nieuw 5", Transportmiddel.getTransportmiddelen().get(1), 25));
                break;
        }
    }
}