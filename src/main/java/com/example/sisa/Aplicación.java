package com.example.sisa;

import com.example.sisa.Controller.Boxeo;
import com.example.sisa.Controller.Calendario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Aplicación extends Application {
    public static Stage stage = null;
    @Override
    public void start(Stage stage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/com/example/sisa/inicio.fxml"));
        Scene scene = new Scene(root);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/example/sisa/Imagenes/LOGO/LOGO-SISA.png")));
        stage.setTitle("JSK");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}