package com.gui.login;

import com.gui.login.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class PresidenteController {

    @FXML
    public Button logout;
    @FXML
    public Button btnGastos;
    @FXML
    private Button AIBtn;
    @FXML
    private Button registrosBtn;
    @FXML
    private Button chatBtn;
    @FXML
    private Label user;
    @FXML
    private VBox vboxPrincipal;
    @FXML
    private VBox vboxPrincipal2;

    private Usuario usuario = LoginController.usuarioActivo;

    @FXML
    private void initialize() {

        vboxPrincipal2.toFront();
        // Establecer el nombre del usuario activo en el Label
        if (usuario != null) {
            user.setText("User: " + usuario.getNombre()); // Asumimos que 'usuario' tiene un método 'getNombre()'
        } else {
            user.setText("User: Desconocido"); // Caso por si el usuario es null
        }
    }

    @FXML
    private void accesoInteligente(ActionEvent event) {

        NavigationUtils.switchView(event, "/com/gui/login/pruebaAcceso.fxml");
    }

    @FXML
    private void VolverLogin(ActionEvent event) throws IOException {
        // Cargar la ventana de inicio presidente.fxml
        NavigationUtils.switchView(event, "/com/gui/login/login.fxml");

    }

    @FXML
    private void AbrirRegistros(ActionEvent event) throws IOException {
        // Cargar la ventana de inicio presidente.fxml
        NavigationUtils.switchView(event, "/com/gui/login/registrosResidente.fxml");

    }

    @FXML
    private void AbrirChat(ActionEvent event) throws IOException {
        // Cargar la ventana de inicio presidente.fxml
        NavigationUtils.switchView(event, "/com/gui/login/chat.fxml");

    }

    @FXML
    private void gastos(ActionEvent event) throws IOException {
        // Cargar la ventana de inicio presidente.fxml
        NavigationUtils.switchView(event, "/com/gui/login/saldo_view.fxml");

    }

}
