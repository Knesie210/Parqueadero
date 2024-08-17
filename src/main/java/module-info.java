module com.espe.parqueadero {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mongodb.bson;
    requires org.mongodb.driver.core;
    requires org.mongodb.driver.sync.client;
    requires java.desktop;

    opens com.espe.parqueadero.Controllers to javafx.fxml;
    opens com.espe.parqueadero.Controllers.Vehicule to javafx.fxml;
    opens com.espe.parqueadero.View to javafx.fxml;
    opens com.espe.parqueadero.Models;
    exports com.espe.parqueadero;
    exports com.espe.parqueadero.Controllers.Vehicule;
    exports com.espe.parqueadero.View;
    exports com.espe.parqueadero.Models;

}
