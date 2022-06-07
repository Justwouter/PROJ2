package com.gui;

import com.logic.Reis;
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

public class ReisGegevensController extends AController implements Initializable {

    @FXML
    private Label points;

    @FXML
    private Label kostenPunten; 

    @FXML
    private Label kostenCO2;

    private User user;

    public int kostenVanVoertuig;

    private int uitstootVanVoertuig;

    private int puntenVerlies;

    private int uitstootCO2;

    @FXML
    private TextField kilometers;

    @FXML
    private ComboBox<String> transportmiddel;

    private ArrayList<Transportmiddel> transportmiddelen;

    @FXML
    private ComboBox<String> pre_set;

    private ArrayList<Reis> preSets;

    @FXML
    private TextField hernoemen;

    @FXML
    private Button checkReis;
    
    /**
     * Deze methode berekent en bewerkt de punten van de gebruiker a.h.v. de ingegeven waardes door de gebruiker.
     * @throws IOException
     */
    @FXML
    private void switchToDashboardWithCO2() throws IOException {
        opslaanUitstoot();
        Main.show("dashboard", user);
    }

    private void opslaanUitstoot(){
        berekenPunten();
        user.getPoint().subtractPoints(puntenVerlies);
    }

    /**
     * Berekent en slaat de uitstoot op in punten en CO2 uitstoot.
     */
    private void berekenPunten(){
        if(kilometers.getText().isBlank()){
            puntenVerlies = 0;
            uitstootCO2 = 0;
        }
        if (!kilometers.getText().isBlank()){
            int km = Integer.parseInt(kilometers.getText());
            puntenVerlies = (km* uitstootVanVoertuig) / 89;
            uitstootCO2 = (km* uitstootVanVoertuig);
        }
    }

    /**
     * Zorgt ervoor dat de kosten worden uitgeprint (zowel punten als CO2)
     */
    @FXML
    public void setTotaalAndCO2(){
        berekenPunten();
        kostenCO2.setText(uitstootCO2 + "g CO2");
        kostenPunten.setText("- " + puntenVerlies);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addVehicles();
        // Only allows numeric value's in Textfield
        addNumberLimiter();
        addTextLimiter(5);
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
     */
    @FXML
    public void printItem(){
        String s = transportmiddel.getValue();
        for (Transportmiddel t : transportmiddelen){
            if (t.getNaam().equals(s)){
                uitstootVanVoertuig = t.getUitstoot();
                kostenVanVoertuig = t.getKosten();
                setTotaalAndCO2();
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

    /**
     * Geven de knoppen 1,2,3,4 en 5 een functie
    */
    @FXML
    public void buttonOne(){
        invullenPreSet(0);
    }
    
    @FXML
    public void buttonTwo(){
        invullenPreSet(1);
    }
    
    @FXML
    public void buttonThree(){
        invullenPreSet(2);
    }
    
    @FXML
    public void buttonFour(){
        invullenPreSet(3);
    }
    
    @FXML
    public void buttonFive(){
        invullenPreSet(4);
    }

    /**
     * zet de opgeslagen waarde in de juiste vakken voor de berekening
    */
    public void invullenPreSet(Integer button){
        if(preSets.get(button).getNaamReis() != null){
            kilometers.setText(preSets.get(button).getKilometers().toString());
            transportmiddel.setValue(preSets.get(button).getTransportmiddel().getNaam());
            hernoemen.setText(preSets.get(button).getNaamReis());
        }else{
            kilometers.clear();
            transportmiddel.getSelectionModel().clearSelection();
            hernoemen.clear();
            kostenCO2.setText("Uitstoot");
            kostenPunten.setText("Kosten");
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

        preSets.set(gekozen, new Reis(naam, Transportmiddel.getTransportmiddelen().get(gekozen2), km));
        user.setReis(gekozen, preSets.get(gekozen));
        System.out.println(user.getReis(gekozen).getNaamReis());
        pre_set.getItems().set(gekozen, gekozen + 1 + ". " + naam);
        pre_set.getSelectionModel().clearSelection();
    }

    @Override
    public void setPresets(User user){
        int tellerPreSet = 1;
        preSets = user.getReizen();
        for (Reis r : preSets) {
            if (preSets.get(tellerPreSet - 1).getNaamReis() != null) {
                pre_set.getItems().add(tellerPreSet + ". " + r.getNaamReis());

            } else {
                pre_set.getItems().add(tellerPreSet + ". ");
            }
            tellerPreSet++;
        }
    }
}

