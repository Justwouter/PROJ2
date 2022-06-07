package com.gui;

import com.logic.User;

public abstract class AController {

    protected User user;

    public void setUser(User user){
        this.user = user;
    }
    abstract void setPoints(User user);
}

    //laat dit even staan gaat mogelijk anders zodat het makkelijker wordt
    // @FXML
    // private void switchToReisGegevens() throws IOException {
    //     Main.show("reisgegevens", user);
    // }

    // @FXML
    // private void switchToLeaderboard() throws IOException {
    //     Main.show("leaderboard", user);
    // }

    // @FXML
    // public void onDBButtonClick() throws IOException {
    //     Main.show("dashboard", user);
    // }
