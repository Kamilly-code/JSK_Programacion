module com.example.sisa {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sisa to javafx.fxml;
    opens com.example.sisa.POO to javafx.base;
    exports com.example.sisa;
    exports com.example.sisa.Controller;
    opens com.example.sisa.Controller to javafx.fxml;
}