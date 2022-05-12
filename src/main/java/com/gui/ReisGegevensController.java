package com.gui;

import java.io.IOException;
import javafx.fxml.FXML;

public class ReisGegevensController {

    @FXML
    private void switchToDash() throws IOException {
        App.setRoot("dashboard");
    }
}