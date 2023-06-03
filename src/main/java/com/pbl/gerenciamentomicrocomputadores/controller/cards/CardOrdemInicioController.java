package com.pbl.gerenciamentomicrocomputadores.controller.cards;

import com.pbl.gerenciamentomicrocomputadores.model.OrdemDeServico;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CardOrdemInicioController {

    @FXML private Label idOrdem;
    @FXML private Label statusOrdem;

    public void setInfo (OrdemDeServico ordemDeServico) {

        idOrdem.setText(Integer.toString(ordemDeServico.getIdOrdem()));
        statusOrdem.setText(ordemDeServico.getStatusAtual());

    }

}
