package com.example.sisa.Controller;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Main implements Initializable {


    /**
     * El panel principal que contiene otras vistas en la interfaz.
     */
    @FXML
    private BorderPane borderPanel;


    /**
     * El botón para desplegar el menú lateral.
     */
    @FXML
    private Button botonMenu;


    /**
     * El botón para desplegar el menú de configuración.
     */
    @FXML
    private Button botonConfig;


    /**
     * Estado de visibilidad del menú lateral.
     */
    private boolean MenuVisible = true;


    /**
     * Inicializa el controlador. Este método se llama automáticamente después de cargar el archivo FXML.
     *
     * @param url La ubicación utilizada para resolver rutas relativas del objeto raíz o null si la ubicación no es conocida.
     * @param resourceBundle Los recursos utilizados para localizar el objeto raíz o null si el objeto raíz no fue localizado.
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            FXMLLoader menuLateral = new FXMLLoader(getClass().getResource("/com/example/sisa/menuLateral.fxml"));
            Parent menu = menuLateral.load();
            MenuLateral menuLateralController = menuLateral.getController();
            menuLateralController.setMainBorderPane(borderPanel);

            Parent cabecera = FXMLLoader.load(getClass().getResource("/com/example/sisa/menuInferior.fxml"));
            Parent principal = FXMLLoader.load(getClass().getResource("/com/example/sisa/Principal.fxml"));
            Parent configuracion = FXMLLoader.load(getClass().getResource("/com/example/sisa/menu.fxml"));

            borderPanel.setTop(cabecera);
            borderPanel.setCenter(principal);

            botonMenu = (Button) cabecera.lookup("#botonMenu");
            botonMenu.setOnAction(event -> desplegarMenu(menu));

            botonConfig = (Button) cabecera.lookup("#botonConfig");
            botonConfig.setOnAction(event -> desplegarMenu(configuracion));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Despliega u oculta el menú lateral en el panel principal.
     *
     * @param menu El menú que se mostrará u ocultará.
     */


    private void desplegarMenu(Parent menu) {
        if (MenuVisible) {
            borderPanel.setLeft(null);
        } else {
            borderPanel.setLeft(menu);
        }
        MenuVisible = !MenuVisible;
    }

}
