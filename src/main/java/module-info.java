module com.gui {
    requires transitive javafx.controls;
    requires javafx.fxml;

    opens com.gui to javafx.fxml;
    exports com.gui;
    exports com.logic;
}
