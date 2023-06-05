package com.pbl.gerenciamentomicrocomputadores.controller.paginascadastrar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastrarOrdemController {


    @FXML private Button cadastrarBotao;
    @FXML private Button adicionarItemBotao;
    @FXML private Button voltarBotao;

    @FXML private TextField idCliente;
    @FXML private ChoiceBox<String> choiceBox;
    @FXML private Label listaItens;


    @FXML private Label mensagemDeErroId;

    @FXML
    void initialize() {

        choiceBox.getItems().add("Montagem/Instalação");
        choiceBox.getItems().add("Sistema operacional");
        choiceBox.getItems().add("Programas");
        choiceBox.getItems().add("Limpeza");

        choiceBox.setValue("Limpeza");
    }

    @FXML
    void adicionarItemAcao(ActionEvent event) {

    }

    @FXML
    void cadastrarAcao(ActionEvent event) {

        

    }

    @FXML
    void fecharAbaCadastrar(ActionEvent event) {

        Stage stage = (Stage) voltarBotao.getScene().getWindow();
        stage.close();

    }

}
