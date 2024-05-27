package com.example.sisa.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;


import java.net.URL;
import java.util.ResourceBundle;

public class Puntos implements Initializable {
    @FXML
    private BarChart<String, Number> tablaPuntos;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        tablaPuntos.setTitle("Puntos de los partidos jugados");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("SISA");
        series.getData().add(new XYChart.Data<>("PUNTOS POR INSCRIBIRTE", 50));
        series.getData().add(new XYChart.Data<>("PUNTOS POR GANAR ", 1));
        series.getData().add(new XYChart.Data<>("PUNTOS POR USUARIO VIP", 30));
        series.getData().add(new XYChart.Data<>("PUNTOS TOTALES", 81));

        tablaPuntos.getData().add(series);
    }
}
