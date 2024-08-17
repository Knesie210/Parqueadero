package com.espe.parqueadero;

import com.espe.parqueadero.View.View;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        View viewApp = new View();
        viewApp.showLogin(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}
