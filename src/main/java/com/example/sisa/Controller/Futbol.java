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

public class Futbol implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private void verMasFutbol1(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sisa/Futbol1.fxml"));
            Parent futbol1 = loader.load();

            anchorPane.getChildren().setAll(futbol1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void verMasFutbol2(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sisa/Futbol2.fxml"));
            Parent futbol1 = loader.load();

            anchorPane.getChildren().setAll(futbol1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
