package com.pbl.gerenciamentomicrocomputadores.controller.cards;

import com.pbl.gerenciamentomicrocomputadores.model.OrdemDeServico;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MiniCardOrdemController {

    @FXML
    private Label idOrdem;

    public void setInfo (OrdemDeServico ordemDeServico) {

        idOrdem.setText(Integer.toString(ordemDeServico.getIdOrdem()));
    }

}
