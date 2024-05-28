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
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class CalendarioTenis1 implements Initializable {
    @FXML
    private DatePicker fechaDisponible;
    @FXML
    private Label labelFecha;
    @FXML
    private AnchorPane segundaVentana;
    @FXML
    private Button disponibilidad;

    private static ObservableList<Jugadores> jugadores = FXCollections.observableArrayList();

    public ObservableList<Jugadores> getJugadores() {
        return jugadores;
    }

    private String nombre;
    private String apellido;
    private Posiciones posicion;



    public DatePicker getFechaDisponible() {
        return fechaDisponible;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setPosicion(Posiciones posicion) {
        this.posicion = posicion;
    }

    private LocalDate selectedDate;

    public LocalDate getSelectedDate() {
        if (fechaDisponible != null) {
            return fechaDisponible.getValue();
        } else {
            System.out.println("Datepicker no se ha inicializado correctamente.");
            return LocalDate.of(2024, 05, 29);
        }
    }

    private Jugadores jugador;
    //*****************************************************************************************

    Tenis tenis1;

    public CalendarioTenis1() {
    }

    public CalendarioTenis1(Tenis tenis1) {
        this.tenis1 = tenis1;
    }

    public void setTenis1(Tenis tenis1) {
        this.tenis1 = tenis1;
    }


    //*****************************************************************************************

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (fechaDisponible != null) {
            fechaDisponible.setDayCellFactory(picker -> new DateCell() {
                @Override
                public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);
                    LocalDate specificDate = LocalDate.of(2024, 5, 29);
                    setDisable(empty || date.compareTo(specificDate) < 0);
                }
            });

            // Establece una fecha seleccionada por defecto
            fechaDisponible.setValue(LocalDate.of(2024, 5, 28));

            fechaDisponible.setOnAction(this::handleDateSelection);
        } else {
            System.out.println("Datepicker no se ha inicializado correctamente.");
        }

        // llama el método loadSecondTabContent
        loadSecondTabContent();

        if (tenis1 != null) {
            Tenis.cargarJogadores(this);
        }
    }

    public void handleDateSelection(ActionEvent actionEvent) {
        if (!areFXMLComponentsInitialized()) {
            System.out.println("Uno o más campos FXML no se han inicializado correctamente.");
            return;
        }

        LocalDate selectedDate = fechaDisponible.getValue();
        if (selectedDate == null) {
            System.out.println("No se ha seleccionado ninguna fecha.");
            return;
        }

        try {
            List<String> lines = Files.readAllLines(Paths.get("src/main/java/com/example/sisa/Ficheros/Tenis1.txt"));
            long count = lines.stream()
                    .filter(line -> {
                        String[] parts = line.split(",");
                        return parts.length >= 4 && parts[3].trim().equals(selectedDate.toString());
                    })
                    .count();

            if (count >= 2) {
                System.out.println("Ya hay 2 jugadores para la fecha seleccionada.");
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        updateDateInfo(selectedDate);
        Tenis.salvarJogadores();

    }

    private boolean areFXMLComponentsInitialized() {
        return fechaDisponible != null && labelFecha != null && disponibilidad != null;
    }

    private void updateDateInfo(LocalDate selectedDate) {
        this.jugador = new Jugadores(nombre, apellido, posicion, selectedDate);


        String formattedDate = selectedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        labelFecha.setText(selectedDate.toString());
        jugador.setFechaDisponible(selectedDate);

        try {
            List<String> lines = Files.readAllLines(Paths.get("src/main/java/com/example/sisa/Ficheros/Tenis1.txt"));
            long count = lines.stream()
                    .filter(line -> line.contains(formattedDate))
                    .count();

            if (count >= 2) {
                disponibilidad.setText("Indisponible");
                disponibilidad.setDisable(true); // Deshabilita el botón
            } else {
                disponibilidad.setText("Disponible");
                disponibilidad.setDisable(false); // Activa el botón
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadSecondTabContent() {
        System.out.println("loadSecondTabContent está siendo llamado");

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/sisa/TablaJugadoresTenis1.fxml"));
            Parent root = loader.load();

            // Inicia calendarioController
            TablaJugadoresTenis1 controller = loader.getController();
            controller.setCalendarioController(this);

            segundaVentana.getChildren().setAll(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void eventFecha(ActionEvent actionEvent) {
        selectedDate = fechaDisponible.getValue();
        if (selectedDate != null) {
            System.out.println("Fecha seleccionada: " + selectedDate);

        } else {
            System.out.println("Ninguna fecha fue seleccionada.");
        }

    }

    public void eventYesOrNot(ActionEvent actionEvent) {
        LocalDate selectedDate = fechaDisponible.getValue();
        if (selectedDate == null) {
            System.out.println("Ninguna fecha fue seleccionada.");
            return;
        }

        // Comprueba la disponibilidad del botón.
        if (disponibilidad.getText().equals("Disponible")) {
            // Crea un nuevo jugador con la fecha seleccionada
            Jugadores newJugador = new Jugadores(nombre, apellido, posicion, selectedDate);

            //Añade el nuevo jugador a lista
            jugadores.add(newJugador);

            int resultado = posicionOjugadorOcupados(nombre, posicion, selectedDate);
            if (resultado == 1 || resultado == 2) {
                Alert alert = getAlert(resultado);
                alert.show();
                return;
            }

            // Guarda el nuevo jugador en el archivo Jugadores.txt
            try (FileWriter writer = new FileWriter("src/main/java/com/example/sisa/Ficheros/Tenis1.txt", true)) {
                writer.append(newJugador.getNombre() + ";" + newJugador.getApellido() + ";" + newJugador.getPosiciones() + ";" + newJugador.getFechaDisponible() + "\n");
            } catch (IOException e) {
                System.out.println("Error al escribir en el archivo: " + e.getMessage());
            }

            updateDateInfo(selectedDate);
        }
    }

    private static Alert getAlert(int resultado) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (resultado == 1) {
            alert.setTitle("Error!");
            alert.setHeaderText("Posición ocupada");
            alert.setContentText("Esta posición ya está ocupada por otro jugador, por favor elija otra.");
            return alert;
        } else if (resultado == 2) {
            alert.setTitle("Error!");
            alert.setHeaderText("Jugador ya registrado.");
            alert.setContentText("Ya estás registrado en un partido de fútbol en esa fecha.");
            return alert;
        }
        return alert;
    }

    private int posicionOjugadorOcupados(String nombreJugador, Posiciones posicion, LocalDate fecha) {
        String rutaFichero = "src/main/java/com/example/sisa/Ficheros/Tenis1.txt";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try (Scanner lector = new Scanner(new File(rutaFichero))) {
            while (lector.hasNext()) {
                String linha = lector.nextLine();
                String[] partes = linha.split(";");
                if (partes.length >= 4 && Posiciones.valueOf(partes[2].trim()) == posicion && LocalDate.parse(partes[3].trim(), formatter).isEqual(fecha)) {
                    return 1;
                } else if (partes.length >= 4 && partes[0].trim().equals(nombreJugador) && LocalDate.parse(partes[3].trim(), formatter).isEqual(fecha)) {
                    return 2;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}

