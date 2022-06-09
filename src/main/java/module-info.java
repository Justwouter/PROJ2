module com.gui {
    requires transitive javafx.controls;
    requires transitive javafx.base;
    requires transitive javafx.graphics;
    requires transitive javafx.media;
    requires transitive javafx.fxml;
    requires transitive com.google.gson;
    requires java.desktop;

    opens com.gui to javafx.fxml;
    exports com.gui;
    exports com.logic;
}
