package com.espe.parqueadero.Controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import com.espe.parqueadero.View.View;

public class LoginController {
    private View viewApp = new View();
    @FXML
    private Button btnSalir;
    @FXML
    private void handleBtnIngreso(ActionEvent event) {

        viewApp.navigateToPage("/Fxml/ingresar.fxml", event);
    }

    @FXML
    private void handleBtnListado(ActionEvent event) {

        viewApp.navigateToPage("/Fxml/listado.fxml", event);
    }

    @FXML
    private void handleBtnRetiro(ActionEvent event) {
        // Cargar la nueva página (FXML) para la recuperación de contraseña
        viewApp.navigateToPage("/Fxml/retirarActualizar.fxml", event);
    }

    @FXML
    private void handleBtnSalir(ActionEvent event) {
        // Cerrar la aplicación
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }

}


