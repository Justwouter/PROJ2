package com.gui;

import com.logic.User;

public abstract class AController {

    protected User user;

    public void setUser(User user){
        this.user = user;
    }
    abstract void setPoints(User user);
}
