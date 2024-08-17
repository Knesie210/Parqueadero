package com.espe.parqueadero.View;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class View {
    public void showLogin(Stage stage) {
        navigateToPage("/Fxml/login.fxml", stage);
    }

    public void showIngresar(Stage stage) {
        navigateToPage("/Fxml/ingresar.fxml", stage);
    }

    public void showListado(Stage stage) {
        navigateToPage("/Fxml/listado.fxml", stage);
    }

    public void showRetirarActualizar(Stage stage) {
        navigateToPage("/Fxml/retirarActualizar.fxml", stage);
    }

    private void navigateToPage(String fxmlFile, Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void navigateToPage(String fxmlFile, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
