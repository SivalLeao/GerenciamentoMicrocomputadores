module com.pbl.gerenciamentomicrocomputadores {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.pbl.gerenciamentomicrocomputadores to javafx.fxml;
    exports com.pbl.gerenciamentomicrocomputadores;
    exports com.pbl.gerenciamentomicrocomputadores.controller;
    opens com.pbl.gerenciamentomicrocomputadores.controller to javafx.fxml;
}