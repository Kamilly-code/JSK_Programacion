module com.example.boneca2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.boneca2 to javafx.fxml;
    exports com.example.boneca2;
}