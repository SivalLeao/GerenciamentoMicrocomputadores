package com.pbl.gerenciamentomicrocomputadores.controller.cards;

import com.pbl.gerenciamentomicrocomputadores.controller.MyListener;
import javafx.fxml.FXML;

import com.pbl.gerenciamentomicrocomputadores.model.OrdemDeServico;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class CardOrdemController {

    @FXML private Label idOrdem;
    @FXML private Label statusOrdem;

    @FXML private Pane paneLimpeza;
    @FXML private Pane paneMontagem;
    @FXML private Pane paneProgramas;
    @FXML private Pane paneSO;

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
        statusOrdem.setText(ordemDeServico.getStatusAtual());

        paneMontagem.setVisible(false);
        paneSO.setVisible(false);
        paneProgramas.setVisible(false);
        paneLimpeza.setVisible(false);

        if (ordemDeServico.getDescricaoServico().getTipoDeServico().equals("Montagem/Instalação")) {
            paneMontagem.setVisible(true);
        }
        else if (ordemDeServico.getDescricaoServico().getTipoDeServico().equals("Sistema operacional")) {
            paneSO.setVisible(true);
        }
        else if (ordemDeServico.getDescricaoServico().getTipoDeServico().equals("Programas")) {
            paneProgramas.setVisible(true);
        }
        else if (ordemDeServico.getDescricaoServico().getTipoDeServico().equals("Limpeza")) {
            paneLimpeza.setVisible(true);
        }

    }

}
