package com.espe.parqueadero.Controllers.Vehicule;

import com.espe.parqueadero.Models.MongoDB;
import com.espe.parqueadero.Models.Vehiculo;
import com.espe.parqueadero.Models.VehiculoCRUD;
import com.espe.parqueadero.View.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListadoController {
    @FXML
    private TableView<Vehiculo> tableVehicule;
    @FXML
    private TableColumn<Vehiculo, String> horaEntrada;
    @FXML
    private TableColumn<Vehiculo, String> horaSalida;
    @FXML
    private TableColumn<Vehiculo, String> placa;
    @FXML
    private TableColumn<Vehiculo, String> propietario;
    @FXML
    private TableColumn<Vehiculo, String> tipoVehiculo;
    @FXML
    private TextField tFieldBuscar;
    @FXML
    private Button btnBuscar;

    private View viewApp = new View();
    private VehiculoCRUD vehiculoCRUD;
    private MongoDB mongoDB;
    @FXML
    private void initialize() {
        mongoDB = new MongoDB();
        vehiculoCRUD = new VehiculoCRUD(MongoDB.getVehiculoCollection());
        listarVehiculos();
        configurarTabla();

        tFieldBuscar.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                btnBuscar.requestFocus();
            }
        });
    }
    @FXML
    private void handleButtonClick(ActionEvent event) {
        viewApp.navigateToPage("/Fxml/login.fxml", event);
    }
    @FXML
    private void handleBtnIngresar(ActionEvent event) {
        viewApp.navigateToPage("/Fxml/ingresar.fxml", event);
    }
    @FXML
    private void handleBtnBuscar(ActionEvent event) {
        listarVehiculosFiltrados(tFieldBuscar.getText());
    }
    @FXML
    public void handleBtnEliminar(ActionEvent event) {
    }
    @FXML
    public void handleBtnEditar(ActionEvent event) {
    }
    private void listarVehiculos() {
        tableVehicule.getItems().setAll(vehiculoCRUD.listarVehiculos());
    }

    private void listarVehiculosFiltrados(String placa) {
        tableVehicule.getItems().setAll(vehiculoCRUD.buscarVehiculos(placa));
    }
    private void configurarTabla() {
        placa.setCellValueFactory(new PropertyValueFactory<>("placa"));
        propietario.setCellValueFactory(new PropertyValueFactory<>("propietario"));
        tipoVehiculo.setCellValueFactory(new PropertyValueFactory<>("tipoVehiculo"));
        horaEntrada.setCellValueFactory(new PropertyValueFactory<>("horaEntrada"));
        horaSalida.setCellValueFactory(new PropertyValueFactory<>("horaSalida"));
    }

}
