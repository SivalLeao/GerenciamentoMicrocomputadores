package com.pbl.gerenciamentomicrocomputadores.controller;

import com.pbl.gerenciamentomicrocomputadores.controller.paginasprincipais.TecnicoController;
import com.pbl.gerenciamentomicrocomputadores.dao.DAO;
import com.pbl.gerenciamentomicrocomputadores.model.OrdemDeServico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PedidoFinalizacaoController {

    @FXML private ChoiceBox<String> choiceBox;
    @FXML private TextField satisfacaoTexto;
    @FXML private Label mensagemDeErroSatisfacao;
    @FXML private Button responderBotao;

    private int idOrdem;

    @FXML
    void initialize() {

        mensagemDeErroSatisfacao.setVisible(false);

        choiceBox.getItems().add("Cartão de crédito");
        choiceBox.getItems().add("Transferência bancária");
        choiceBox.getItems().add("Dinheiro");

        choiceBox.setValue("Cartão de crédito");
    }

    @FXML
    void responderAcao(ActionEvent event) {

        if (satisfacaoTexto.getText().replaceAll("\\s+", "").length() >= 3) {

            mensagemDeErroSatisfacao.setVisible(false);

            if (DAO.getOrdemDeServico().checarPorId(idOrdem)) {

                OrdemDeServico ordemDeServico = DAO.getOrdemDeServico().encontrarPorId(idOrdem);
                ordemDeServico.setFormaPagamento(choiceBox.getValue());
                ordemDeServico.setSatisfacaoCliente(satisfacaoTexto.getText());
                DAO.getOrdemDeServico().atualizar(ordemDeServico);

                TecnicoController tecnicoController = MainController.getFXMLLoaderPrincipal().getController();
                tecnicoController.finalizarServico();

                Stage stage = (Stage) responderBotao.getScene().getWindow();
                stage.close();
            }
        }
        else  {

            mensagemDeErroSatisfacao.setVisible(true);
        }

    }

    public void setIdOrdem (int idOrdem) { this.idOrdem = idOrdem; }

}
