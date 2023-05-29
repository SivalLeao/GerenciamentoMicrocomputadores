package com.pbl.gerenciamentomicrocomputadores.controller;

import com.pbl.gerenciamentomicrocomputadores.dao.DAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class LoginController {

    @FXML
    private TextField cpfTecnico;

    @FXML
    private Label mensagemDeErro;

    @FXML
    private Button entrarBotao;

    @FXML
    private Button voltarBotao;

    @FXML
    void acessoTecnico (ActionEvent event) {

        if (!(cpfTecnico.getText().matches("^[0-9]+$"))) {

            mensagemDeErro.setText("Entrada inválida");
        }
        else if (DAO.getTecnico().checarPorCpf(cpfTecnico.getText())) {

            FXMLLoader fxmlLoader = MainController.getFXMLLoaderInicio();
            InicioController inicioController = fxmlLoader.getController();
            inicioController.fazendoMudancaLogin(DAO.getTecnico().encontrarPorCpf(cpfTecnico.getText()));
            cpfTecnico.setText("");
            Stage stage = (Stage) voltarBotao.getScene().getWindow();
            stage.close();
        }
        else {

            mensagemDeErro.setText("Usuário não encontrado");
        }

    }

    @FXML
    void fecharAbaLogin (ActionEvent event) {

        Stage stage = (Stage) voltarBotao.getScene().getWindow();
        stage.close();
    }

}
