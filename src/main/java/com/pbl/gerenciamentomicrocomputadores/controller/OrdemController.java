package com.pbl.gerenciamentomicrocomputadores.controller;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import com.pbl.gerenciamentomicrocomputadores.MainApplication;
import com.pbl.gerenciamentomicrocomputadores.dao.DAO;
import com.pbl.gerenciamentomicrocomputadores.model.OrdemDeServico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.Label;

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
    private Label idOrdemEscolhida;

    @FXML
    private Label idCliente;

    @FXML
    private Label idTecnico;

    @FXML
    private Label tipoServico;

    @FXML
    private Label statusServico;

    @FXML
    private Label dataPedido;

    private MyListener myListener;

    private List<OrdemDeServico> ordensData;

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

        this.ordensData = DAO.getOrdemDeServico().encontrarTodos();

        if (this.ordensData.size() > 0) {

            setOrdemEscolhida(this.ordensData.get(0));

            this.myListener = new MyListener() {
                @Override
                public void onClickListener(OrdemDeServico ordemDeServico) {

                    setOrdemEscolhida(ordemDeServico);
                }
            };

        }

        try {

            int linhaAtual = 1;
            int colunaAtual = 0;

            for (int i = 0; i < this.ordensData.size(); i++) {

                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("CardOrdemView.fxml"));
                AnchorPane novoCard = fxmlLoader.load();

                CardOrdemController cardOrdemController = fxmlLoader.getController();
                cardOrdemController.setInfo(this.ordensData.get(i), this.myListener);


                if ( colunaAtual == 3) {
                    colunaAtual = 0;
                    linhaAtual++;
                }

                this.gridContainer.add(novoCard, colunaAtual++, linhaAtual);

                this.gridContainer.setMinWidth(Region.USE_COMPUTED_SIZE);
                this.gridContainer.setPrefWidth(Region.USE_COMPUTED_SIZE);
                this.gridContainer.setMaxWidth(Region.USE_COMPUTED_SIZE);

                this.gridContainer.setMinHeight(Region.USE_COMPUTED_SIZE);
                this.gridContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
                this.gridContainer.setMaxHeight(Region.USE_COMPUTED_SIZE);

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

    private void setOrdemEscolhida (OrdemDeServico ordemDeServico) {

        idOrdemEscolhida.setText(Integer.toString(ordemDeServico.getIdOrdem()));
        idCliente.setText(Integer.toString(ordemDeServico.getIdCliente()));
        idTecnico.setText(Integer.toString(ordemDeServico.getIdTecnico()));
        tipoServico.setText(ordemDeServico.getDescricaoServico().getTipoDeServico());
        statusServico.setText(ordemDeServico.getStatusAtual());

        DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        dataPedido.setText(ordemDeServico.getData().getDataInicio().format(formatadorData));

    }

}
