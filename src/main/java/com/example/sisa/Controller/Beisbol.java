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

public class Beisbol implements Initializable {


    /**
     * El panel principal donde se cargarán las diferentes vistas.
     */


    @FXML
    private AnchorPane anchorPane;


    /**
     * Muestra la vista de Beisbol1 en el panel principal cuando se dispara un evento de acción.
     *
     * @param event El evento de acción que se dispara al hacer clic en el botón correspondiente.
     */


    @FXML
    private void verMasBeisbol1(ActionEvent event) {
        try {
            // Cargar la vista de Beisbol1 desde el archivo FXML correspondiente.
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sisa/Beisbol1.fxml"));
            Parent beisbol1 = loader.load();

            // Establecer la vista cargada en el panel principal.
            anchorPane.getChildren().setAll(beisbol1);
        } catch (IOException e) {
            // Imprimir la traza de la excepción en caso de error al cargar la vista.
            e.printStackTrace();
        }
    }


    /**
     * Muestra la vista de Beisbol2 en el panel principal cuando se dispara un evento de acción.
     *
     * @param event El evento de acción que se dispara al hacer clic en el botón correspondiente.
     */


    @FXML
    private void verMasBeisbol2(ActionEvent event) {
        try {
            // Cargar la vista de Beisbol2 desde el archivo FXML correspondiente.
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sisa/Beisbol2.fxml"));
            Parent beisbol2 = loader.load();

            // Establecer la vista cargada en el panel principal.
            anchorPane.getChildren().setAll(beisbol2);
        } catch (IOException e) {
            // Imprimir la traza de la excepción en caso de error al cargar la vista.
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
        // Método de inicialización del controlador, se puede utilizar para configurar componentes adicionales si es necesario.
    }

}

