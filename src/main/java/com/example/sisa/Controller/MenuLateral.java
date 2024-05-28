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


    /**
     * Controlador de registro para cargar los usuarios registrados.
     */
    @FXML
    public Controller registroController; // Controlador para el registro de usuarios

    @FXML
    public TextFlow nombreUsuario;  // Componente de la interfaz para mostrar el nombre del usuario

    /**
     * Constructor de la clase MenuLateral.
     * Inicializa el controlador de registro.
     */
    public MenuLateral() {
        this.registroController = new Controller();
    }

    private BorderPane mainBorderPane;  // Componente de la interfaz principal

    /**
     * Establece el componente principal de la interfaz.
     * @param mainBorderPane El componente principal de la interfaz.
     */
    public void setMainBorderPane(BorderPane mainBorderPane) {
        this.mainBorderPane = mainBorderPane;
    }

    // Métodos para manejar los eventos de los botones de la barra lateral del menú
    // Cada método carga una pantalla diferente en el componente principal de la interfaz
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
    /**
     * Método que se ejecuta al inicializar la clase.
     * Carga la lista de usuarios y muestra el nombre del usuario actual en la interfaz.
     */
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

    /**
     * Método para obtener el nombre del usuario actual.
     * Lee el nombre del usuario actual de un fichero.
     * @return El nombre del usuario actual, o null si ocurre un error.
     */

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
