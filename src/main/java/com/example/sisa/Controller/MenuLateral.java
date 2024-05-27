package com.example.sisa.Controller;

import com.example.sisa.POO.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import com.example.sisa.Controller.Inicio.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import static com.example.sisa.Aplicación.stage;

public class MenuLateral implements Initializable {
    @FXML
    public Controller registroController;

    @FXML
    public TextFlow nombreUsuario;


    public MenuLateral() {
        this.registroController = new Controller();
    }

    private BorderPane mainBorderPane;


    public void setMainBorderPane(BorderPane mainBorderPane) {
        this.mainBorderPane = mainBorderPane;
    }
    @FXML
    private void botonInicio(ActionEvent event) throws IOException {
        System.out.println("Inicio");
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/sisa/Principal.fxml"));
        mainBorderPane.setCenter(root);

    }

    @FXML
    private void botonDeportes(ActionEvent event) throws IOException {
        System.out.println("Deportes");
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/sisa/Deportes.fxml"));
        mainBorderPane.setCenter(root);


    }
    @FXML
    private void botonPuntos(ActionEvent event) throws IOException {
        System.out.println("Puntos");
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/sisa/Puntos.fxml"));
        mainBorderPane.setCenter(root);
    }
    @FXML
    private void botonAmigos(ActionEvent event) throws IOException {
        System.out.println("Amigos");
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/sisa/Amigos.fxml"));
        mainBorderPane.setCenter(root);

    }
    @FXML
    private void botonSalir(ActionEvent event){
        System.out.println("Salir");
        System.exit(0);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Usuario> usuarios = registroController.carregarUsuariosDoRegistro();
        String nomeUsuarioAtual = colocar_el_usuario_Actual();
        if (nomeUsuarioAtual == null) {
            System.out.println("Não foi possível obter o nome do usuário atual.");
            return;
        }
        nomeUsuarioAtual = nomeUsuarioAtual.trim();
       Usuario usuario = null;


        Text text1 = new Text(nomeUsuarioAtual);
        nombreUsuario.getChildren().clear();
        nombreUsuario.getChildren().add(text1);

    }

    public static String colocar_el_usuario_Actual (){
        try {
            String rutaFichero = "src/main/java/com/example/sisa/Ficheros/Usuario_Actual.txt";
            File myFile = new File(rutaFichero);
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                return data;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Ocurrió un error.");
            e.printStackTrace();
        }
        return null;
    }

}
