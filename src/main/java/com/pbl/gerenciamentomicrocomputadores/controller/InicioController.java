package com.pbl.gerenciamentomicrocomputadores.controller;

import com.pbl.gerenciamentomicrocomputadores.MainApplication;
import com.pbl.gerenciamentomicrocomputadores.dao.DAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InicioController {

    @FXML
    private Button clienteBotao;

    @FXML
    private Button tecnicoBotao;

    @FXML
    private Button ordemBotao;

    @FXML
    private Button estoqueBotao;

    @FXML
    private VBox barraInteira;

    @FXML
    private HBox containerBarraRetraida;

    @FXML
    private Pane barraRetraida;

    @FXML
    private Pane paneCantoInicio;

    @FXML
    private Pane paneTecnicoLogado;

    @FXML
    private Button loginBotao;

    @FXML
    private Button cadastrarBotao;

    @FXML
    private Label nomeTecnico;

    @FXML
    private Label idTecnico;

    @FXML
    void initialize () {

        barraInteira.setVisible(false);
        containerBarraRetraida.setVisible(true);

        barraInteira.setOnMouseExited(event -> {barraInteira.setVisible(false);
            containerBarraRetraida.setVisible(true);
        });
        barraRetraida.setOnMouseEntered(event -> {barraInteira.setVisible(true);
            containerBarraRetraida.setVisible(false);
        });

        paneCantoInicio.setVisible(true);
        paneTecnicoLogado.setVisible(false);

    }

    @FXML
    void abaCliente (ActionEvent event) {

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
    void abaTecnico (ActionEvent event) {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("TecnicoView.fxml"));
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
    void abaOrdem (ActionEvent event) {

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
    void abaEstoque (ActionEvent event) {

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
    void abaLogin (ActionEvent event) {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("LoginView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 400, 200);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.getIcons().add(new Image(MainApplication.class.getResourceAsStream("/com/pbl/gerenciamentomicrocomputadores/Icones/Icone.png")));
            stage.setScene(scene);

            LoginController.setStage(stage);

            stage.show();
        }
        catch (java.io.IOException e) {

        }

    }

}
