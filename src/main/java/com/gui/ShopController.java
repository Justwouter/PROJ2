package com.gui;

import com.logic.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;


public class ShopController extends AController {

    @FXML
    private Label points;

    //just here because of the implementation
    @Override
    void setPoints(User user) {
        points.setText(user.getPoint().getPointsString());
    }
}
