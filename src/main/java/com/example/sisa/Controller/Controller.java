package com.example.sisa.Controller;

import com.example.sisa.POO.Usuario;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public static String getUsuario_Actual (){
        try{
            File usuarioActual = new File("src/main/java/com/example/sisa/Ficheros/Usuario_Actual.txt");
            Scanner leerUsuario = new Scanner(usuarioActual);

            while (leerUsuario.hasNext()) {
                String linea = leerUsuario.nextLine();

                return linea;
            }

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Usuario> carregarUsuariosDoRegistro() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        String rutaFichero = "src/main/java/com/example/sisa/Ficheros/Registro.txt";
        try (Scanner lector = new Scanner(new File(rutaFichero))) {
            while (lector.hasNext()) {
                String linea = lector.nextLine();
                String[] partes = linea.split(";");
                if (partes.length >= 5) {
                    String contraseña = partes[4].trim();
                    String usuario = partes[3].trim();
                    String nombre = partes[0].trim();
                    String apellidos = partes[1].trim();
                    String correo = partes[2].trim();
                    usuarios.add(new Usuario(nombre,apellidos,correo,usuario,contraseña));
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return usuarios;
    }
}