package com.espe.parqueadero.Controllers.Vehicule;

import com.espe.parqueadero.Models.MongoDB;
import com.espe.parqueadero.Models.VehiculoCRUD;
import com.espe.parqueadero.View.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.espe.parqueadero.Controllers.Vehicule.IngresarController.showAlert;

public class RetirarActualizarController {
    @FXML
    private TextField tFieldPlacas;
    @FXML
    private Button btnRetirar;
    @FXML
    private void handleButtonClick(ActionEvent event) {
        viewApp.navigateToPage("/Fxml/login.fxml", event);
    }
    @FXML
    private void initialize() {
        MongoDB mongoDB = new MongoDB();
        vehiculoCRUD = new VehiculoCRUD(MongoDB.getVehiculoCollection());
        btnRetirar.setOnAction(this::handleBtnRetirar);

        Tooltip tooltip = btnRetirar.getTooltip();
        if (tooltip != null) {
            tooltip.setShowDelay(javafx.util.Duration.millis(100));
            tooltip.setHideDelay(javafx.util.Duration.millis(300));
        }
    }
    @FXML
    private void handleBtnRetirar(ActionEvent event) {
        String placasText = tFieldPlacas.getText();
        if (placasText.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Campos Incompletos", "Por favor, ingresa la placa.");
            return;
        }

        String horaSalida = obtenerHoraActual();
        actualizarHoraSalida(placasText, horaSalida);

        showAlert(Alert.AlertType.INFORMATION, "Salida", "Gracias por su visita. Vuelva pronto.");
        Stage stage = (Stage) btnRetirar.getScene().getWindow();
        stage.close();
    }

    private  View viewApp = new View();
    private  VehiculoCRUD vehiculoCRUD;

    public RetirarActualizarController() {
        MongoDB mongoDB = new MongoDB();
        this.vehiculoCRUD = new VehiculoCRUD(mongoDB.getVehiculoCollection());
    }
    private void actualizarHoraSalida(String placas, String horaSalida) {
        vehiculoCRUD.actualizarHoraSalida(placas, horaSalida);
    }

    private String obtenerHoraActual() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }
}
