package com.pbl.gerenciamentomicrocomputadores.controller;

import com.pbl.gerenciamentomicrocomputadores.MainApplication;
import com.pbl.gerenciamentomicrocomputadores.controller.paginasprincipais.EstoqueController;
import com.pbl.gerenciamentomicrocomputadores.dao.DAO;
import com.pbl.gerenciamentomicrocomputadores.model.Peca;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AtualizarPecaController {

    @FXML private Button atualizarPecaBotao;
    @FXML private Button voltarBotao;

    @FXML private Label nomePeca;
    @FXML private TextField valorPeca;
    @FXML private TextField custoPeca;
    @FXML private TextField qtdPeca;

    @FXML private Label mensagemDeErroValor;
    @FXML private Label mensagemDeErroCusto;
    @FXML private Label mensagemDeErroQtd;

    @FXML
    void pressed(MouseEvent event) {

        if (MainController.getStageConfirmacao() != null) {

            MainController.getStageConfirmacao().close();
        }

    }

    @FXML
    void atualizarPecaAcao(ActionEvent event) {

        if (validarEntradas() == 0) {

            if (!(DAO.getPeca().checarPorNome(nomePeca.getText()))) {

                try {

                    FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("MensagemView.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    Stage stage = new Stage();
                    stage.setResizable(false);
                    stage.getIcons().add(new Image(MainApplication.class.getResourceAsStream("/com/pbl/gerenciamentomicrocomputadores/Icones/Icone.png")));
                    stage.setScene(scene);

                    MensagemController mensagemController = fxmlLoader.getController();
                    mensagemController.setMensagem("Peça não está cadastrada no estoque.");

                    stage.show();

                    Stage stageAdicionar = (Stage) voltarBotao.getScene().getWindow();
                    stageAdicionar.close();

                    limparDados();
                }
                catch (java.io.IOException e) {

                }
            }
            else {

                try {

                    FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("ConfirmacaoView.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    Stage stage = new Stage();
                    stage.setResizable(false);
                    stage.getIcons().add(new Image(MainApplication.class.getResourceAsStream("/com/pbl/gerenciamentomicrocomputadores/Icones/Icone.png")));
                    stage.setScene(scene);
                    stage.setAlwaysOnTop(true);

                    ConfirmacaoController confirmacaoController = fxmlLoader.getController();
                    confirmacaoController.setTexto("Deseja atualizar a peça?");
                    confirmacaoController.setTipoDeAcao("atualizar");

                    MainController.setStageConfirmacao(stage);

                    stage.show();
                }
                catch (java.io.IOException e) {

                }

            }

        }
    }

    public void atualizarPeca () {

        Peca peca = new Peca(nomePeca.getText(), Integer.parseInt(qtdPeca.getText()),
                Double.parseDouble(valorPeca.getText()), Double.parseDouble(custoPeca.getText()));

        DAO.getPeca().atualizar(peca);

        FXMLLoader fxmlLoader = MainController.getFXMLLoaderPrincipal();
        String classeController = fxmlLoader.getController().getClass().getSimpleName();

        if (classeController.equals("EstoqueController")) {

            EstoqueController estoqueController = fxmlLoader.getController();
            estoqueController.atualizarCards();
        }

        Stage stageAdicionar = (Stage) voltarBotao.getScene().getWindow();
        stageAdicionar.close();

        limparDados();

        try {

            fxmlLoader = new FXMLLoader(MainApplication.class.getResource("MensagemView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.getIcons().add(new Image(MainApplication.class.getResourceAsStream("/com/pbl/gerenciamentomicrocomputadores/Icones/Icone.png")));
            stage.setScene(scene);

            MensagemController mensagemController = fxmlLoader.getController();
            mensagemController.setMensagem("Peça atualizada.");

            stage.show();

        }
        catch (java.io.IOException e) {

        }

    }

    public int validarEntradas () {

        valorPeca.setText(valorPeca.getText().replaceAll(",", "."));
        custoPeca.setText(custoPeca.getText().replaceAll(",", "."));

        int qtdErros = 0;

        try {

            Double valor = Double.parseDouble(valorPeca.getText());

            if (! (valor > 0)) {

                throw new NumberFormatException();
            }
            mensagemDeErroValor.setText("");
        }
        catch ( NumberFormatException e) {

            mensagemDeErroValor.setText("Entrada inválida.");
            qtdErros++;
        }

        try {

            Double custo = Double.parseDouble(custoPeca.getText());

            if (! (custo > 0)) {

                throw new NumberFormatException();
            }
            mensagemDeErroCusto.setText("");
        }
        catch ( NumberFormatException e) {

            mensagemDeErroCusto.setText("Entrada inválida.");
            qtdErros++;
        }

        try {

            int qtd = Integer.parseInt(qtdPeca.getText());

            if (! (qtd > 0)) {

                throw new NumberFormatException();
            }
            mensagemDeErroQtd.setText("");
        }
        catch ( NumberFormatException e) {

            mensagemDeErroQtd.setText("Entrada inválida.");
            qtdErros++;
        }

        return qtdErros;

    }

    public void limparDados () {

        valorPeca.setText("");
        custoPeca.setText("");
        qtdPeca.setText("");
        mensagemDeErroValor.setText("");
        mensagemDeErroCusto.setText("");
        mensagemDeErroQtd.setText("");
    }

    @FXML
    void fecharAbaCadastrar(ActionEvent event) {

        limparDados();

        Stage stage = (Stage) voltarBotao.getScene().getWindow();
        stage.close();
    }

    public void setDadosPeca(String nomePeca) {

        this.nomePeca.setText(nomePeca);
        valorPeca.setText(Double.toString(DAO.getPeca().encontrarPorNome(nomePeca).getValor()));
        custoPeca.setText(Double.toString(DAO.getPeca().encontrarPorNome(nomePeca).getCusto()));
        qtdPeca.setText(Integer.toString(DAO.getPeca().encontrarPorNome(nomePeca).getQuantidade()));
    }

}
