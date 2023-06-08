package com.pbl.gerenciamentomicrocomputadores.controller.paginascadastrar;

import com.pbl.gerenciamentomicrocomputadores.controller.MainController;
import com.pbl.gerenciamentomicrocomputadores.controller.paginasprincipais.TecnicoController;
import com.pbl.gerenciamentomicrocomputadores.dao.DAO;
import com.pbl.gerenciamentomicrocomputadores.model.Tecnico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

        int qtdErros = 0;

        if (! ((nomeTecnico.getText().matches("^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$"))
                && (nomeTecnico.getText().replaceAll("\\s+", "").length() >= 3))) {

            mensagemDeErroNome.setText("Apenas letras. Mínimo 3 caracteres.");
            qtdErros++;
        }
        else {
            mensagemDeErroNome.setText("");
        }

        if (! ((enderecoTecnico.getText().replaceAll("\\s+", "").length() >= 3))) {

            mensagemDeErroEndereco.setText("Mínimo 3 caracteres.");
            qtdErros++;
        }
        else {
            mensagemDeErroEndereco.setText("");
        }

        if (! ((cpfTecnico.getText().matches("^[0-9 .-]+$")) &&
                (cpfTecnico.getText().replaceAll("\\s+|\\.+|-+", "").length() == 11)))  {

            mensagemDeErroCpf.setText("Apenas números. Deve conter 11 caracteres.");
            qtdErros++;
        }
        else {
            mensagemDeErroCpf.setText("");
        }

        if (! ((telefoneTecnico.getText().matches("^[0-9() -]+$")) &&
        (telefoneTecnico.getText().replaceAll("\\s+|\\(+|\\)+|-+", "").length() == 11)))  {

            mensagemDeErroTelefone.setText("Apenas números. Deve conter 11 caracteres.");
            qtdErros++;
        }
        else {
            mensagemDeErroTelefone.setText("");
        }

        if (qtdErros == 0) {

            if (DAO.getCliente().checarPorCpf(cpfTecnico.getText())) {

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

            }
        }

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
