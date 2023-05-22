package com.pbl.gerenciamentomicrocomputadores.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.pbl.gerenciamentomicrocomputadores.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class TecnicoController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button clienteBotao;

    @FXML
    private Button estoqueBotao;

    @FXML
    private Button inicioBotao;

    @FXML
    private Button ordemBotao;

    @FXML
    void abaCliente(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("ClienteView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1280, 650);
            Stage stage = MainController.getStage();
            stage.setScene(scene);
            MainController.setStage(stage);
            stage.show();
        }
        catch (java.io.IOException e) {

        }
    }

    @FXML
    void abaEstoque(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("EstoqueView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1280, 650);
            Stage stage = MainController.getStage();
            stage.setScene(scene);
            MainController.setStage(stage);
            stage.show();
        }
        catch (java.io.IOException e) {

        }

    }

    @FXML
    void abaInicio(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("MenuView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1280, 650);
            Stage stage = MainController.getStage();
            stage.setScene(scene);
            MainController.setStage(stage);
            stage.show();
        }
        catch (java.io.IOException e) {

        }
    }

    @FXML
    void abaOrdem(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("OrdemView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1280, 650);
            Stage stage = MainController.getStage();
            stage.setScene(scene);
            MainController.setStage(stage);
            stage.show();
        }
        catch (java.io.IOException e) {

        }
    }

    @FXML
    void initialize() {
        assert clienteBotao != null : "fx:id=\"clienteBotao\" was not injected: check your FXML file 'TecnicoView.fxml'.";
        assert estoqueBotao != null : "fx:id=\"estoqueBotao\" was not injected: check your FXML file 'TecnicoView.fxml'.";
        assert inicioBotao != null : "fx:id=\"inicioBotao\" was not injected: check your FXML file 'TecnicoView.fxml'.";
        assert ordemBotao != null : "fx:id=\"ordemBotao\" was not injected: check your FXML file 'TecnicoView.fxml'.";

    }

}
