package com.pbl.gerenciamentomicrocomputadores.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.pbl.gerenciamentomicrocomputadores.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class OrdemController {

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
    private Button tecnicoBotao;

    @FXML
    private GridPane gridContainer;

    @FXML
    private VBox barraInteira;

    @FXML
    private HBox containerBarraRetraida;

    @FXML
    private Pane barraRetraida;

    @FXML
    void initialize() {

        barraInteira.setVisible(false);
        containerBarraRetraida.setVisible(true);

        barraInteira.setOnMouseExited(event -> {barraInteira.setVisible(false);
            containerBarraRetraida.setVisible(true);
        });
        barraRetraida.setOnMouseEntered(event -> {
            barraInteira.setVisible(true);
            containerBarraRetraida.setVisible(false);
        });

        try {

            int linhaAtual = 1;
            int colunaAtual = 0;

            for (int i = 0; i < 10; i++) {

                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("CardOrdemView.fxml"));
                AnchorPane novoCard = fxmlLoader.load();

                if ( colunaAtual == 3) {
                    colunaAtual = 0;
                    linhaAtual++;
                }

                gridContainer.add(novoCard, colunaAtual++, linhaAtual);

                gridContainer.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridContainer.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridContainer.setMaxWidth(Region.USE_COMPUTED_SIZE);

                gridContainer.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridContainer.setMaxHeight(Region.USE_COMPUTED_SIZE);

                GridPane.setMargin(novoCard, new Insets(20));

            }

        }
        catch ( java.io.IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void abaCliente(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("ClienteView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1280, 650);
            Stage stage = MainController.getStageInicio();
            stage.setScene(scene);
            MainController.setStageInicio(stage);
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
            Stage stage = MainController.getStageInicio();
            stage.setScene(scene);
            MainController.setStageInicio(stage);
            stage.show();
        }
        catch (java.io.IOException e) {

        }

    }

    @FXML
    void abaInicio(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("InicioView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1280, 650);
            Stage stage = MainController.getStageInicio();
            stage.setScene(scene);
            MainController.setStageInicio(stage);
            stage.show();
        }
        catch (java.io.IOException e) {

        }
    }

    @FXML
    void abaTecnico(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("TecnicoView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1280, 650);
            Stage stage = MainController.getStageInicio();
            stage.setScene(scene);
            MainController.setStageInicio(stage);
            stage.show();
        }
        catch (java.io.IOException e) {

        }
    }

}
