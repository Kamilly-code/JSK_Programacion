package com.example.sisa.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Badminton implements Initializable {

    /**
     * Panel principal.
     */

    @FXML
    private AnchorPane anchorPane;


    /**
     * Maneja el evento de clic para mostrar la vista de Badminton1.
     *
     * @param event El evento de acción que se dispara al hacer clic.
     */


    @FXML
    private void verMasBadminton1(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sisa/Badminton1.fxml"));
            Parent badminton1 = loader.load();
            anchorPane.getChildren().setAll(badminton1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * Maneja el evento de clic para mostrar la vista de Badminton2.
     *
     * @param event El evento de acción que se dispara al hacer clic.
     */


    @FXML
    private void verMasBadminton2(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sisa/Badminton2.fxml"));
            Parent badminton2 = loader.load();
            anchorPane.getChildren().setAll(badminton2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Inicializa el controlador. Este método se llama automáticamente después de cargar el archivo FXML.
     *
     * @param url La ubicación utilizada para resolver rutas relativas del objeto raíz o null si la ubicación no es conocida.
     * @param resourceBundle Los recursos utilizados para localizar el objeto raíz o null si el objeto raíz no fue localizado.
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Inicialización del controlador, si es necesario.
    }
}
