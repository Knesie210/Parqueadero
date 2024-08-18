package com.espe.parqueadero.Controllers.Vehicule;

import com.espe.parqueadero.Models.MongoDB;
import com.espe.parqueadero.Models.Vehiculo;
import com.espe.parqueadero.Models.VehiculoCRUD;
import com.espe.parqueadero.View.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

import static com.espe.parqueadero.Controllers.Vehicule.IngresarController.showAlert;

public class ListadoController {
    @FXML
    public Button btnEditar;
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
    @FXML
    private Button btnEliminar;

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
                buscarVehiculo();
            }
        });
        btnEliminar.setOnAction(this::handleBtnEliminar);
        btnEditar.setOnAction(this::handleBtnEditar);

        placa.setCellValueFactory(new PropertyValueFactory<>("placa"));
        propietario.setCellValueFactory(new PropertyValueFactory<>("propietario"));
        tipoVehiculo.setCellValueFactory(new PropertyValueFactory<>("tipoVehiculo"));
        horaEntrada.setCellValueFactory(new PropertyValueFactory<>("horaEntrada"));
        horaSalida.setCellValueFactory(new PropertyValueFactory<>("horaSalida"));


        placa.setCellFactory(TextFieldTableCell.forTableColumn());
        propietario.setCellFactory(TextFieldTableCell.forTableColumn());
        placa.setEditable(true);
        propietario.setEditable(true);

        placa.setOnEditCommit(this::handleEdit);
        propietario.setOnEditCommit(this::handleEdit);


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
    private void handleBtnEliminar(ActionEvent event) {
        eliminarVehiculoSeleccionado();
    }

    @FXML
    private void handleBtnEditar(ActionEvent event) {
        tableVehicule.setEditable(true);
    }

    private void buscarVehiculo() {
        String criterioBusqueda = tFieldBuscar.getText().trim();

        if (criterioBusqueda.isEmpty()) {
            listarVehiculos();  // Si el campo de búsqueda está vacío, listar todos los vehículos
        } else {
            List<Vehiculo> resultados = vehiculoCRUD.buscarVehiculos(criterioBusqueda);
            tableVehicule.getItems().setAll(resultados);
        }
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

    private void eliminarVehiculoSeleccionado() {
        Vehiculo vehiculoSeleccionado = tableVehicule.getSelectionModel().getSelectedItem();
        if (vehiculoSeleccionado != null) {
            vehiculoCRUD.eliminarVehiculo(vehiculoSeleccionado.getPlaca());
            listarVehiculos();
            showAlert(Alert.AlertType.INFORMATION, "Eliminación Exitosa", "El vehículo ha sido eliminado correctamente.");
        } else {
            showAlert(Alert.AlertType.WARNING, "Ninguna Selección", "Por favor, selecciona un vehículo para eliminar.");
        }
    }

    @FXML
    private void handleEdit(TableColumn.CellEditEvent<Vehiculo, String> event) {
        Vehiculo vehiculo = event.getRowValue();
        String nuevoValor = event.getNewValue();
        String columnaEditada = event.getTableColumn().getId();

        if (columnaEditada.equals("placa")) {
            vehiculo.setPlaca(nuevoValor);
        } else if (columnaEditada.equals("propietario")) {
            vehiculo.setPropietario(nuevoValor);
        }

        vehiculoCRUD.actualizarDatosVehiculo(vehiculo.getPlaca(), vehiculo);

        listarVehiculos(); // Actualiza la tabla para reflejar los cambios
    }

}
