package com.gui.login;

import com.gui.login.model.DatabaseConnection;
import com.gui.login.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Vector;

public class RegistrosSensoresController implements Initializable {

    @FXML
    private Label lbl;

    private Usuario user = LoginController.usuarioActivo;
    private int idActivo = LoginController.idActivo;
    private DatabaseConnection db = new DatabaseConnection();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        StringBuilder mensajesEncontrados = new StringBuilder();

        Vector<String> registros = db.obtenerDatosDeSensores();
        for (String mensaje : registros) {
            mensajesEncontrados.append(mensaje).append("\n");
        }

        lbl.setText(mensajesEncontrados.toString());
    }

    @FXML
    public void mostrarRegistro(ActionEvent e) {

    }

    @FXML
    private void volverMenu(ActionEvent event) throws IOException {
        // Cargar la ventana de inicio presidente.fxml
        if (user.getTipo().equals("residente")) {
            NavigationUtils.switchView(event, "/com/gui/login/menu_residente.fxml");
        } else if (user.getTipo().equals("portero")) {
            NavigationUtils.switchView(event, "/com/gui/login/menu_portero.fxml");
        } else if (user.getTipo().equals("tecnico")) {
            NavigationUtils.switchView(event, "/com/gui/login/menu_tecnico.fxml");
        } else if (user.getTipo().equals("presidente")) {
            NavigationUtils.switchView(event, "/com/gui/login/menu_presidente.fxml");
        }

    }
}
