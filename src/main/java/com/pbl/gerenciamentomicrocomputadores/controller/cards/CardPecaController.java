package com.pbl.gerenciamentomicrocomputadores.controller.cards;

import com.pbl.gerenciamentomicrocomputadores.controller.MainController;
import com.pbl.gerenciamentomicrocomputadores.controller.MyListener;
import com.pbl.gerenciamentomicrocomputadores.model.Peca;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class CardPecaController {

    @FXML private Label nomePeca;

    private Peca peca;
    private MyListener<Peca> myListener;

    @FXML
    public void click (MouseEvent mouseEvent) {

        this.myListener.onClickListener(peca);

        if (MainController.getStageConfirmacao() != null) {

            MainController.getStageConfirmacao().close();
        }
        if (MainController.getStageAtualizarPeca() != null) {

            MainController.getStageAtualizarPeca().close();
        }
    }

    public void setInfo (Peca peca, MyListener<Peca> myListener) {

        this.peca = peca;
        this.myListener = myListener;

        nomePeca.setText(peca.getNome());
    }

}
