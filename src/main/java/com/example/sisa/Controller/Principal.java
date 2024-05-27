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
    @FXML
    private VBox contenido_principal;

    @FXML
    private void botonJugar(ActionEvent event){
        System.out.println("JUGAR");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sisa/Futbol.fxml"));
            Parent futbol = loader.load();

            contenido_principal.getChildren().setAll(futbol);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void botonPelear(ActionEvent event){
        System.out.println("JUGAR");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sisa/Boxeo.fxml"));
            Parent futbol = loader.load();

            contenido_principal.getChildren().setAll(futbol);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void botonTenis(ActionEvent event){
        System.out.println("JUGAR");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sisa/Tenis.fxml"));
            Parent futbol = loader.load();

            contenido_principal.getChildren().setAll(futbol);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
