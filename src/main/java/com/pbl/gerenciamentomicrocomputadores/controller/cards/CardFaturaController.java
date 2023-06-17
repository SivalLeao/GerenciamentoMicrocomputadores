package com.pbl.gerenciamentomicrocomputadores.controller.cards;

import com.pbl.gerenciamentomicrocomputadores.controller.MyListener;
import com.pbl.gerenciamentomicrocomputadores.model.OrdemDeServico;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class CardFaturaController {

    @FXML private Label idOrdem;

    private OrdemDeServico ordemDeServico;
    private MyListener<OrdemDeServico> myListener;

    @FXML
    public void click (MouseEvent mouseEvent) {

        this.myListener.onClickListener(ordemDeServico);

    }

    public void setInfo (OrdemDeServico ordemDeServico, MyListener<OrdemDeServico> myListener) {

        this.ordemDeServico = ordemDeServico;
        this.myListener = myListener;

        idOrdem.setText(Integer.toString(ordemDeServico.getIdOrdem()));
    }

}
