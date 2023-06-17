package com.pbl.gerenciamentomicrocomputadores.controller.cards;

import com.pbl.gerenciamentomicrocomputadores.model.Tecnico;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CardTecnicoController {

    @FXML private Label idTecnico;
    @FXML private Label nomeTecnico;

    public void setInfo (Tecnico tecnico) {

        nomeTecnico.setText(tecnico.getNome());
        idTecnico.setText(Integer.toString(tecnico.getId()));

    }

}