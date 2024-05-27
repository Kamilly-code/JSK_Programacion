package com.example.sisa.Controller;

import com.example.sisa.POO.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Clase Amigos que implementa la interfaz Initializable de JavaFX.
 * Esta clase se encarga de gestionar la funcionalidad de la pantalla de amigos.
 * que la clase amigos nos agrega Usuarios a la lista de amigos que el usuario dese.

 */

public class Amigos implements Initializable {

    @FXML
    private TableView<Usuario> TablaAmigos1;

    @FXML
    private TableColumn<Usuario, String> columnaNombre;

    @FXML
    private TableColumn<Usuario, String> columnApellido;

    @FXML
    private TableColumn<Usuario, String> columCorreo;

    @FXML
    private Label labelBuscador;

    @FXML
    private TextField textBuscador;

    @FXML
    private Button botonBuscar;



    /**
     * Método que se ejecuta al inicializar la clase.
     * Llama al método start con un nuevo Stage.
     */


            @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        start(new Stage());




    }

    /**
     * Método que inicializa la tabla de amigos.
     * Carga una lista de usuarios desde un archivo y los agrega a una lista observable,
     * que luego se establece como los elementos de la tabla.
     * También se establecen las fábricas de celdas de valor para las columnas de la tabla.
     */

    public void start(Stage primaryStage) {
        // Agregar un método de acción al botón

        // creamos una lista observable de usuarios que nos lee el metodo cargarUsuariossuAmigos y
        // lo que el metodos nos devuelve lo mete a usuariosRegisto.
        ObservableList<Usuario> usuariosRegisto = FXCollections.observableArrayList(
                //ponemos el usuario actual para que nos lea los amigos de ese usuario.
                cargarUsuariossuAmigos(MenuLateral.colocar_el_usuario_Actual()));

        for (Usuario usuario : usuariosRegisto) {
            System.out.println(usuario);
        }
        TablaAmigos1.setItems(usuariosRegisto);

         // ahora metemos la informacion en las columnas de la tabla
        TablaAmigos1.setItems(usuariosRegisto);

        columnaNombre.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nombre"));
        columnApellido.setCellValueFactory(new PropertyValueFactory<Usuario, String>("apellidos"));
        columCorreo.setCellValueFactory(new PropertyValueFactory<Usuario, String>("correoElectronico"));
    }

    /**
     * Método que se ejecuta al hacer clic en el botón "Agregar amigos".
     * Obtiene el nombre del usuario del campo de texto y llama al método agregarUsuario con ese nombre.
     *
     */

    @FXML
    private void ButtonAgregarAmigos(ActionEvent event) {
        // Obtén el nombre del usuario desde el campo de texto
        String nombre = textBuscador.getText();

        // Llama a los métodos con el nombre del usuario
        agregarUsuario(nombre);



        // creamos una lista observable de usuarios que nos lee el metodo cargarUsuariosDesdeRegistro y lo que el metodos nos devuelve lo mete a usuariosRegisto.
        ObservableList<Usuario> usuariosRegisto = FXCollections.observableArrayList(

                cargarUsuariossuAmigos(MenuLateral.colocar_el_usuario_Actual())



        );

        for (Usuario usuario : usuariosRegisto) {
            System.out.println(usuario);
        }
        TablaAmigos1.setItems(usuariosRegisto);

        // ahora metemos la informacion en las columnas de la tabla
        TablaAmigos1.setItems(usuariosRegisto);

        columnaNombre.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nombre"));
        columnApellido.setCellValueFactory(new PropertyValueFactory<Usuario, String>("apellidos"));
        columCorreo.setCellValueFactory(new PropertyValueFactory<Usuario, String>("correoElectronico"));
    }

    /**
     * Método que carga una lista de amigos de un usuario desde un archivo.
     * El nombre del archivo se basa en el nombre del usuario proporcionado.
     * Cada línea del archivo se divide en partes y se utiliza para crear un nuevo objeto Usuario que se agrega a la lista.
     * @param nombre El nombre del usuario.
     * @return Una lista de los amigos de ese usuario.
     */


    public static ArrayList<Usuario> cargarUsuariossuAmigos(String nombre) {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        String rutaFichero = "src/main/java/com/example/sisa/Ficheros/"+nombre+".txt";
        try (Scanner lector = new Scanner(new File(rutaFichero.trim()))) {
            while (lector.hasNext()) {
                String linea = lector.nextLine();
                String[] partes = linea.split(";");
                if (partes.length >= 5) {
                    String nombre1 = partes[0].trim();
                    String apellidos = partes[1].trim();
                    String correo = partes[2].trim();
                    String usuario = partes[3].trim();
                    String contraseña = partes[4].trim();
                    usuarios.add(new Usuario(nombre1, apellidos, correo, usuario, contraseña));
                }

            }

            System.out.println("Usuarios cargados: " + usuarios.size());


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return usuarios;
    }

    /**
     * Método que carga una lista de todos los usuarios registrados desde un archivo.
     * Cada línea del archivo se divide en partes y se utiliza para crear un nuevo objeto Usuario que se agrega a la lista.
     * @return Una lista de usuarios.
     */


    public static ArrayList<Usuario> cargarUsuariosRegistro() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        String rutaFichero = "src/main/java/com/example/sisa/Ficheros/Registro.txt";
        try (Scanner lector = new Scanner(new File(rutaFichero.trim()))) {
            while (lector.hasNext()) {
                String linea = lector.nextLine();
                String[] partes = linea.split(";");
                if (partes.length >= 5) {
                    String nombre1 = partes[0].trim();
                    String apellidos = partes[1].trim();
                    String correo = partes[2].trim();
                    String usuario = partes[3].trim();
                    String contraseña = partes[4].trim();
                    usuarios.add(new Usuario(nombre1, apellidos, correo, usuario, contraseña));
                }

            }

            System.out.println("Usuarios cargados: " + usuarios.size());


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return usuarios;
    }

    /**
     * Método que agrega un usuario a la lista de amigos de un usuario.
     * Busca el nombre del usuario en la lista de usuarios registrados.
     * Si se encuentra, se escribe la información del usuario en el archivo de amigos del usuario actual.
     * Si no se encuentra, se imprime un mensaje de error.
     * @param nombre El nombre del usuario a agregar.
     */

    public static void agregarUsuario(String nombre) {
        ArrayList<Usuario> usuarios = cargarUsuariosRegistro();

        // Buscar el nombre en la lista de usuarios
        for (Usuario usuario : usuarios) {
            if (usuario.getUsuario().equals(nombre)) {
                try {
                    // Crear un FileWriter en modo append
                    FileWriter writer = new FileWriter("src/main/java/com/example/sisa/Ficheros/"+MenuLateral.colocar_el_usuario_Actual()+".txt", true);

                    // Escribir el contenido en el fichero
                    writer.write(usuario.toString());

                    // Añadir una nueva línea al final
                    writer.write(System.lineSeparator());

                    // Cerrar el FileWriter
                    writer.close();
                } catch (IOException e) {
                    System.out.println("Error al escribir en el fichero: " + e.getMessage());
                }

                // Salir del método una vez que se ha encontrado el nombre
                return;
            }
        }

        // Si llegamos a este punto, el nombre no se encontró en la lista de usuarios
        System.out.println("El usuario " + nombre + " no se encontró en la lista de usuarios.");
    }



    }



