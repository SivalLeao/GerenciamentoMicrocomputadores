module com.pbl.gerenciamentomicrocomputadores {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.pbl.gerenciamentomicrocomputadores to javafx.fxml;
    exports com.pbl.gerenciamentomicrocomputadores;
    exports com.pbl.gerenciamentomicrocomputadores.controller;
    opens com.pbl.gerenciamentomicrocomputadores.controller to javafx.fxml;
}