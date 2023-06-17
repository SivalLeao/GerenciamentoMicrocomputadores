package com.pbl.gerenciamentomicrocomputadores.controller.paginascadastrar;

import com.pbl.gerenciamentomicrocomputadores.MainApplication;
import com.pbl.gerenciamentomicrocomputadores.controller.MainController;
import com.pbl.gerenciamentomicrocomputadores.controller.mensagem.MensagemController;
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
import javafx.stage.Stage;

import java.text.Normalizer;

public class CadastrarPecaController {

    @FXML private Button cadastrarBotao;
    @FXML private Button voltarBotao;

    @FXML private TextField nomePeca;
    @FXML private TextField valorPeca;
    @FXML private TextField custoPeca;
    @FXML private TextField qtdPeca;

    @FXML private Label mensagemDeErroNome;
    @FXML private Label mensagemDeErroValor;
    @FXML private Label mensagemDeErroCusto;
    @FXML private Label mensagemDeErroQtd;

    @FXML
    void cadastrarAcao(ActionEvent event) {

        int qtdErros = validarEntradas();

        if (qtdErros == 0) {

            Peca peca = new Peca(nomePeca.getText(), Integer.parseInt(qtdPeca.getText()),
                    Double.parseDouble(valorPeca.getText()), Double.parseDouble(custoPeca.getText()));

            DAO.getPeca().criar(peca);

            limparDados();

            FXMLLoader fxmlLoader = MainController.getFXMLLoaderPrincipal();
            String classeController = fxmlLoader.getController().getClass().getSimpleName();

            if (classeController.equals("EstoqueController")) {

                EstoqueController estoqueController = MainController.getFXMLLoaderPrincipal().getController();
                estoqueController.atualizarCards();
            }

            Stage stage = (Stage) voltarBotao.getScene().getWindow();
            stage.close();

            try {

                fxmlLoader = new FXMLLoader(MainApplication.class.getResource("MensagemView.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                stage = new Stage();
                stage.setResizable(false);
                stage.getIcons().add(new Image(MainApplication.class.getResourceAsStream("/com/pbl/gerenciamentomicrocomputadores/Icones/Icone.png")));
                stage.setScene(scene);

                MensagemController mensagemController = fxmlLoader.getController();
                mensagemController.setMensagem("Peça adicionada ao estoque.");

                stage.show();

            }
            catch (java.io.IOException e) {

            }

        }

    }

    public void limparDados () {

        nomePeca.setText("");
        valorPeca.setText("");
        custoPeca.setText("");
        qtdPeca.setText("");
        mensagemDeErroNome.setText("");
        mensagemDeErroValor.setText("");
        mensagemDeErroCusto.setText("");
        mensagemDeErroQtd.setText("");
    }

    public int validarEntradas () {

        valorPeca.setText(valorPeca.getText().replaceAll(",", "."));
        custoPeca.setText(custoPeca.getText().replaceAll(",", "."));

        int qtdErros = 0;

        if (! (nomePeca.getText().replaceAll("\\s+", "").length() >= 2)) {

            mensagemDeErroNome.setText("Ao menos 2 caracteres.");
            qtdErros++;
        }
        else if (DAO.getPeca().checarPorNome(Normalizer.normalize(nomePeca.getText(), Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase())) {

            mensagemDeErroNome.setText("Peça já cadastrada.");
            qtdErros++;
        }
        else {
            mensagemDeErroNome.setText("");
        }

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

    @FXML
    void fecharAbaCadastrar(ActionEvent event) {

        Stage stage = (Stage) voltarBotao.getScene().getWindow();
        stage.close();

    }

}