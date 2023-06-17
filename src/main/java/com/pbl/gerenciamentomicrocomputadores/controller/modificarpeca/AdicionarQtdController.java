package com.pbl.gerenciamentomicrocomputadores.controller.modificarpeca;

import com.pbl.gerenciamentomicrocomputadores.MainApplication;
import com.pbl.gerenciamentomicrocomputadores.controller.MainController;
import com.pbl.gerenciamentomicrocomputadores.controller.mensagem.MensagemController;
import com.pbl.gerenciamentomicrocomputadores.controller.paginasprincipais.EstoqueController;
import com.pbl.gerenciamentomicrocomputadores.dao.DAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AdicionarQtdController {

    @FXML private Button adicionarBotao;
    @FXML private Button voltarBotao;

    @FXML private Label nomePeca;
    @FXML private TextField qtdAdicionada;
    @FXML private Label mensagemDeErro;

    @FXML
    void adicionarAcao(ActionEvent event) {

        if (validarQtd() == 1) {

            mensagemDeErro.setText("Entrada inválida.");
        }
        else {

            String mensagem = "";

            if (DAO.getPeca().checarPorNome(nomePeca.getText())) {

                DAO.getPeca().adicionarQuantidade(nomePeca.getText(), Integer.parseInt(qtdAdicionada.getText()));

                FXMLLoader fxmlLoader = MainController.getFXMLLoaderPrincipal();
                String classeController = fxmlLoader.getController().getClass().getSimpleName();

                if (classeController.equals("EstoqueController")) {

                    EstoqueController estoqueController = fxmlLoader.getController();
                    estoqueController.atualizarCards();
                }

                Stage stageAdicionar = (Stage) voltarBotao.getScene().getWindow();
                stageAdicionar.close();

                mensagem = "Quantidade adicionada em " + nomePeca.getText() + ".";
            }
            else {

                mensagem = "Peça não está cadastrada no estoque.";

            }

            try {

                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("MensagemView.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setResizable(false);
                stage.getIcons().add(new Image(MainApplication.class.getResourceAsStream("/com/pbl/gerenciamentomicrocomputadores/Icones/Icone.png")));
                stage.setScene(scene);

                MensagemController mensagemController = fxmlLoader.getController();
                mensagemController.setMensagem(mensagem);

                stage.show();

                Stage stageAdicionar = (Stage) voltarBotao.getScene().getWindow();
                stageAdicionar.close();

                mensagemDeErro.setText("");
                qtdAdicionada.setText("");
            }
            catch (java.io.IOException e) {

            }

        }

    }

    public int validarQtd () {

        int erro = 0;

        try {

            int qtd = Integer.parseInt(qtdAdicionada.getText());

            if (! (qtd > 0)) {

                throw new NumberFormatException();
            }
        }
        catch (NumberFormatException e) {

            erro++;
        }

        return erro;
    }

    @FXML
    void fecharAbaLogin(ActionEvent event) {

        mensagemDeErro.setText("");
        qtdAdicionada.setText("");

        Stage stage = (Stage) voltarBotao.getScene().getWindow();
        stage.close();
    }

    public void setNomePeca (String nomePeca) { this.nomePeca.setText(nomePeca); }

}
