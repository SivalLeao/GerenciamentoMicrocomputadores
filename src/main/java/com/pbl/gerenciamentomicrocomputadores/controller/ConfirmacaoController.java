package com.pbl.gerenciamentomicrocomputadores.controller;

import com.pbl.gerenciamentomicrocomputadores.controller.paginasprincipais.ClienteController;
import com.pbl.gerenciamentomicrocomputadores.controller.paginasprincipais.EstoqueController;
import com.pbl.gerenciamentomicrocomputadores.controller.paginasprincipais.TecnicoController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ConfirmacaoController {

    @FXML private Label texto;

    @FXML private Button confirmarBotao;
    @FXML private Button cancelarBotao;

    String tipoDeAcao;

    @FXML
    void confirmarAcao(ActionEvent event) {

        FXMLLoader fxmlLoader = MainController.getFXMLLoaderPrincipal();
        String classeController = fxmlLoader.getController().getClass().getSimpleName();

        if (classeController.equals("ClienteController")) {

            ClienteController clienteController = fxmlLoader.getController();

            if (tipoDeAcao.equals("atualizar")) {

                clienteController.atualizarCliente();
            }
            else if (tipoDeAcao.equals("remover")) {

                clienteController.removerCliente();
            }

        }
        else if (classeController.equals("TecnicoController")) {

            TecnicoController tecnicoController = fxmlLoader.getController();

            if (tipoDeAcao.equals("atualizar")) {

                tecnicoController.atualizarPerfil();
            }
            else if (tipoDeAcao.equals("remover")) {

                tecnicoController.removerPerfil();
            }

        }
        else if (classeController.equals("EstoqueController")) {

            EstoqueController estoqueController = fxmlLoader.getController();

            if (tipoDeAcao.equals("atualizar")) {

               FXMLLoader fxmlLoaderAtualizarPeca = MainController.getFXMLLoaderAtualizarPeca();
               AtualizarPecaController atualizarPecaController = fxmlLoaderAtualizarPeca.getController();
               atualizarPecaController.atualizarPeca();
            }
            else if (tipoDeAcao.equals("remover")) {

                estoqueController.removerPeca();
            }

        }

        Stage stage = (Stage) confirmarBotao.getScene().getWindow();
        stage.close();
    }

    @FXML
    void cancelarAcao(ActionEvent event) {

        Stage stage = (Stage) confirmarBotao.getScene().getWindow();
        stage.close();
    }

    public void setTexto (String mensagem) {

        texto.setText(mensagem);
    }

    public void setTipoDeAcao (String acao) {

        tipoDeAcao = acao;
    }

}
