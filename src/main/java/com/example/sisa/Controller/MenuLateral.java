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
    public Controller registroController;


    /**
     * Componente de la interfaz de usuario para mostrar el nombre del usuario.
     */
    @FXML
    public TextFlow nombreUsuario;


    /**
     * Panel principal de la aplicación donde se cargarán las diferentes vistas.
     */
    private BorderPane mainBorderPane;


    /**
     * Constructor de la clase MenuLateral.
     * Inicializa el controlador de registro.
     */
    public MenuLateral() {
        this.registroController = new Controller();
    }


    /**
     * Establece el panel principal de la aplicación.
     *
     * @param mainBorderPane El panel principal.
     */
    public void setMainBorderPane(BorderPane mainBorderPane) {
        this.mainBorderPane = mainBorderPane;
    }


    /**
     * Maneja el evento del botón de inicio.
     * Carga la vista principal en el panel central.
     *
     * @param event El evento de acción.
     * @throws IOException Si ocurre un error al cargar el archivo FXML.
     */
    @FXML
    private void botonInicio(ActionEvent event) throws IOException {
        System.out.println("Inicio");
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/sisa/Principal.fxml"));
        mainBorderPane.setCenter(root);
    }


    /**
     * Maneja el evento del botón de deportes.
     * Carga la vista de deportes en el panel central.
     *
     * @param event El evento de acción.
     * @throws IOException Si ocurre un error al cargar el archivo FXML.
     */
    @FXML
    private void botonDeportes(ActionEvent event) throws IOException {
        System.out.println("Deportes");
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/sisa/Deportes.fxml"));
        mainBorderPane.setCenter(root);
    }


    /**
     * Maneja el evento del botón de puntos.
     * Carga la vista de puntos en el panel central.
     *
     * @param event El evento de acción.
     * @throws IOException Si ocurre un error al cargar el archivo FXML.
     */
    @FXML
    private void botonPuntos(ActionEvent event) throws IOException {
        System.out.println("Puntos");
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/sisa/Puntos.fxml"));
        mainBorderPane.setCenter(root);
    }


    /**
     * Maneja el evento del botón de amigos.
     * Carga la vista de amigos en el panel central.
     *
     * @param event El evento de acción.
     * @throws IOException Si ocurre un error al cargar el archivo FXML.
     */
    @FXML
    private void botonAmigos(ActionEvent event) throws IOException {
        System.out.println("Amigos");
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/sisa/Amigos.fxml"));
        mainBorderPane.setCenter(root);
    }


    /**
     * Maneja el evento del botón de salir.
     * Termina la aplicación.
     *
     * @param event El evento de acción.
     */
    @FXML
    private void botonSalir(ActionEvent event) {
        System.out.println("Salir");
        System.exit(0);
    }



    /**
     * Inicializa el controlador después de cargar el archivo FXML.
     *
     * @param url La ubicación utilizada para resolver rutas relativas del objeto raíz o null si la ubicación no es conocida.
     * @param resourceBundle Los recursos utilizados para localizar el objeto raíz o null si el objeto raíz no fue localizado.
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
     * Obtiene el nombre del usuario actual desde un archivo de texto.
     *
     * @return El nombre del usuario actual, o null si no se pudo obtener.
     */

    public static String colocar_el_usuario_Actual() {
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
