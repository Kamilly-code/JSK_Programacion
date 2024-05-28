package com.example.sisa.Controller;

import com.example.sisa.POO.Jugadores;
import com.example.sisa.POO.Posiciones;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.Scanner;

import static com.example.sisa.Controller.Controller.getUsuario_Actual;

public class Tenis2 implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    //Exteriores****************************************************************************************
    private CalendarioTenis1 calendarioController;
    @FXML
    private DatePicker fechaDisponible;
    private static ObservableList<Jugadores> jugadores = FXCollections.observableArrayList();

    public ObservableList<Jugadores> getJugadores() {
        return jugadores;
    }
    //***************************************************************************************************

    @FXML
    private void verMasTenis1(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sisa/Tenis1.fxml"));
            Parent baloncesto1 = loader.load();

            anchorPane.getChildren().setAll(baloncesto1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void verMasTenis2(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sisa/Tenis2.fxml"));
            Parent baloncesto2 = loader.load();

            anchorPane.getChildren().setAll(baloncesto2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * Jugadores
     * @param actionEvent
     */

    @FXML
    public void eventJugador1(ActionEvent actionEvent) {
        String nombreUsuarioActual = getUsuario_Actual();
        if (nombreUsuarioActual == null) {
            System.out.println("No se pueden obtener los datos del registro.");
            return;
        }
        nombreUsuarioActual = nombreUsuarioActual.trim();

        String nombre = null;
        String apellido = null;

        String[] datos = cargarDatosRegistro(nombreUsuarioActual);
        if (datos != null) {
            nombre = datos[0];
            apellido = datos[1];
            Posiciones posicion = Posiciones.Tenista1;



            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar Acción");
            alert.setHeaderText("¿SEGURO QUE QUIERES CONTINUAR?");
            alert.setContentText("Accederás a la tabla de calendario para elegir el día del partido.");

            ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

            if (result == ButtonType.OK) {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/com/example/sisa/CalendarioTenis2.fxml"));
                    Parent root = loader.load();

                    // Obtenga el controlador y defina el nombre, apellido y posición
                    CalendarioTenis2 calendarioController = loader.getController();
                    calendarioController.setNombre(nombre);
                    calendarioController.setApellido(apellido);
                    calendarioController.setPosicion(posicion);

                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("No se pueden obtener los datos del registro.");
            return;
        }
    }
    @FXML
    public void eventJugador2(ActionEvent actionEvent) {
        String nombreUsuarioActual = getUsuario_Actual();
        if (nombreUsuarioActual == null) {
            System.out.println("No se pueden obtener los datos del registro.");
            return;
        }
        nombreUsuarioActual = nombreUsuarioActual.trim();

        String nombre = null;
        String apellido = null;

        String[] datos = cargarDatosRegistro(nombreUsuarioActual);
        if (datos != null) {
            nombre = datos[0];
            apellido = datos[1];
            Posiciones posicion = Posiciones.Tenista2;



            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar Acción");
            alert.setHeaderText("¿SEGURO QUE QUIERES CONTINUAR?");
            alert.setContentText("Accederás a la tabla de calendario para elegir el día del partido.");

            ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

            if (result == ButtonType.OK) {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/com/example/sisa/CalendarioTenis2.fxml"));
                    Parent root = loader.load();

                    // Obtenga el controlador y defina el nombre, apellido y posición
                    CalendarioTenis2 calendarioController = loader.getController();
                    calendarioController.setNombre(nombre);
                    calendarioController.setApellido(apellido);
                    calendarioController.setPosicion(posicion);

                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("No se pudo obtener los datos del registro.");
            return;
        }
    }

    @FXML
    public void eventJugador3(ActionEvent actionEvent) {
        String nombreUsuarioActual = getUsuario_Actual();
        if (nombreUsuarioActual == null) {
            System.out.println("No se pueden obtener los datos del registro.");
            return;
        }
        nombreUsuarioActual = nombreUsuarioActual.trim();

        String nombre = null;
        String apellido = null;

        String[] datos = cargarDatosRegistro(nombreUsuarioActual);
        if (datos != null) {
            nombre = datos[0];
            apellido = datos[1];
            Posiciones posicion = Posiciones.Tenista3;



            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar Acción");
            alert.setHeaderText("¿SEGURO QUE QUIERES CONTINUAR?");
            alert.setContentText("Accederás a la tabla de calendario para elegir el día del partido.");

            ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

            if (result == ButtonType.OK) {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/com/example/sisa/CalendarioTenis2.fxml"));
                    Parent root = loader.load();

                    // Obtenga el controlador y defina el nombre, apellido y posición
                    CalendarioTenis2 calendarioController = loader.getController();
                    calendarioController.setNombre(nombre);
                    calendarioController.setApellido(apellido);
                    calendarioController.setPosicion(posicion);

                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("No se pueden obtener los datos del registro.");
            return;
        }
    }

    @FXML
    public void eventJugador4(ActionEvent actionEvent) {
        String nombreUsuarioActual = getUsuario_Actual();
        if (nombreUsuarioActual == null) {
            System.out.println("No se pueden obtener los datos del registro.");
            return;
        }
        nombreUsuarioActual = nombreUsuarioActual.trim();

        String nombre = null;
        String apellido = null;

        String[] datos = cargarDatosRegistro(nombreUsuarioActual);
        if (datos != null) {
            nombre = datos[0];
            apellido = datos[1];
            Posiciones posicion = Posiciones.Tenista4;



            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar Acción");
            alert.setHeaderText("¿SEGURO QUE QUIERES CONTINUAR?");
            alert.setContentText("Accederás a la tabla de calendario para elegir el día del partido.");

            ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

            if (result == ButtonType.OK) {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/com/example/sisa/CalendarioTenis2.fxml"));
                    Parent root = loader.load();

                    // Obtenga el controlador y defina el nombre, apellido y posición
                    CalendarioTenis2 calendarioController = loader.getController();
                    calendarioController.setNombre(nombre);
                    calendarioController.setApellido(apellido);
                    calendarioController.setPosicion(posicion);

                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("No se pueden obtener los datos del registro.");
            return;
        }
    }


    /**
     * Datos importantes
     * @param nombreUA
     * @return
     */


    private String[] cargarDatosRegistro(String nombreUA) {
        String rutaFichero = "src/main/java/com/example/sisa/Ficheros/Registro.txt";
        try (Scanner lector = new Scanner(new File(rutaFichero))) {
            while (lector.hasNext()) {
                String linha = lector.nextLine();
                String[] partes = linha.split(";");
                if (partes.length >= 5 && partes[3].trim().equals(nombreUA)) {
                    String nombre = partes[0].trim();
                    String apellidos = partes[1].trim();
                    return new String[] {nombre,apellidos};
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static void salvarJogadores() {
        String rutaFichero = "src/main/java/com/example/sisa/Ficheros/Tenis2.txt";
        try (FileWriter writer = new FileWriter(rutaFichero,true)) {
            for (Jugadores jugador : jugadores) {
                writer.append(jugador.getNombre() + ";" + jugador.getApellido() + ";" + jugador.getPosiciones() + ";" + jugador.getFechaDisponible() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al escribir en el archivo: " + e.getMessage());
        }
    }

    public static void cargarJogadores(CalendarioTenis2 calendario) {
        jugadores.clear();
        String rutaFichero = "src/main/java/com/example/sisa/Ficheros/Tenis2.txt";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try (Scanner lector = new Scanner(new File(rutaFichero))) {
            while (lector.hasNext()) {
                String linha = lector.nextLine();
                String[] partes = linha.split(";");
                if (partes.length >= 4) {
                    String nombre = partes[0].trim();
                    String apellidos = partes[1].trim();
                    Posiciones posicao = Posiciones.valueOf(partes[2].trim());
                    LocalDate fecha = LocalDate.parse(partes[3].trim(), formatter);

                    Jugadores jugador = new Jugadores(nombre, apellidos, posicao, fecha);
                    jugadores.add(jugador);
                }
            }

            // Después de cargar a todos los jugadores, llamamos al método handleDateSelection
            calendario.handleDateSelection(null); // Pase null si no tiene ActionEvent

        } catch (FileNotFoundException e) {
            throw new RuntimeException("Archivo no encontrado: " + rutaFichero, e);
        } catch (Exception e) {
            throw new RuntimeException("Error al cargar jugadores", e);
        }
    }



    public LocalDate getSelectedDate() {
        if (fechaDisponible != null) {
            return fechaDisponible.getValue();
        } else {
            System.out.println("Datepicker no se ha inicializado correctamente.");
            return null;
        }
    }



}
