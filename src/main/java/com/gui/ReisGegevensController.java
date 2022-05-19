package com.gui;

import com.logic.Reizen;
import com.logic.Transportmiddel;
import com.logic.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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

    @FXML
    private Label kostenTotaal; 
    
    private User user;

    private int kostenVanVoertuig;

    private int uitstootVanVoertuig;

    private int puntenVerlies;

    @FXML
    private TextField kilometers;

    @FXML
    private ComboBox<String> transportmiddel;

    private ArrayList<Transportmiddel> transportmiddelen;

    @FXML
    private ComboBox<String> pre_set;

    private ArrayList<Reizen> preSets;

    @FXML
    private TextField hernoemen;

    @FXML
    private Button checkReis;
    
    /**
     * Deze methode berekent en bewerkt de punten van de gebruiker a.h.v. de ingegeven waardes door de gebruiker.
     * @throws IOException <- Hier zeurt java om dus laat het lekker zitten.
     */
    @FXML
    private void switchToDash() throws IOException {
        opslaanUitstoot();
        Main.show("dashboard", user);
    }

    private void opslaanUitstoot(){
        berekenUitstoot();
        user.getPoint().subtractPoints(uitstootVanVoertuig);
    }

    /**
     * Berekent en slaat de uitstoot op.
     */
    private void berekenUitstoot(){
        if (!kilometers.getText().isBlank()){
            int km = Integer.parseInt(kilometers.getText());
//            user.getPoint().subtractPoints(km*kosten/10);
            puntenVerlies = (km* uitstootVanVoertuig) / 89;
        }
    }

    /**
     * Gaat terug naar het dashboard en past de punten NIET aan.
     * @throws IOException
     */
    @FXML
    private void switchToDash2() throws IOException {
        Main.show("dashboard", user);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addVehicles();
      // Sets preSets in ComboBox
        int tellerPreSet = 1;
        preSets = Reizen.getReis();
        for (Reizen r : preSets) {
            if (preSets.get(tellerPreSet - 1).getNaamReis() != null) {
                pre_set.getItems().add(tellerPreSet + ". " + r.getNaamReis());

            } else {
                pre_set.getItems().add(tellerPreSet + ". ");
            }
            tellerPreSet++;
        }

        // Only allows numeric value's in Textfield
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
                uitstootVanVoertuig = t.getUitstoot();
                kostenVanVoertuig = t.getKosten();
                setKostenTotaal();
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

    @FXML
    public void setKostenTotaal(){
        berekenUitstoot();
        kostenTotaal.setText("- " + puntenVerlies);
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
        if(preSets.get(button).getNaamReis() != null){
            kilometers.setText(preSets.get(button).getKilometers().toString());
            transportmiddel.setValue(preSets.get(button).getTransportmiddel().getNaam());
            hernoemen.setText(preSets.get(button).getNaamReis());
        }else{
            kilometers.clear();
            transportmiddel.getSelectionModel().clearSelection();
            hernoemen.clear();
        }   
    }

    @FXML
    public void toevoegenPreSet() {
        // gekozen optie voor preset inlezen
        String selected = pre_set.getValue();
        int gekozen = pre_set.getItems().indexOf(selected);

        //ingevulde kilometers inlezen
        Integer km = Integer.parseInt(kilometers.getText());

        //gekozen transportmiddel inlezen
        String transport = transportmiddel.getValue();
        int gekozen2 = transportmiddel.getItems().indexOf(transport);

        //hernoemen naam pre-set
        String naam = hernoemen.getText();

        switch (gekozen) {
            case (0):
                preSets.set(0, new Reizen(naam, Transportmiddel.getTransportmiddelen().get(gekozen2), km));
                pre_set.getItems().set(gekozen, "1. " + naam);
                break;
            case (1):
                preSets.set(1, new Reizen(naam, Transportmiddel.getTransportmiddelen().get(gekozen2), km));
                pre_set.getItems().set(gekozen, "2. " + naam);
                break;
            case (2):
                preSets.set(2, new Reizen(naam, Transportmiddel.getTransportmiddelen().get(gekozen2), km));
                pre_set.getItems().set(gekozen, "3. " + naam);
                break;
            case (3):
                preSets.set(3, new Reizen(naam, Transportmiddel.getTransportmiddelen().get(gekozen2), km));
                pre_set.getItems().set(gekozen, "4. " + naam);
                break;
            case (4):
                preSets.set(4, new Reizen(naam, Transportmiddel.getTransportmiddelen().get(gekozen2), km));
                pre_set.getItems().set(gekozen, "5. " + naam);
                break;
        }
        pre_set.getSelectionModel().clearSelection();
    }
}

