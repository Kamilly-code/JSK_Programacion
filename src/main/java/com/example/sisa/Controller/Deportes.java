package com.example.sisa.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Deportes implements Initializable {

    /**
     * El panel principal donde se cargarán las diferentes vistas.
     */


    @FXML
    private AnchorPane anchorPane;


    /**
     * Muestra la vista de Futbol en el panel principal cuando se dispara un evento de acción.
     *
     * @param event El evento de acción que se dispara al hacer clic en el botón correspondiente.
     */


    @FXML
    private void imagenFutbol(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sisa/Futbol.fxml"));
            Parent futbol = loader.load();

            anchorPane.getChildren().setAll(futbol);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Muestra la vista de Baloncesto en el panel principal cuando se dispara un evento de acción.
     *
     * @param event El evento de acción que se dispara al hacer clic en el botón correspondiente.
     */


    @FXML
    private void imagenBasket(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sisa/Baloncesto.fxml"));
            Parent baloncesto = loader.load();

            anchorPane.getChildren().setAll(baloncesto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Muestra la vista de Tenis en el panel principal cuando se dispara un evento de acción.
     *
     * @param event El evento de acción que se dispara al hacer clic en el botón correspondiente.
     */


    @FXML
    private void imagenTenis(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sisa/Tenis.fxml"));
            Parent tenis = loader.load();

            anchorPane.getChildren().setAll(tenis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Muestra la vista de Voley en el panel principal cuando se dispara un evento de acción.
     *
     * @param event El evento de acción que se dispara al hacer clic en el botón correspondiente.
     */


    @FXML
    private void imagenVoley(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sisa/Voley.fxml"));
            Parent voley = loader.load();

            anchorPane.getChildren().setAll(voley);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Muestra la vista de Golf en el panel principal cuando se dispara un evento de acción.
     *
     * @param event El evento de acción que se dispara al hacer clic en el botón correspondiente.
     */


    @FXML
    private void imagenGolf(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sisa/Golf.fxml"));
            Parent golf = loader.load();

            anchorPane.getChildren().setAll(golf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Muestra la vista de Badminton en el panel principal cuando se dispara un evento de acción.
     *
     * @param event El evento de acción que se dispara al hacer clic en el botón correspondiente.
     */


    @FXML
    private void imagenBadminton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sisa/Badminton.fxml"));
            Parent badminton = loader.load();

            anchorPane.getChildren().setAll(badminton);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Muestra la vista de Beisbol en el panel principal cuando se dispara un evento de acción.
     *
     * @param event El evento de acción que se dispara al hacer clic en el botón correspondiente.
     */


    @FXML
    private void imagenBeisbol(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sisa/Beisbol.fxml"));
            Parent beisbol = loader.load();

            anchorPane.getChildren().setAll(beisbol);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Muestra la vista de Boxeo en el panel principal cuando se dispara un evento de acción.
     *
     * @param event El evento de acción que se dispara al hacer clic en el botón correspondiente.
     */


    @FXML
    private void imagenBoxeo(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sisa/Boxeo.fxml"));
            Parent boxeo = loader.load();

            anchorPane.getChildren().setAll(boxeo);
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
        // Método de inicialización del controlador, se puede utilizar para configurar componentes adicionales si es necesario.
    }
}
