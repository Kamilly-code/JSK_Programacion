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

public class Voley implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private void verMasVoley1(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sisa/Voley1.fxml"));
            Parent baloncesto1 = loader.load();

            anchorPane.getChildren().setAll(baloncesto1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void verMasVoley2(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sisa/Voley2.fxml"));
            Parent baloncesto2 = loader.load();

            anchorPane.getChildren().setAll(baloncesto2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
