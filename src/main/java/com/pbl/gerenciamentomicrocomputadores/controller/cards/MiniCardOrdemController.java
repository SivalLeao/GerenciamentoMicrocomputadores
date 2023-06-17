package com.pbl.gerenciamentomicrocomputadores.controller.cards;

import com.pbl.gerenciamentomicrocomputadores.model.OrdemDeServico;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MiniCardOrdemController {

    @FXML private Label idOrdem;
    @FXML private Label tipoDeServico;

    public void setInfo (OrdemDeServico ordemDeServico) {

        idOrdem.setText(Integer.toString(ordemDeServico.getIdOrdem()));

        if (ordemDeServico.getDescricaoServico().getTipoDeServico().equals("Montagem/Instalação")) {

            tipoDeServico.setText(" Montagem/\n Instalação");
        }
        else if (ordemDeServico.getDescricaoServico().getTipoDeServico().equals("Sistema operacional")) {

            tipoDeServico.setText(" Sistema\n Operacional");
        }
        else {

            tipoDeServico.setText(" " + ordemDeServico.getDescricaoServico().getTipoDeServico());
        }

    }

}
