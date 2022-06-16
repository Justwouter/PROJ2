module com.gui {
    requires transitive javafx.controls;
    requires transitive javafx.base;
    requires transitive javafx.graphics;
    requires transitive javafx.media;
    requires transitive javafx.fxml;
    requires transitive com.google.gson;

    opens com.gui to javafx.fxml;
    opens com.logic;
    opens com.save;
    exports com.gui;
    exports com.logic;
    exports com.save;
}
