package com.pbl.gerenciamentomicrocomputadores.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MensagemController {

    @FXML private Label mensagem;

    @FXML private Button confirmarBotao;

    @FXML void confirmarAcao(ActionEvent event) {

        Stage stage = (Stage) confirmarBotao.getScene().getWindow();
        stage.close();

    }

    public void setMensagem (String mensagem) { this.mensagem.setText(mensagem);}

}
