package com.pbl.gerenciamentomicrocomputadores.controller.paginascadastrar;

import com.pbl.gerenciamentomicrocomputadores.MainApplication;
import com.pbl.gerenciamentomicrocomputadores.controller.MainController;
import com.pbl.gerenciamentomicrocomputadores.controller.MensagemController;
import com.pbl.gerenciamentomicrocomputadores.controller.paginasprincipais.ClienteController;
import com.pbl.gerenciamentomicrocomputadores.dao.DAO;
import com.pbl.gerenciamentomicrocomputadores.model.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class CadastrarClienteController {

    @FXML private Button cadastrarBotao;
    @FXML private Button voltarBotao;

    @FXML private TextField nomeCliente;
    @FXML private TextField enderecoCliente;
    @FXML private TextField cpfCliente;
    @FXML private TextField telefoneCliente;

    @FXML private Label mensagemDeErroNome;
    @FXML private Label mensagemDeErroEndereco;
    @FXML private Label mensagemDeErroCpf;
    @FXML private Label mensagemDeErroTelefone;

    @FXML
    void cadastrarAcao(ActionEvent event) {

        if (validarEntradas() == 0) {

            if (DAO.getCliente().checarPorCpf(cpfCliente.getText())) {

                mensagemDeErroCpf.setText("CPF já cadastrado.");
            }
            else {

                Cliente cliente = new Cliente(nomeCliente.getText(), enderecoCliente.getText(), telefoneCliente.getText(),
                        cpfCliente.getText());

                nomeCliente.setText("");
                enderecoCliente.setText("");
                telefoneCliente.setText("");
                cpfCliente.setText("");

                DAO.getCliente().criar(cliente);

                FXMLLoader fxmlLoader = MainController.getFXMLLoaderPrincipal();
                String classeController = fxmlLoader.getController().getClass().getSimpleName();

                if (classeController.equals("ClienteController")) {

                    ClienteController clienteController = MainController.getFXMLLoaderPrincipal().getController();
                    clienteController.atualizarCards();
                    clienteController.atualizarMiniOrdens();
                    clienteController.esconderMensagensDeErro();
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
                    mensagemController.setMensagem("Cliente Adicionado.");

                    stage.show();

                }
                catch (java.io.IOException e) {

                }
            }
        }

    }

    public int validarEntradas () {

        int qtdErros = 0;

        if (! ((nomeCliente.getText().matches("^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$"))
                && (nomeCliente.getText().replaceAll("\\s+", "").length() >= 3))) {

            mensagemDeErroNome.setText("Apenas letras. Ao menos 3 caracteres.");
            qtdErros++;
        }
        else {
            mensagemDeErroNome.setText("");
        }

        if (! ((enderecoCliente.getText().replaceAll("\\s+", "").length() >= 3))) {

            mensagemDeErroEndereco.setText("Ao menos 3 caracteres.");
            qtdErros++;
        }
        else {
            mensagemDeErroEndereco.setText("");
        }

        if (! ((telefoneCliente.getText().matches("^[0-9]+$")) &&
                (telefoneCliente.getText().length() == 11)))  {

            mensagemDeErroTelefone.setText("Apenas números. Deve conter 11 caracteres.");
            qtdErros++;
        }
        else {
            mensagemDeErroTelefone.setText("");
        }

        if (! ((cpfCliente.getText().matches("^[0-9]+$")) &&
                (cpfCliente.getText().length() == 11)))  {

            mensagemDeErroCpf.setText("Apenas números. Deve conter 11 caracteres.");
            qtdErros++;
        }
        else {
            mensagemDeErroCpf.setText("");
        }

        return qtdErros;
    }

    @FXML
    void fecharAbaCadastrar(ActionEvent event) {

        nomeCliente.setText("");
        enderecoCliente.setText("");
        telefoneCliente.setText("");
        cpfCliente.setText("");

        mensagemDeErroNome.setText("");
        mensagemDeErroEndereco.setText("");
        mensagemDeErroTelefone.setText("");
        mensagemDeErroCpf.setText("");

        Stage stage = (Stage) voltarBotao.getScene().getWindow();
        stage.close();

    }

}
