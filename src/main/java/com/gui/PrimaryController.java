package com.gui;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
