package com.example.sisa.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;


import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Scanner;

import static com.example.sisa.Controller.Inicio.Crear_y_comprobar_fichero;
import static com.example.sisa.Controller.MenuLateral.colocar_el_usuario_Actual;

/**
 * Clase Puntos que implementa la interfaz Initializable.
 * Esta clase se encarga de manejar la lógica de la visualización de puntos en un gráfico de barras.
 */

public class Puntos implements Initializable {
    @FXML
    private BarChart<String, Number> tablaPuntos;

    /**
     * Método que se ejecuta al inicializar la clase.
     * Se encarga de limpiar los datos antiguos del gráfico, obtener los puntos del usuario actual y
     * añadirlos al gráfico.
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Limpiar los datos antiguos
        tablaPuntos.getData().clear();
        // Obtener los puntos del usuario actual
        Object[] puntos = verPuntos(colocar_el_usuario_Actual());

        // Configurar el título del gráfico
        tablaPuntos.setTitle("Puntos de los partidos jugados");
        // Crear una nueva serie de datos para el gráfico con los datos del usuario actual y actualizar el gráfico
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(colocar_el_usuario_Actual());
        // Añadir los puntos al gráfico
        series.getData().add(new XYChart.Data<>("PUNTOS POR INSCRIBIRTE",(Number)puntos[1]));
        series.getData().add(new XYChart.Data<>("PUNTOS POR GANAR ", (Number)puntos[2]));
        series.getData().add(new XYChart.Data<>("PUNTOS POR USUARIO VIP", (Number)puntos[3]));
        series.getData().add(new XYChart.Data<>("PUNTOS TOTALES", (Number)puntos[4]));
        // Añadir la serie de datos al gráfico
        tablaPuntos.getData().add(series);
    }
    /**
     * Método para obtener los puntos de un usuario.
     * Lee el fichero de puntos, busca la línea correspondiente al usuario y devuelve los puntos.
     * @param nombre El nombre del usuario.
     * @return Un array de objetos con los puntos del usuario.
     */
    public static Object[] verPuntos(String nombre) {
        // Ruta del fichero de puntos
        String rutaFichero = "src/main/java/com/example/sisa/Ficheros/Puntos.txt";
        File fichero = new File(rutaFichero.trim());
        // Comprobar si el fichero existe
        if (Crear_y_comprobar_fichero(rutaFichero)) {
            Scanner lector = null;
            try {
                // Leer el fichero
                lector = new Scanner(fichero);
                while (lector.hasNext()) {
                    String linea = lector.nextLine();
                    String[] partes = linea.split(",");
                    if (partes.length == 4) {
                        String usuario = partes[0].trim();
                        int punto1 = Integer.parseInt(partes[1]);
                        int punto2 = Integer.parseInt(partes[2]);
                        int punto3 = Integer.parseInt(partes[3]);
                        int punto4 = punto1 + punto2 + punto3;


                        // Crear un array con los puntos
                        Object[] pepe = new Object[]{usuario, punto1, punto2, punto3, punto4};

                        // Si el usuario es el que estamos buscando, devolver los puntos
                        return pepe;


                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("No encuentro el fichero " + e.getMessage());
            }
        } else {
            System.out.println("no entro");
        }
        return null;
    }



}
