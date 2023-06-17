package com.pbl.gerenciamentomicrocomputadores.controller.paginascadastrar;

import com.pbl.gerenciamentomicrocomputadores.MainApplication;
import com.pbl.gerenciamentomicrocomputadores.controller.MainController;
import com.pbl.gerenciamentomicrocomputadores.controller.mensagem.MensagemController;
import com.pbl.gerenciamentomicrocomputadores.controller.paginasprincipais.TecnicoController;
import com.pbl.gerenciamentomicrocomputadores.dao.DAO;
import com.pbl.gerenciamentomicrocomputadores.model.Tecnico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class CadastrarTecnicoController {

    @FXML private Button cadastrarBotao;
    @FXML private Button voltarBotao;

    @FXML private TextField nomeTecnico;
    @FXML private TextField enderecoTecnico;
    @FXML private TextField cpfTecnico;
    @FXML private TextField telefoneTecnico;

    @FXML private Label mensagemDeErroNome;
    @FXML private Label mensagemDeErroEndereco;
    @FXML private Label mensagemDeErroCpf;
    @FXML private Label mensagemDeErroTelefone;

    @FXML
    void cadastrarAcao (ActionEvent event) {

        if (validarEntradas() == 0) {

            if (DAO.getTecnico().checarPorCpf(cpfTecnico.getText())) {

                mensagemDeErroCpf.setText("CPF já cadastrado.");
            }
            else {

                Tecnico tecnico = new Tecnico(nomeTecnico.getText(), enderecoTecnico.getText(), telefoneTecnico.getText(),
                        cpfTecnico.getText());

                nomeTecnico.setText("");
                enderecoTecnico.setText("");
                telefoneTecnico.setText("");
                cpfTecnico.setText("");

                DAO.getTecnico().criar(tecnico);

                FXMLLoader fxmlLoader = MainController.getFXMLLoaderPrincipal();
                String classeController = fxmlLoader.getController().getClass().getSimpleName();

                if (classeController.equals("TecnicoController")) {

                    TecnicoController tecnicoController = fxmlLoader.getController();
                    tecnicoController.atualizarCards();
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
                    mensagemController.setMensagem("Técnico cadastrado.");

                    stage.show();

                }
                catch (java.io.IOException e) {

                }

            }
        }

    }

    public int validarEntradas () {

        int qtdErros = 0;

        if (! ((nomeTecnico.getText().matches("^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$"))
                && (nomeTecnico.getText().replaceAll("\\s+", "").length() >= 3))) {

            mensagemDeErroNome.setText("Apenas letras. Ao menos 3 caracteres.");
            qtdErros++;
        }
        else {
            mensagemDeErroNome.setText("");
        }

        if (! ((enderecoTecnico.getText().replaceAll("\\s+", "").length() >= 3))) {

            mensagemDeErroEndereco.setText("Ao menos 3 caracteres.");
            qtdErros++;
        }
        else {
            mensagemDeErroEndereco.setText("");
        }

        if (! ((cpfTecnico.getText().matches("^[0-9]+$")) &&
                (cpfTecnico.getText().length() == 11)))  {

            mensagemDeErroCpf.setText("Apenas números. Deve conter 11 caracteres.");
            qtdErros++;
        }
        else {
            mensagemDeErroCpf.setText("");
        }

        if (! ((telefoneTecnico.getText().matches("^[0-9]+$")) &&
                (telefoneTecnico.getText().length() == 11)))  {

            mensagemDeErroTelefone.setText("Apenas números. Deve conter 11 caracteres.");
            qtdErros++;
        }
        else {
            mensagemDeErroTelefone.setText("");
        }

        return qtdErros;
    }

    @FXML
    void fecharAbaCadastrar(ActionEvent event) {

        nomeTecnico.setText("");
        enderecoTecnico.setText("");
        telefoneTecnico.setText("");
        cpfTecnico.setText("");

        mensagemDeErroNome.setText("");
        mensagemDeErroEndereco.setText("");
        mensagemDeErroTelefone.setText("");
        mensagemDeErroCpf.setText("");

        Stage stage = (Stage) voltarBotao.getScene().getWindow();
        stage.close();

    }

}
