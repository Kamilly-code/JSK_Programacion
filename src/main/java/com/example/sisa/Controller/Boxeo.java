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
import javafx.scene.control.DateCell;
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

import static com.example.sisa.Controller.Controller.*;
import static com.example.sisa.Controller.Calendario.*;

public class Boxeo implements Initializable {

    private Calendario calendarioController;
    @FXML
    private DatePicker fechaDisponible;
    @FXML
    private AnchorPane anchorPane;

    private static ObservableList<Jugadores> jugadores = FXCollections.observableArrayList();

    public ObservableList<Jugadores> getJugadores() {
        return jugadores;
    }


    @FXML
    private void verMasBoxeo1(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sisa/Boxeo1.fxml"));
            Parent baloncesto1 = loader.load();

            anchorPane.getChildren().setAll(baloncesto1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void verMasBoxeo2(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sisa/Boxeo2.fxml"));
            Parent baloncesto2 = loader.load();

            anchorPane.getChildren().setAll(baloncesto2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void eventJugador1(ActionEvent actionEvent) {
        String nombreUsuarioActual = getUsuario_Actual();
        if (nombreUsuarioActual == null) {
            System.out.println("No es posible obtener el nombre del usuario actual.");
            return;
        }
        nombreUsuarioActual = nombreUsuarioActual.trim();

        String nombre = null;
        String apellido = null;

        String[] datos = cargarDatosRegistro(nombreUsuarioActual);
        if (datos != null) {
            nombre = datos[0];
            apellido = datos[1];
            Posiciones posicion = Posiciones.Luchador1;



            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar Acción");
            alert.setHeaderText("¿SEGURO QUE QUIERES CONTINUAR?");
            alert.setContentText("Accederás a la tabla calendário para eligir el día del partido.");

            ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

            if (result == ButtonType.OK) {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/com/example/sisa/Calendario.fxml"));
                    Parent root = loader.load();

                    // Obtenha o controlador e defina o nome, sobrenome e posição
                    Calendario calendarioController = loader.getController();
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
            System.out.println("Não foi possível obter os dados do registro.");
            return;
        }
    }



    @FXML
    public void eventJugador2(ActionEvent actionEvent) {
        String nombreUsuarioActual = getUsuario_Actual();
        if (nombreUsuarioActual == null) {
            System.out.println("No es posible obtener el nombre del usuario actual.");
            return;
        }
        nombreUsuarioActual = nombreUsuarioActual.trim();

        String nombre = null;
        String apellido = null;

        String[] datos = cargarDatosRegistro(nombreUsuarioActual);
        if (datos != null) {
            nombre = datos[0];
            apellido = datos[1];
            Posiciones posicion = Posiciones.Luchador2;



            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar Acción");
            alert.setHeaderText("¿SEGURO QUE QUIERES CONTINUAR?");
            alert.setContentText("Accederás a la tabla calendário para eligir el día del partido.");

            ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

            if (result == ButtonType.OK) {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/com/example/sisa/Calendario.fxml"));
                    Parent root = loader.load();

                    // Obtenha o controlador e defina o nome, sobrenome e posição
                    Calendario calendarioController = loader.getController();
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
            System.out.println("Não foi possível obter os dados do registro.");
            return;
        }

    }



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
        String rutaFichero = "src/main/java/com/example/sisa/Ficheros/Jugadores.txt";
        try (FileWriter writer = new FileWriter(rutaFichero,true)) {
            for (Jugadores jugador : jugadores) {
                writer.append(jugador.getNombre() + ";" + jugador.getApellido() + ";" + jugador.getPosiciones() + ";" + jugador.getFechaDisponible() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al escribir en el archivo: " + e.getMessage());
        }
    }

    public static void cargarJogadores(Calendario calendario) {
        jugadores.clear();
        String rutaFichero = "src/main/java/com/example/sisa/Ficheros/Jugadores.txt";
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

            // Depois de carregar todos os jogadores, você pode chamar o método handleDateSelection
            calendario.handleDateSelection(null); // Passe null se não tiver um ActionEvent

        } catch (FileNotFoundException e) {
            throw new RuntimeException("Arquivo não encontrado: " + rutaFichero, e);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao carregar jogadores", e);
        }
    }



    public LocalDate getSelectedDate() {
        if (fechaDisponible != null) {
            return fechaDisponible.getValue();
        } else {
            System.out.println("DatePicker não foi inicializado corretamente.");
            return null;
        }
    }

}
