package com.example.sisa.Controller;

import com.example.sisa.POO.Jugadores;
import com.example.sisa.POO.Posiciones;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.Scanner;

public class TablaJugadores implements Initializable {
    @FXML
    public TableView<Jugadores> tablaJugadores1;
    @FXML
    public TableColumn<Jugadores, String> columnaJugadores;
    @FXML
    public TableColumn<Jugadores, Posiciones> columnaPosicion;
    @FXML
    public TableColumn<Jugadores, LocalDate> columnaFecha;

    private Calendario calendarioController;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       // start(new Stage());
    }

    public void setCalendarioController(Calendario calendarioController) {
        this.calendarioController = calendarioController;

        start(new Stage());
    }

    private void start(Stage stage) {
       //ObservableList<Jugadores> listaJugadores = calendarioController.getJugadores();

        // Limpia la tabla antes de añadir a los jugadores.
        tablaJugadores1.getItems().clear();

        try (Scanner scanner = new Scanner(new File("src/main/java/com/example/sisa/Ficheros/Jugadores.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(";");
                if (parts.length >= 4) {
                    String nombre = parts[0];
                    String apellido = parts[1];
                    Posiciones posiciones = Posiciones.valueOf(parts[2]);
                    LocalDate fechaDisponible = LocalDate.parse(parts[3]);

                    Jugadores jugador = new Jugadores(nombre, apellido, posiciones, fechaDisponible);

                    // Comprobar si la tabla ya contiene un jugador con el mismo nombre y la misma fecha
                    boolean isDuplicate = tablaJugadores1.getItems().stream()
                            .anyMatch(j -> j.getNombreCompleto().equals(jugador.getNombreCompleto()) && j.getFechaDisponible().equals(jugador.getFechaDisponible()));

                    // Si no es un duplicado, se añade a la tabla
                    if (!isDuplicate) {
                        tablaJugadores1.getItems().add(jugador);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        columnaJugadores.setCellValueFactory(new PropertyValueFactory<Jugadores, String>("nombreCompleto"));
        columnaPosicion.setCellValueFactory(new PropertyValueFactory<Jugadores, Posiciones>("posiciones"));
        columnaFecha.setCellValueFactory(new PropertyValueFactory<Jugadores, LocalDate>("fechaDisponible"));
    }

}
