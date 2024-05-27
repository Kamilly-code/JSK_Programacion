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
    @FXML
    private AnchorPane anchorPane;

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
    @FXML
    private void imagenBasket(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sisa/Baloncesto.fxml"));
            Parent baloncesto = loader.load();

            anchorPane.getChildren().setAll(baloncesto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void imagenTenis(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sisa/Tenis.fxml"));
            Parent tenis = loader.load();

            anchorPane.getChildren().setAll(tenis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void imagenVoley(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sisa/Voley.fxml"));
            Parent voley = loader.load();

            anchorPane.getChildren().setAll(voley);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void imagenGolf(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sisa/Golf.fxml"));
            Parent golf = loader.load();

            anchorPane.getChildren().setAll(golf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void imagenBadminton(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sisa/Badminton.fxml"));
            Parent golf = loader.load();

            anchorPane.getChildren().setAll(golf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void imagenBeisbol(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sisa/Beisbol.fxml"));
            Parent golf = loader.load();

            anchorPane.getChildren().setAll(golf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void imagenBoxeo(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sisa/Boxeo.fxml"));
            Parent golf = loader.load();

            anchorPane.getChildren().setAll(golf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
