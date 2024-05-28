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

public class Golf implements Initializable {


    /**
     * El contenedor principal donde se cargarán las diferentes vistas de Golf.
     */
    @FXML
    private AnchorPane anchorPane;



    /**
     * Maneja el evento del botón "Ver Más Golf 1" para cargar la vista Golf1.
     *
     * @param event El evento de acción.
     */
    @FXML
    private void verMasGolf1(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sisa/Golf1.fxml"));
            Parent golf1 = loader.load();
            anchorPane.getChildren().setAll(golf1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * Maneja el evento del botón "Ver Más Golf 2" para cargar la vista Golf2.
     *
     * @param event El evento de acción.
     */
    @FXML
    private void verMasGolf2(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sisa/Golf2.fxml"));
            Parent golf2 = loader.load();
            anchorPane.getChildren().setAll(golf2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * Inicializa el controlador después de cargar el archivo FXML.
     *
     * @param url La ubicación utilizada para resolver rutas relativas del objeto raíz o null si la ubicación no es conocida.
     * @param resourceBundle Los recursos utilizados para localizar el objeto raíz o null si el objeto raíz no fue localizado.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
