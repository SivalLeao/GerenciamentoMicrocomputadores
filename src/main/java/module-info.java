module com.pbl.gerenciamentomicrocomputadores {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.pbl.gerenciamentomicrocomputadores to javafx.fxml;
    exports com.pbl.gerenciamentomicrocomputadores;
    exports com.pbl.gerenciamentomicrocomputadores.controller;
    opens com.pbl.gerenciamentomicrocomputadores.controller to javafx.fxml;
    exports com.pbl.gerenciamentomicrocomputadores.controller.cards;
    opens com.pbl.gerenciamentomicrocomputadores.controller.cards to javafx.fxml;
    exports com.pbl.gerenciamentomicrocomputadores.controller.paginasprincipais;
    opens com.pbl.gerenciamentomicrocomputadores.controller.paginasprincipais to javafx.fxml;
    exports com.pbl.gerenciamentomicrocomputadores.controller.paginascadastrar;
    opens com.pbl.gerenciamentomicrocomputadores.controller.paginascadastrar to javafx.fxml;
    exports com.pbl.gerenciamentomicrocomputadores.controller.modificarpeca;
    opens com.pbl.gerenciamentomicrocomputadores.controller.modificarpeca to javafx.fxml;
    exports com.pbl.gerenciamentomicrocomputadores.controller.relatorio;
    opens com.pbl.gerenciamentomicrocomputadores.controller.relatorio to javafx.fxml;
    exports com.pbl.gerenciamentomicrocomputadores.controller.login;
    opens com.pbl.gerenciamentomicrocomputadores.controller.login to javafx.fxml;
    exports com.pbl.gerenciamentomicrocomputadores.controller.confirmacao;
    opens com.pbl.gerenciamentomicrocomputadores.controller.confirmacao to javafx.fxml;
    exports com.pbl.gerenciamentomicrocomputadores.controller.mensagem;
    opens com.pbl.gerenciamentomicrocomputadores.controller.mensagem to javafx.fxml;
    exports com.pbl.gerenciamentomicrocomputadores.controller.finalizacaoservico;
    opens com.pbl.gerenciamentomicrocomputadores.controller.finalizacaoservico to javafx.fxml;
}