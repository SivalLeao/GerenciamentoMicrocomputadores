package com.pbl.gerenciamentomicrocomputadores.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MensagemController {

    @FXML
    private Label mensagem;

    public void setMensagem (String mensagem) { this.mensagem.setText(mensagem);}

}
