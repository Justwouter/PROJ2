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

    private int uitstoot;

    @FXML
    private TextField kilometers;

    @FXML
    private ComboBox<String> transportmiddel;

    private ArrayList<Transportmiddel> transportmiddelen;



    /**
     * Deze methode berekent en bewerkt de punten van de gebruiker a.h.v. de ingegeven waardes door de gebruiker.
     * @throws IOException <- Hier zeurt java om dus laat het lekker zitten.
     */
    @FXML
    private void switchToDash() throws IOException {
        berekenUitstoot();
        Main.show("dashboard", user);
    }

    /**
     * Berekent en slaat de uitstoot op.
     */
    private void berekenUitstoot(){
        if (!kilometers.getText().isBlank()){
            int km = Integer.parseInt(kilometers.getText());
            user.getPoint().subtractPoints(km*kosten/10);
        }
    }

    // Gaat terug naar het dashboard en past de punten NIET aan.
    @FXML
    private void switchToDash2() throws IOException {
        Main.show("dashboard", user);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addVehicles();
        addNumberLimiter();
        addTextLimiter(9);
    }

    /**
     * Deze methode voegt alle aanwezige transportmiddelen toe aan de ComboBox zodat deze geselecteerd kunnen worden.
     */
    public void addVehicles(){
        transportmiddelen = Transportmiddel.getTransportmiddelen();
        for (Transportmiddel t : transportmiddelen) {
            transportmiddel.getItems().add(t.getNaam());
        }
    }

    /**
     * Deze methode zorgt ervoor dat het invoerveld van de kilometers louter cijfers kan bevatten.
     */
    public void addNumberLimiter(){
        kilometers.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                kilometers.setText(newValue.replaceAll("\\D", ""));
            }
        });
    }

    /**
     * Deze methode zorgt voor een maximaal aantal tekens die het invoerveld van de kilometers kan bevatten.
     * @param maxLength aantal maximale tekens
     */

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

    /**
     * Deze methode checkt de gekozen waarde van de ComboBox en geeft daarbij berekeningswaardes mee.
     * TODO Het toevoegen van de berekeningen zelf zodat ze zichtbaar worden voor de gebruiker vóór het opslaan.
     */
    @FXML
    public void printItem(){
        String s = transportmiddel.getValue();
        for (Transportmiddel t : transportmiddelen){
            if (t.getNaam().equals(s)){
                uitstoot = t.getUitstoot();
                kosten = t.getKosten();
            }
        }
    }

    /**
     * Deze methode geeft een user mee aan de controller die de reisgegevens invoert.
     * @param u De gebruiker die zijn reisgegevens invoert.
     */
    public void setUser(User u){
        this.user = u;
    }

    /**
     * Deze methode geeft een user mee aan de controller waarvan punten opgehaald worden.
     * @param user De gebruiker die zijn reisgegevens invoert.
     */
    @Override
    public void setPoints(User user) {
        points.setText(user.getPoint().getPointsString());
    }
  
    public void setKostenTotaal(){
        //TODO maak hier de berekening
    }
    // TODO Maak check die invoerveld van kilometers limiteerd tot cijfers.
}

