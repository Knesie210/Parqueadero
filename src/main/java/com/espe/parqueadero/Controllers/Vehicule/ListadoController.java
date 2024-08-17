package com.espe.parqueadero.Controllers.Vehicule;

import com.espe.parqueadero.Models.MongoDB;
import com.espe.parqueadero.Models.Vehiculo;
import com.espe.parqueadero.Models.VehiculoCRUD;
import com.espe.parqueadero.View.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

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

    private VehiculoCRUD vehiculoCRUD;
    private View viewApp = new View();

    @FXML
    private void initialize() {
        // Configurar las columnas de la tabla
        placa.setCellValueFactory(new PropertyValueFactory<>("placa"));
        propietario.setCellValueFactory(new PropertyValueFactory<>("propietario"));
        tipoVehiculo.setCellValueFactory(new PropertyValueFactory<>("tipoVehiculo"));
        horaEntrada.setCellValueFactory(new PropertyValueFactory<>("horaEntrada"));
        horaSalida.setCellValueFactory(new PropertyValueFactory<>("horaSalida"));

        // Configurar el CRUD
        MongoDB mongoDB = new MongoDB();
        vehiculoCRUD = new VehiculoCRUD(MongoDB.getVehiculoCollection());

        // Inicializar tabla
        listarVehiculos();

        tFieldBuscar.setOnKeyPressed(event -> handleEnter(event, btnBuscar));
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
        String buscarText = tFieldBuscar.getText();
        // Buscar vehículos por placa
        List<Vehiculo> vehiculos = vehiculoCRUD.buscarVehiculos(buscarText);
        tableVehicule.getItems().setAll(vehiculos);
    }

    @FXML
    private void handleEnter(KeyEvent event, Object o) {
        if (event.getCode() == KeyCode.ENTER) {
            btnBuscar.requestFocus();
        }
    }

    private void listarVehiculos() {
        List<Vehiculo> vehiculos = vehiculoCRUD.listarVehiculos();
        tableVehicule.getItems().setAll(vehiculos);
    }
}
