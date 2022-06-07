package com.gui;

import com.logic.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ShopController extends AController {

    @FXML
    private Label points;

    @Override
    void setPoints(User user) {
        points.setText(user.getPoint().getPointsString());
    }
}
