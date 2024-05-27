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

public class CalendarioFutbol1 implements Initializable {
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
            System.out.println("DatePicker não foi inicializado corretamente.");
            return LocalDate.of(2024, 05, 29);
        }
    }

    private Jugadores jugador;
//*****************************************************************************************
    Futbol futbol1;

    public CalendarioFutbol1() {
    }

    public CalendarioFutbol1(Futbol futbol1) {
        this.futbol1 = futbol1;
    }

    public void setFutbol1(Futbol futbol1) {
        this.futbol1 = futbol1;
    }

    //**********************************************************************************

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

            // Defina uma data selecionada por padrão
            fechaDisponible.setValue(LocalDate.of(2024, 5, 28));

            fechaDisponible.setOnAction(this::handleDateSelection);
        } else {
            System.out.println("DatePicker não foi inicializado corretamente.");
        }

        // Chame o método loadSecondTabContent
        loadSecondTabContent();

        if (futbol1 != null) {
            Futbol.cargarJogadores(this);
        }
    }

    public void handleDateSelection(ActionEvent actionEvent) {
        if (!areFXMLComponentsInitialized()) {
            System.out.println("Um ou mais campos FXML não foram inicializados corretamente.");
            return;
        }

        LocalDate selectedDate = fechaDisponible.getValue();
        if (selectedDate == null) {
            System.out.println("Nenhuma data foi selecionada.");
            return;
        }

        try {
            List<String> lines = Files.readAllLines(Paths.get("src/main/java/com/example/sisa/Ficheros/Futbol1.txt"));
            long count = lines.stream()
                    .filter(line -> {
                        String[] parts = line.split(",");
                        return parts.length >= 4 && parts[3].trim().equals(selectedDate.toString());
                    })
                    .count();

            if (count >= 2) {
                System.out.println("Já existem 2 jogadores para a data selecionada.");
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        updateDateInfo(selectedDate);
        Boxeo.salvarJogadores();

    }

    private boolean areFXMLComponentsInitialized() {
        return fechaDisponible != null && labelFecha != null && disponibilidad != null;
    }

    private void updateDateInfo(LocalDate selectedDate) {
        this.jugador = new Jugadores(nombre, apellido, posicion, selectedDate); // Use o construtor com argumentos
        String formattedDate = selectedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        labelFecha.setText(selectedDate.toString());
        jugador.setFechaDisponible(selectedDate);

        try {
            List<String> lines = Files.readAllLines(Paths.get("src/main/java/com/example/sisa/Ficheros/Futbol1.txt"));
            long count = lines.stream()
                    .filter(line -> line.contains(formattedDate))
                    .count();

            if (count >= 2) {
                disponibilidad.setText("Indisponível");
                disponibilidad.setDisable(true); // Desabilita o botão
            } else {
                disponibilidad.setText("Disponível");
                disponibilidad.setDisable(false); // Habilita o botão
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("fechaDisponible: " + fechaDisponible.getValue());
    }

    public void loadSecondTabContent() {
        System.out.println("loadSecondTabContent está sendo chamado");

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/sisa/TablaJugadoresFutbol1.fxml"));
            Parent root = loader.load();

            // Inicialize calendarioController
            TablaJugadoresFutbol1 controller = loader.getController();
            controller.setCalendarioController(this);

            segundaVentana.getChildren().setAll(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void eventFecha(ActionEvent actionEvent) {
        selectedDate = fechaDisponible.getValue();
        if (selectedDate != null) {
            System.out.println("Data selecionada: " + selectedDate);
            // Aqui você pode adicionar o código para fazer algo com a data selecionada
        } else {
            System.out.println("Nenhuma data foi selecionada.");
        }

    }

    public void eventYesOrNot(ActionEvent actionEvent) {
        LocalDate selectedDate = fechaDisponible.getValue();
        if (selectedDate == null) {
            System.out.println("Nenhuma data foi selecionada.");
            return;
        }

        // Verifique se o botão está disponível
        if (disponibilidad.getText().equals("Disponível")) {
            // Crie um novo jogador com a data selecionada
            Jugadores newJugador = new Jugadores(nombre, apellido, posicion, selectedDate);

            // Adicione o novo jogador à lista de jogadores
            jugadores.add(newJugador);

            int resultado = posicionOjugadorOcupados(nombre, posicion, selectedDate);
            if (resultado == 1 || resultado == 2) {
                Alert alert = getAlert(resultado);
                alert.show();
                return;
            }

            // Salve o novo jogador no arquivo Jugadores.txt
            try (FileWriter writer = new FileWriter("src/main/java/com/example/sisa/Ficheros/Futbol1.txt", true)) {
                writer.append(newJugador.getNombre() + ";" + newJugador.getApellido() + ";" + newJugador.getPosiciones() + ";" + newJugador.getFechaDisponible() + "\n");
            } catch (IOException e) {
                System.out.println("Ocorreu um erro ao escrever no arquivo: " + e.getMessage());
            }

            // Atualize a informação da data
            updateDateInfo(selectedDate);
        }
    }

    private static Alert getAlert(int resultado) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (resultado == 1) {
            alert.setTitle("Error!");
            alert.setHeaderText("Posición ya ocupada");
            alert.setContentText("Esta posición ya está ocupada por otro jugador, favor elegir otra");
            return alert;
        } else if (resultado == 2) {
            alert.setTitle("Error!");
            alert.setHeaderText("Jugador ya cadastrado");
            alert.setContentText("Ya estás cadastrado en una partida de boxeo en esa fecha");
            return alert;
        }
        return alert;
    }

    private int posicionOjugadorOcupados(String nombreJugador, Posiciones posicion, LocalDate fecha) {
        String rutaFichero = "src/main/java/com/example/sisa/Ficheros/Futbol1.txt";
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

