package com.gui.login;
import java.util.Scanner;
import java.util.Vector;
import com.gui.login.model.DatabaseConnection;
import com.gui.login.model.Puerta;
import com.gui.login.model.Usuario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class HelloApplication extends Application {

    static ArrayList<Puerta> ListaPuertasPublicas = new ArrayList<Puerta>();


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root); // Cambiar el tamaño de la ventana
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setTitle("DOMOTECH");
        stage.setMinWidth(960); // Ancho mínimo de la ventana
        stage.setMinHeight(540); // Alto mínimo de la ventana
        stage.show();
    }

    public static void main(String[] args) {
            launch();
        }
    }


