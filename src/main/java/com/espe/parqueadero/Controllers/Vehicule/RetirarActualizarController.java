package com.espe.parqueadero.Controllers.Vehicule;

import com.espe.parqueadero.Models.MongoDB;
import com.espe.parqueadero.Models.VehiculoCRUD;
import com.espe.parqueadero.View.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Objects;

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
    }
    @FXML
    private void handleBtnRetirar(ActionEvent event) {
        String placasText = tFieldPlacas.getText();
        if (placasText.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Campos Incompletos", "Por favor, ingresa la placa.");
            return;
        }
        vehiculoCRUD.actualizarHoraSalida(placasText);

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
}
