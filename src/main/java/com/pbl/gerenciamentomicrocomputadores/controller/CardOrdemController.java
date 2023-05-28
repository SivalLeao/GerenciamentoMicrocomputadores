package com.pbl.gerenciamentomicrocomputadores.controller;

import javafx.fxml.FXML;

import com.pbl.gerenciamentomicrocomputadores.model.OrdemDeServico;
import javafx.scene.control.Label;

import javafx.scene.input.MouseEvent;

public class CardOrdemController {

    @FXML
    private Label idOrdem;

    @FXML
    private Label status;

    private OrdemDeServico ordemDeServico;

    private MyListener myListener;

    @FXML
    public void click (MouseEvent mouseEvent) {

        this.myListener.onClickListener(this.ordemDeServico);
    }

    public void setInfo (OrdemDeServico ordemDeServico, MyListener myListener) {

        this.ordemDeServico = ordemDeServico;
        this.myListener = myListener;
        idOrdem.setText(Integer.toString(ordemDeServico.getIdOrdem()));
        status.setText(ordemDeServico.getStatusAtual());
    }

}
