module com.tempid {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.tempid to javafx.fxml;
    exports com.tempid;
}
