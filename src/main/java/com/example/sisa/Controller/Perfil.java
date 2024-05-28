package com.example.sisa.Controller;

import com.example.sisa.POO.Usuario;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.sisa.Controller.Controller.getUsuario_Actual;

public class Perfil {
    @FXML
    private Button b_editarCorreo;
    @FXML
    private TextFlow nombreUsuario1;
    @FXML
    private Controller registroController;
    @FXML
    private Button b_editarNombre;
    @FXML
    private Button b_editarUsuario;
    @FXML
    private Button b_editarContraseña;
    @FXML
    private TextField nombreCambiar;
    @FXML
    private TextField usuarioCambiar;
    @FXML
    private TextField contraseñaCambiar;
    @FXML
    private TextField correoCambiar;

    private Usuario usuarioAtual;

    public Perfil() { // Corrigido: o nome do construtor deve corresponder ao nome da classe
        this.registroController = new Controller();
    }

    @FXML
    private BorderPane borderPane;

    public void eventEditarNombre(ActionEvent actionEvent) {
        String novoNome = nombreCambiar.getText().trim();
        usuarioAtual.setNombre(novoNome);
        atualizarRegistro(usuarioAtual);
    }

    public void eventEditarUsuario(ActionEvent actionEvent) throws IOException {
        String novoUsuario = usuarioCambiar.getText().trim();
        String nomeUsuarioAntigo = usuarioAtual.getUsuario();
        usuarioAtual.setUsuario(novoUsuario);
        atualizarNomeUsuarioEmTodosArquivos(nomeUsuarioAntigo, novoUsuario);
    }

    public void eventEditarContraseña(ActionEvent actionEvent) throws IOException {
        String novaSenha = contraseñaCambiar.getText().trim();
        String senhaAntiga = usuarioAtual.getContraseña();
        usuarioAtual.setContraseña(novaSenha);
        // Atualiza a senha apenas do usuarioAtual
        atualizarSenhaUsuarioEmArquivo("src/main/java/com/example/sisa/Ficheros/Usuarios.txt", senhaAntiga, novaSenha, usuarioAtual);
        atualizarRegistro(usuarioAtual);
    }

    public void eventEditarCorreo(ActionEvent actionEvent) {
        String novoCorreo = correoCambiar.getText().trim();
        usuarioAtual.setCorreoElectronico(novoCorreo);
        atualizarRegistro(usuarioAtual);
    }

    @FXML
    public void initialize() {
        ArrayList<Usuario> usuarios = registroController.carregarUsuariosDoRegistro();
        String nomeUsuarioAtual = getUsuario_Actual();
        if (nomeUsuarioAtual == null) {
            System.out.println("Não foi possível obter o nome do usuário atual.");
            return;
        }
        nomeUsuarioAtual = nomeUsuarioAtual.trim();
        for (Usuario u : usuarios) {
            if (u.getUsuario().equals(nomeUsuarioAtual)) {
                usuarioAtual = u; // Atribua o usuário encontrado a usuarioAtual
                break;
            }
        }
        if (usuarioAtual == null) {
            System.out.println("O usuário atual não foi encontrado na lista de usuários.");
            return;
        } else {
            nombreCambiar.setText(usuarioAtual.getNombre());
            usuarioCambiar.setText(usuarioAtual.getUsuario());
            contraseñaCambiar.setText(usuarioAtual.getContraseña());
            correoCambiar.setText(usuarioAtual.getCorreoElectronico());
        }
    }

    public void atualizarRegistro(Usuario usuario) {
        ArrayList<Usuario> usuarios = registroController.carregarUsuariosDoRegistro();
        int index = -1;
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getUsuario().equals(usuario.getUsuario())) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            usuarios.set(index, usuario);
        } else {
            usuarios.add(usuario);
        }

        try {
            escreverUsuariosNoArquivo(usuarios);
        } catch (IOException e) {
            System.out.println("Não foi possível encontrar o arquivo Registro.txt.");
        }
    }

    public void atualizarNomeUsuarioEmTodosArquivos(String nomeUsuarioAntigo, String nomeUsuarioNovo) throws IOException {
        atualizarNomeUsuarioEmArquivo("src/main/java/com/example/sisa/Ficheros/Registro.txt", nomeUsuarioAntigo, nomeUsuarioNovo);
        atualizarNomeUsuarioEmArquivoUsuarioAtual("src/main/java/com/example/sisa/Ficheros/Usuario_Actual.txt", nomeUsuarioAntigo, nomeUsuarioNovo);
        atualizarNomeUsuarioEmArquivoUsuarios("src/main/java/com/example/sisa/Ficheros/Usuarios.txt", nomeUsuarioAntigo, nomeUsuarioNovo);
        atualizarNomeUsuarioEmArquivoPuntos("src/main/java/com/example/sisa/Ficheros/Puntos.txt", nomeUsuarioAntigo, nomeUsuarioNovo);
    }

    private void atualizarNomeUsuarioEmArquivo(String caminhoArquivo, String nomeUsuarioAntigo, String nomeUsuarioNovo) throws IOException {
        File file = new File(caminhoArquivo);
        if (!file.exists()) {
            throw new FileNotFoundException("Arquivo não encontrado: " + caminhoArquivo);
        }

        List<String> linhas = Files.readAllLines(file.toPath());
        List<String> linhasAtualizadas = linhas.stream()
                .map(linha -> linha.replace(";" + nomeUsuarioAntigo + ";", ";" + nomeUsuarioNovo + ";"))
                .collect(Collectors.toList());

        Files.write(file.toPath(), linhasAtualizadas);
    }

    private void atualizarNomeUsuarioEmArquivoUsuarios(String caminhoArquivo, String nomeUsuarioAntigo, String nomeUsuarioNovo) throws IOException {
        File file = new File(caminhoArquivo);
        if (!file.exists()) {
            throw new FileNotFoundException("Arquivo não encontrado: " + caminhoArquivo);
        }

        List<String> linhas = Files.readAllLines(file.toPath());
        List<String> linhasAtualizadas = linhas.stream()
                .map(linha -> linha.startsWith(nomeUsuarioAntigo + ";") ? nomeUsuarioNovo + ";" + linha.split(";", 2)[1] : linha)
                .collect(Collectors.toList());

        Files.write(file.toPath(), linhasAtualizadas);
    }

    private void atualizarNomeUsuarioEmArquivoPuntos(String caminhoArquivo, String nomeUsuarioAntigo, String nomeUsuarioNovo) throws IOException {
        File file = new File(caminhoArquivo);
        if (!file.exists()) {
            throw new FileNotFoundException("Arquivo não encontrado: " + caminhoArquivo);
        }

        List<String> linhas = Files.readAllLines(file.toPath());
        List<String> linhasAtualizadas = linhas.stream()
                .map(linha -> linha.startsWith(nomeUsuarioAntigo + ",") ? nomeUsuarioNovo + "," + linha.split(",", 2)[1] : linha)
                .collect(Collectors.toList());

        Files.write(file.toPath(), linhasAtualizadas);
    }

    private void atualizarNomeUsuarioEmArquivoUsuarioAtual(String caminhoArquivo, String nomeUsuarioAntigo, String nomeUsuarioNovo) throws IOException {
        File file = new File(caminhoArquivo);
        if (!file.exists()) {
            throw new FileNotFoundException("Arquivo não encontrado: " + caminhoArquivo);
        }

        List<String> linhas = Files.readAllLines(file.toPath());
        List<String> linhasAtualizadas = linhas.stream()
                .map(linha -> linha.replace(nomeUsuarioAntigo, nomeUsuarioNovo))
                .collect(Collectors.toList());

        Files.write(file.toPath(), linhasAtualizadas);
    }


    private void escreverUsuariosNoArquivo(ArrayList<Usuario> usuarios) throws IOException {
        File file = new File("src/main/java/com/example/sisa/Ficheros/Registro.txt");
        if (!file.exists()) {
            file.createNewFile();  // Este método lança IOException
        }
        try (PrintWriter writer = new PrintWriter(file)) {
            for (Usuario u : usuarios) {
                writer.println(u.getNombre() + ";" + u.getApellidos() + ";" + u.getCorreoElectronico() + ";" + u.getUsuario() + ";" + u.getContraseña());
            }
        }
    }

    private void atualizarSenhaUsuarioEmArquivo(String caminhoArquivo, String senhaAntiga, String novaSenha, Usuario usuario) throws IOException {
        File file = new File(caminhoArquivo);
        if (!file.exists()) {
            throw new FileNotFoundException("Arquivo não encontrado: " + caminhoArquivo);
        }

        List<String> linhas = Files.readAllLines(file.toPath());
        List<String> linhasAtualizadas = new ArrayList<>();

        for (String linha : linhas) {
            String[] partes = linha.split(";");
            if (partes.length > 1 && partes[1].equals(senhaAntiga) && partes[0].equals(usuario.getUsuario())) {
                partes[1] = novaSenha; // Altera a senha, que está no índice 0
                linhasAtualizadas.add(String.join(";", partes));
            } else {
                linhasAtualizadas.add(linha);
            }
        }

        Files.write(file.toPath(), linhasAtualizadas);
    }

    public void eventMiCuenta(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sisa/menu.fxml"));
            Parent perfil = loader.load();

            borderPane.getChildren().setAll(perfil);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void eventCerrarSesión(ActionEvent event) {
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

    public void eventPerfil(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sisa/Perfil.fxml"));
            Parent perfil = loader.load();

            borderPane.getChildren().setAll(perfil);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
