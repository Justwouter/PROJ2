package com.gui;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class DashController {

    @FXML
    private void switchToReisGegevens() throws IOException {
        App.setRoot("reisgegevens");
    }
}
