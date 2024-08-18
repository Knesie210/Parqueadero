package com.espe.parqueadero.Controllers.Vehicule;

import com.espe.parqueadero.Models.MongoDB;
import com.espe.parqueadero.Models.Vehiculo;
import com.espe.parqueadero.Models.VehiculoCRUD;
import com.espe.parqueadero.View.View;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.Objects;

public class IngresarController {

    @FXML
    private TextField tFieldPropietario;
    @FXML
    private TextField tFieldPlacas;
    @FXML
    private ToggleGroup tgTransport;
    @FXML
    private RadioButton btnRdVehiculo;
    @FXML
    private RadioButton btnRdMotocicleta;
    @FXML
    private Button btnIngresar;

    private final VehiculoCRUD vehiculoCRUD;
    private MongoDB mongoDB;
    public IngresarController() {
        MongoDB mongoDB = new MongoDB();
        this.vehiculoCRUD = new VehiculoCRUD(mongoDB.getVehiculoCollection() );
    }

    private final View viewApp = new View();

    @FXML
    private void handleButtonClick(ActionEvent event) {
        viewApp.navigateToPage("/Fxml/login.fxml", event);
    }
    @FXML
    private void initialize() {
        MongoClient client = MongoClients.create("mongodb://localhost:27017");
        mongoDB = new MongoDB();
        tFieldPropietario.setOnKeyPressed(event -> handleEnter(event, tFieldPlacas));
        tFieldPlacas.setOnKeyPressed(event -> handleEnter(event, null));
        btnRdMotocicleta.setToggleGroup(tgTransport);
        btnRdVehiculo.setToggleGroup(tgTransport);
    }

    @FXML
    private void handleBtnIngresar(ActionEvent event) {
        String propietarioText = tFieldPropietario.getText();
        String placasText = tFieldPlacas.getText();
        if (propietarioText.isEmpty() || placasText.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Campos Incompletos", "Por favor, completa todos los campos.");
            return;
        }
        RadioButton selectedRadioButton = (RadioButton) tgTransport.getSelectedToggle();
        String tipoTransporte = selectedRadioButton.getText();
        showAlert(Alert.AlertType.INFORMATION, "Ingreso Exitoso", "Bienvenido disfruta tu visita");

        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setPlaca(placasText);
        vehiculo.setPropietario(propietarioText);
        vehiculo.setTipoVehiculo(tipoTransporte);

        vehiculoCRUD.ingresarVehiculo(vehiculo);
        tFieldPropietario.clear();
        tFieldPlacas.clear();
        tgTransport.selectToggle(null);
//        Stage stage = (Stage) btnIngresar.getScene().getWindow();
//        stage.close();
    }

    public void handleEnter(KeyEvent event, TextField nextTextField) {
        if (Objects.requireNonNull(event.getCode()) == KeyCode.ENTER) {
            if (nextTextField != null) {
                nextTextField.requestFocus();
            } else {
                btnIngresar.requestFocus();
            }
        }
    }
    public static void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

