package com.espe.parqueadero.Controllers.Vehicule;

import com.espe.parqueadero.View.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.Objects;

public class RetirarActualizarController {
    IngresarController ingresarController = new IngresarController();
    @FXML
    private TextField tFieldPlacas;
    @FXML
    private Button btnRetirar;

    private final View viewApp = new View();
    @FXML
    private void handleButtonClick(ActionEvent event) {
        viewApp.navigateToPage("/Fxml/login.fxml", event);
    }

    @FXML
    private void initialize() {
        tFieldPlacas.setOnKeyPressed(event -> ingresarController.handleEnter(event, tFieldPlacas));
    }
    @FXML
    private void handleBtnRetirar(ActionEvent event) {
        String placasText = tFieldPlacas.getText();
        if (placasText.isEmpty()) {
            ingresarController.showAlert(Alert.AlertType.WARNING, "Campos Incompletos", "Por favor, completa todos los campos.");
            return;
        }
        ingresarController.showAlert(Alert.AlertType.INFORMATION,"Salida","Gracias por su visita vuelva pronto");
        Stage stage = (Stage) btnRetirar.getScene().getWindow();
        stage.close();
    }
}
