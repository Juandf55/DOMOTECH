package com.gui.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class SaldoControlador {

    @FXML
    private Label lbl_validation;
    @FXML
    private Label lbl_validation1;
    @FXML
    private Label lbl_validation2;

    private final Random random = new Random();

    public void initialize() {
        // Asignar números aleatorios a los labels al iniciar la vista
        lbl_validation.setText(String.valueOf(numeroAleatorio(1000, 40000)));
        lbl_validation1.setText(String.valueOf(numeroAleatorio(1000, 40000)));
        lbl_validation2.setText(String.valueOf(numeroAleatorio(1000, 40000)));
    }

    private int numeroAleatorio(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    @FXML
    private void VolverInicio(ActionEvent event) throws IOException {
        // Cargar la ventana de inicio presidente.fxml
        NavigationUtils.switchView(event, "/com/gui/login/menu_presidente.fxml");
    }
}
