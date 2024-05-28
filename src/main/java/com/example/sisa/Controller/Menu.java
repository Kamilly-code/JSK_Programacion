package com.example.sisa.Controller;

import com.example.sisa.POO.Usuario;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.example.sisa.Controller.Controller.getUsuario_Actual;

public class Menu implements Initializable {
    @FXML
    private Controller registroController;

    private Usuario usuarioAtual;

    public Menu() {
        this.registroController = new Controller();
    }

    @FXML
    private Button b_editarNombre;
    @FXML
    private Button b_editarUsuario;
    @FXML
    private Button b_editarContraseña;
    @FXML
    private TextFlow nombre;
    @FXML
    private TextFlow nombreUsuario2;
    @FXML
    private TextFlow contraseña;
    @FXML
    private TextFlow correoElectronico;
    @FXML
    private Button b_editarCorreo;
    @FXML
    private BorderPane mainBorderPane;

    public void setMainBorderPane(BorderPane mainBorderPane) {
        this.mainBorderPane = mainBorderPane;
    }
    @FXML
    private void eventMiCuenta(ActionEvent event){

    }

    public void eventCerrarSesión(ActionEvent event) throws IOException {
        Object irPerfil = event.getSource();
        Node node = (Node) irPerfil;
        Scene scene1 = node.getScene();
        Window window = scene1.getWindow();
        Stage stage = (Stage) window;
        stage.close();

        Platform.runLater(() -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/sisa/inicio.fxml"));
                Scene scene = new Scene(root);
                Stage newStage = new Stage();
                newStage.setScene(scene);
                newStage.setResizable(false);
                newStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void eventPerfil(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sisa/Perfil.fxml"));
            Parent perfil = loader.load();

            mainBorderPane.getChildren().setAll(perfil);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

     @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
         ArrayList<Usuario> usuarios = registroController.carregarUsuariosDoRegistro();
         String nomeUsuarioAtual = getUsuario_Actual();
         if (nomeUsuarioAtual == null) {
             System.out.println("Não foi possível obter o nome do usuário atual.");
             return;
         }
         nomeUsuarioAtual = nomeUsuarioAtual.trim();
         Usuario usuario = null;
         for (Usuario u : usuarios) {
             if (u.getUsuario().equals(nomeUsuarioAtual)) {
                 usuario = u;
                 break;
             }
         }
         if (usuario == null) {
             System.out.println("O usuário atual não foi encontrado na lista de usuários.");
             return;
         }
         Text text1 = new Text(usuario.getUsuario());
         nombre.getChildren().clear();
         nombre.getChildren().add(text1);

         Text text2 = new Text(usuario.getUsuario());
         nombreUsuario2.getChildren().clear();
         nombreUsuario2.getChildren().add(text2);

         Text text3 = new Text(usuario.getContraseña());
         contraseña.getChildren().clear();
         contraseña.getChildren().add(text3);

         Text text4 = new Text(usuario.getCorreoElectronico());
         correoElectronico.getChildren().clear();
         correoElectronico.getChildren().add(text4);

         Text text5 = new Text(usuario.getNombre() +" "+ usuario.getApellidos());
         nombre.getChildren().clear();
         nombre.getChildren().add(text5);
    }

    public void eventEditarNombre(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sisa/Perfil.fxml"));
            Parent perfil = loader.load();

            mainBorderPane.getChildren().setAll(perfil);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void eventEditarUsuario(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sisa/Perfil.fxml"));
            Parent perfil = loader.load();

            mainBorderPane.getChildren().setAll(perfil);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void eventEditarContraseña(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sisa/Perfil.fxml"));
            Parent perfil = loader.load();

            mainBorderPane.getChildren().setAll(perfil);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void eventEditarCorreo(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sisa/Perfil.fxml"));
            Parent perfil = loader.load();

            mainBorderPane.getChildren().setAll(perfil);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
