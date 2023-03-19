module com.pbl.gerenciamentomicrocomputadores {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.pbl.gerenciamentomicrocomputadores to javafx.fxml;
    exports com.pbl.gerenciamentomicrocomputadores;
}