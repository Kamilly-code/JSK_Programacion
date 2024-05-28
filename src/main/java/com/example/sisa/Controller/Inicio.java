package com.example.sisa.Controller;

import com.example.sisa.POO.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.event.ActionEvent;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
/**
 * Clase Inicio que implementa la interfaz Initializable.
 * Esta clase se encarga de manejar la lógica de inicio de sesión y registro de usuarios.
 */
public class Inicio implements Initializable {
    // Campos de texto para recoger la información del usuario
    public static Stage stage = null;
    private static ArrayList<Usuario> usuarios = new ArrayList<>();
    @FXML
    private TextField textNombre;

    @FXML
    private Hyperlink link;

    @FXML
    private Hyperlink flecha;

    @FXML
    private TextField textUsuario;

    @FXML
    private TextField textContrasena;

    @FXML
    private  TextField textApellidos, textCorreo;

    /**
     * Método que se ejecuta al hacer clic en el botón de inicio de sesión.
     * Comprueba si el usuario existe y si la contraseña es correcta.
     * Si el inicio de sesión es exitoso, carga la pantalla principal.
     * Si el inicio de sesión falla, muestra un mensaje de error.
     */

    @FXML
    private void eventInicar(ActionEvent event) throws IOException {
        String nombre = textUsuario.getText();
        String contraseña = textContrasena.getText();

        int comprobarResultado = ComprobarUsuario(nombre, contraseña);

        if (comprobarResultado == 2) {

            octener_el_usuario_actual(nombre);
            Object o = event.getSource();
            Node node = (Node) o;
            Scene scene1 = node.getScene();
            Window window = scene1.getWindow();
            Stage stage = (Stage) window;

            Parent root = FXMLLoader.load(getClass().getResource("/com/example/sisa/Main.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("JSK");
            stage.setScene(scene);
            Inicio.stage = stage;
            stage.setMinWidth(1024);
            stage.setMaxWidth(1024);
            stage.setMinHeight(668);
            stage.setMaxHeight(668);
            stage.setResizable(true);
            stage.show();
        } else {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error!");

            if (comprobarResultado == 0){
                alert.setHeaderText("Usuario no registrado");
                alert.setContentText("Crea un usuario para poder acceder");
            } else if (comprobarResultado == 1) {
                alert.setHeaderText("Contraseña incorrecta");
                alert.setContentText("Digite la contraseña correcta para poder acceder");
            }

            alert.show();

            System.out.println(ComprobarUsuario("johan","1234"));


        }
    }

    /**
     * Método que se ejecuta al hacer clic en el enlace de registro.
     * Carga la pantalla de registro.
     */

    @FXML
    private void eventLink(ActionEvent event) throws IOException {
        Object s = event.getSource();
        Node node = (Node) s;
        Scene scene1 = node.getScene();
        Window window = scene1.getWindow();
        Stage stage = (Stage) window;

        Parent root = FXMLLoader.load(getClass().getResource("/com/example/sisa/registro.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Método que se ejecuta al hacer clic en la flecha.
     * Carga la pantalla de inicio.
     */

    @FXML
    private void eventFlecha(ActionEvent event) throws IOException {
        Object f = event.getSource();
        Node node = (Node) f;
        Scene scene1 = node.getScene();
        Window window = scene1.getWindow();
        Stage stage = (Stage) window;

        Parent root = FXMLLoader.load(getClass().getResource("/com/example/sisa/inicio.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    /**
     * Método que se ejecuta al hacer clic en el botón de continuar en la pantalla de registro.
     * Crea un nuevo usuario y lo añade a la lista de usuarios.
     * Si el registro es exitoso, carga la pantalla de inicio.
     * Si el registro falla, muestra un mensaje de error.
     */

    @FXML
    private void eventContinuar(ActionEvent event) throws IOException {
        //creando el usuario
        String nombre = textNombre.getText();
        String contra = textContrasena.getText();
        String apellidos = textApellidos.getText();
        String correo = textCorreo.getText();
        String usuarioNombre = textUsuario.getText();
        Usuario usuario = new Usuario(nombre, apellidos, correo, usuarioNombre, contra);

        if (nombre.isEmpty() || contra.isEmpty() || apellidos.isEmpty() || correo.isEmpty() || usuarioNombre.isEmpty()){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("CAMPOS VACIOS");
            alert.setContentText("Tiene que preencher los campos para poder adentrar en la aplicación");
            alert.showAndWait();
        } else {

            int resultado = ComprobarUsuarioYGmail(textUsuario.getText(),textCorreo.getText());

            switch (resultado) {
                case 1:
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Usuario ya existe");
                    alert.setContentText("El nombre de usuario ya existe cambialo");
                    alert.showAndWait();
                    break;
                case 2:
                    Alert alert2 = new Alert(AlertType.ERROR);
                    alert2.setTitle("Error");
                    alert2.setHeaderText("Correo ya existe");
                    alert2.setContentText("El correo electrónico ya existe, cambialo");
                    alert2.showAndWait();
                    break;
                default:
                    usuarios.add(usuario);
                    informacion_del_usuario_creado(usuario);
                    Object s = event.getSource();
                    Node node = (Node) s;
                    Scene scene1 = node.getScene();
                    Window window = scene1.getWindow();
                    Stage stage = (Stage) window;

                    Parent root = FXMLLoader.load(getClass().getResource("/com/example/sisa/inicio.fxml"));
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    break;
            }
        }
    }
    /**
     * Método que se ejecuta al inicializar la clase.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    /**
     * Método para crear y comprobar la existencia de un fichero.
     * @param rutaFichero La ruta del fichero.
     * @return El fichero si existe y es válido, de lo contrario, null.
     */

    public static File crearYComprobarFichero(String rutaFichero) {
        // Implementar la lógica para crear y comprobar el fichero
        // Retornar true si el fichero existe y es válido, de lo contrario, false.
        File fichero = new File(rutaFichero.trim());
        fichero.exists();
        return fichero;
    }
    /**
     * Método para comprobar si un usuario existe y si la contraseña es correcta.
     * @param nombre El nombre del usuario.
     * @param contraseña La contraseña del usuario.
     * @return 2 si el usuario existe y la contraseña es correcta, 1 si la contraseña es incorrecta, 0 si el usuario no existe.
     */

    public static int ComprobarUsuario (String nombre , String contraseña) {
        String rutaFichero = "src/main/java/com/example/sisa/Ficheros/Usuarios.txt";
        File fichero = new File(rutaFichero.trim());
        String[] pepe;
        if (Crear_y_comprobar_fichero(rutaFichero)) {
            Scanner lector = null;
            try {

                lector = new Scanner(fichero);
                while (lector.hasNext()) {
                    String linea = lector.nextLine();
                    String[] partes = linea.split(";");
                    if (partes.length == 2) {
                        String clave = partes[0].trim();
                        String valor = partes[1].trim();
                        pepe = new String[]{clave, valor};
                        if (pepe[0].equals(nombre)) {
                            System.out.println("entro al 2 if");
                            if (pepe[1].equals(contraseña)) {
                                return 2; // usuário registrado, con la contraseña y el usuario correctos.
                            } else {
                                return 1; // usuário registrado,sin embargo la contraseña está incorrecta.
                            }
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("No encuentro el fichero " + e.getMessage());
            }
        } else {
            System.out.println("no entro");
        }
        return 0; // usuario no registrado
    }

    /**
     * Método para comprobar si un nombre de usuario y un correo electrónico ya existen.
     * @param nombre El nombre del usuario.
     * @param correo El correo electrónico del usuario.
     * @return 1 si el nombre de usuario ya existe, 2 si el correo electrónico ya existe,
     * 0 si ni el nombre de usuario ni el correo electrónico existen.
     */

    public static int ComprobarUsuarioYGmail (String nombre, String correo) {
        String rutaFichero = "src/main/java/com/example/sisa/Ficheros/Usuarios.txt";
        String rutaRegistro = "src/main/java/com/example/sisa/Ficheros/Registro.txt";

        File fichero = new File(rutaFichero.trim());
        File segundoFichero = new File(rutaRegistro.trim());

        if (Crear_y_comprobar_fichero(rutaFichero)) {
            Scanner lector = null;
            Scanner lector2 = null;
            try {
                lector = new Scanner(fichero);
                lector2 = new Scanner(segundoFichero);
                while  (lector.hasNext()) {
                    String linea = lector.nextLine();
                    String[] partes = linea.split(";");
                    if (partes.length >= 1) {
                        String clave = partes[0].trim();
                        if (clave.equals(nombre)) {
                            return 1; // usuário registrado
                        }
                    }
                }

                while (lector2.hasNext()) {
                    String linea = lector2.nextLine();
                    String[] partes = linea.split(";");
                    if (partes.length >= 5) {
                        String claves = partes[2].trim();
                        if (claves.equals(correo)){
                            return 2;
                        }
                    }
                }

            } catch (FileNotFoundException e) {
                System.out.println("No encuentro el fichero " + e.getMessage());
            }
        } else {
            System.out.println("no entro");
        }
        return 0; // usuario no registrado
    }

    /**
     * Método para comprobar la existencia de un fichero.
     * @param ruta La ruta del fichero.
     * @return true si el fichero existe, false en caso contrario.
     */

    public static boolean Crear_y_comprobar_fichero (String ruta){
        File fichero = new File(ruta);
        return fichero.exists();
    }

    /**
     * Método para guardar la información de un usuario recién creado.
     * @param usuario El usuario recién creado.
     */
    private void informacion_del_usuario_creado(Usuario usuario) {
        String rutaFichero = "src/main/java/com/example/sisa/Ficheros/Usuarios.txt";
        String rutaPuntos = "src/main/java/com/example/sisa/Ficheros/Puntos.txt";
        String rutaRegistro = "src/main/java/com/example/sisa/Ficheros/Registro.txt";
        String rutadeamigos = "src/main/java/com/example/sisa/Ficheros/"+usuario.getUsuario()+".txt";
        try (FileWriter fw = new FileWriter(crearYComprobarFichero(rutaFichero), true)) {
            fw.append(usuario.getUsuario() + ";" +  usuario.getContraseña() + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (FileWriter puntos_subir = new FileWriter((rutaPuntos),true)){
            puntos_subir.append(usuario.getUsuario() + ",5,0,0,5" + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (FileWriter fw = new FileWriter(crearYComprobarFichero(rutaRegistro), true)) {
            fw.append("\n"+usuario.getNombre() + ";" + usuario.getApellidos() + ";" +usuario.getCorreoElectronico() + ";" +usuario.getUsuario()+ ";" +usuario.getContraseña());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            File file = new File(rutadeamigos);
            if (file.createNewFile()) {
                System.out.println("Fichero creado: " + file.getName());
            } else {
                System.out.println("El fichero ya existe.");
            }
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error.");
            e.printStackTrace();
        }


    }

    /**
     * Método para guardar el nombre del usuario actual.
     * @param nombre El nombre del usuario actual.
     */
    public static void octener_el_usuario_actual (String nombre){
        String usuario_txt = "src/main/java/com/example/sisa/Ficheros/Usuario_Actual.txt";
        String contenido = nombre;

        try (FileWriter writer = new FileWriter(usuario_txt)) {
            writer.append(contenido);
        } catch (IOException e) {
            System.out.println("Ocurrió un error al escribir en el archivo: " + e.getMessage());
        }
    }




}
