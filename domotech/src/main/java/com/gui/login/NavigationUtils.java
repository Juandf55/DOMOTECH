package com.gui.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class NavigationUtils {

    /**
     * Switches the view in the current stage by replacing the root of the current
     * scene.
     * This preserves the stage properties (like full screen) and prevents resizing
     * glitches.
     *
     * @param event    The event that triggered the switch (used to find the stage).
     * @param fxmlPath The path to the FXML file to load.
     */
    public static void switchView(ActionEvent event, String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(NavigationUtils.class.getResource(fxmlPath));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Replaces the root, keeping the same Scene and Stage properties
            stage.getScene().setRoot(root);

            // Ensure we are still in full screen if needed, though usually setRoot
            // preserves it better than setScene
            if (!stage.isFullScreen()) {
                stage.setFullScreen(true);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading view: " + fxmlPath);
        }
    }
}
