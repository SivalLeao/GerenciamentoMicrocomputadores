package com.pbl.gerenciamentomicrocomputadores.controller;

import javafx.fxml.FXML;

import com.pbl.gerenciamentomicrocomputadores.model.OrdemDeServico;
import javafx.scene.control.Label;

import java.awt.event.MouseEvent;

public class CardOrdemController {

    @FXML
    private Label idOrdem;

    @FXML
    private Label status;

    @FXML
    public void click (MouseEvent event) {

        myListener.onClickListener(ordemDeServico);
    }

    private OrdemDeServico ordemDeServico;

    private MyListener myListener;

    public void setInfo (OrdemDeServico ordemDeServico, MyListener myListener) {

        this.ordemDeServico = ordemDeServico;
        this.myListener = myListener;
        idOrdem.setText(Integer.toString(ordemDeServico.getIdOrdem()));
        status.setText(ordemDeServico.getStatusAtual());
    }

}
