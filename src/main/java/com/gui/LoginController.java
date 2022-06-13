package com.gui;

import com.logic.Leaderboard;
import com.logic.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController extends AController {

    @FXML
    public TextField usernameField;

    @FXML
    public PasswordField passwordField;

    @FXML
    public Label foutmelding;

    /**
     * Zodra er op de login knop gedrukt wordt of als er op enter wordt gedrukt, 
     * checkt de LoginChecker of de juiste gegevens zijn meegegeven.
     * Indien LoginChecker false returned, wordt er op het scherm voor de gebruiker getoond dat er iets fout was.
     * @throws IOException <- Vangt problemen op!
     */
    @FXML
    private void attemptLogin() throws IOException {
        if (loginChecker(usernameField.getText(), passwordField.getText())){
            foutmelding.setVisible(false);
                Main.show("Dashboard", user);
        }else{
            foutmelding.setVisible(true);
        }
    }

    /**
     * Loopt alle gebruikers door en zodra een gebruiker gelijk is aan de username dan wordt er gekeken naar het wachtwoord.
     * Komt het wachtwoord overeen, dan returned het true en set hij de user van de Controller.
     * Komt het niet overeen, returned het false.
     * @param username Gebruikersnaam van user
     * @param password Wachtwoord van User
     * @return True/False
     */
    public boolean loginChecker(String username, String password) {
        for (User user : Leaderboard.getUsers("")){
            if (user.getUsername().equals(username)){
                if (user.checkPassword(password)){
                    setUser(user);
                    return true;
                }
            }
        }
        return false;
    }

    @FXML
    private void onEnterPressed() throws IOException{
        attemptLogin();
    }
}
