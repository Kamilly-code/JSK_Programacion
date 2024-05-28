package com.example.sisa.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class Principal implements Initializable {


    /**
     * El contenedor principal donde se cargarán las diferentes vistas de los deportes.
     */
    @FXML
    private VBox contenido_principal;



    /**
     * Maneja el evento del botón "Jugar" para cargar la vista de fútbol.
     *
     * @param event El evento de acción.
     */
    @FXML
    private void botonJugar(ActionEvent event) {
        System.out.println("JUGAR");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sisa/Futbol.fxml"));
            Parent futbol = loader.load();
            contenido_principal.getChildren().setAll(futbol);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * Maneja el evento del botón "Pelear" para cargar la vista de boxeo.
     *
     * @param event El evento de acción.
     */
    @FXML
    private void botonPelear(ActionEvent event) {
        System.out.println("PELEAR");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sisa/Boxeo.fxml"));
            Parent boxeo = loader.load();
            contenido_principal.getChildren().setAll(boxeo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * Maneja el evento del botón "Tenis" para cargar la vista de tenis.
     *
     * @param event El evento de acción.
     */
    @FXML
    private void botonTenis(ActionEvent event) {
        System.out.println("TENIS");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sisa/Tenis.fxml"));
            Parent tenis = loader.load();
            contenido_principal.getChildren().setAll(tenis);
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
