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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class TecnicoController {

    @FXML
    private Label user;
    @FXML
    private VBox vboxPrincipal;
    @FXML
    public Button logout;
    @FXML
    private VBox vboxPrincipal2;

    private Usuario usuario = LoginController.usuarioActivo;
    @FXML
    public Button gastos;
    @FXML
    public Button controltecnico;
    @FXML
    public Button chat;

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
    private void setControltecnico(ActionEvent event) {
        NavigationUtils.switchView(event, "/com/gui/login/menu_alertas.fxml");
    }

    @FXML
    private void chat(ActionEvent event) {
        NavigationUtils.switchView(event, "/com/gui/login/chat.fxml");
    }

    @FXML
    private void handleSaldoButtonAction2(ActionEvent event) {
        NavigationUtils.switchView(event, "/com/gui/login/saldo_view.fxml");
    }

    @FXML
    private void VolverLogin(ActionEvent event) throws IOException {
        // Cargar la ventana de inicio presidente.fxml
        NavigationUtils.switchView(event, "/com/gui/login/login.fxml");

    }

}
