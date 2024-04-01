module com.example.mainone {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.mainone to javafx.fxml;
    exports com.example.mainone;
}