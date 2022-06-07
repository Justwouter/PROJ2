package com.gui;

import com.logic.User;
import javafx.fxml.Initializable;

public abstract class AController implements Initializable {

    protected User user;

    public void setUser(User user){
        this.user = user;
    }
    abstract void setPoints(User user);
}
