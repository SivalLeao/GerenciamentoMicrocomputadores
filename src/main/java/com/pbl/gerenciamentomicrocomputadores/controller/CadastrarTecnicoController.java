package com.pbl.gerenciamentomicrocomputadores.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastrarTecnicoController {

    private static Stage stage;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Cadastrar;

    @FXML
    private TextField cpfTecnico;

    @FXML
    private TextField enderecoTecnico;

    @FXML
    private Label mensagemDeErroCpf;

    @FXML
    private Label mensagemDeErroEndereco;

    @FXML
    private Label mensagemDeErroNome;

    @FXML
    private Label mensagemDeErroTelefone;

    @FXML
    private TextField nomeTecnico;

    @FXML
    private TextField telefoneTecnico;

    @FXML
    private Button voltarBotao;

    @FXML
    void Cadastrar(ActionEvent event) {

    }
    public static Stage getStage () { return stage; }

    public static void setStage (Stage stage) { CadastrarTecnicoController.stage = stage; }
    @FXML
    void fecharAbaCadastrar(ActionEvent event) {
        Stage stage = (Stage) voltarBotao.getScene().getWindow();
        stage.close();
    }
}
