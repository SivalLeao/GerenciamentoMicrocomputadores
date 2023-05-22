package com.pbl.gerenciamentomicrocomputadores.controller;

import com.pbl.gerenciamentomicrocomputadores.MainApplication;
import com.pbl.gerenciamentomicrocomputadores.dao.DAO;
import com.pbl.gerenciamentomicrocomputadores.model.Data;
import com.pbl.gerenciamentomicrocomputadores.model.DescricaoServico;
import com.pbl.gerenciamentomicrocomputadores.model.OrdemDeServico;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class MenuController {

    @FXML
    private Button clienteBotao;

    @FXML
    private Button tecnicoBotao;

    @FXML
    private Button ordemBotao;

    @FXML
    private Button estoqueBotao;

    @FXML
    private TableView<OrdemDeServico> tabelaOrdens;

    private ObservableList<OrdemDeServico> ordensData;

    @FXML
    void initialize () {

        this.ordensData = FXCollections.observableArrayList(DAO.getOrdemDeServico().encontrarTodos());

        TableColumn<OrdemDeServico, Integer> idOrdemCol = new TableColumn<>("ID");
        TableColumn<OrdemDeServico, String> statusCol = new TableColumn<>("Status");
        TableColumn<OrdemDeServico, Integer> idTecnicoCol = new TableColumn<>("ID Técnico");
        TableColumn<OrdemDeServico, Integer> idClienteCol = new TableColumn<>("ID Cliente");
        TableColumn<OrdemDeServico, String> dataInicioCol = new TableColumn<>("Data do pedido");
        TableColumn<OrdemDeServico, String> horarioCol = new TableColumn<>("Horário do pedido");
        TableColumn<OrdemDeServico, String> tipoDeServicoCol = new TableColumn<>("Serviço");

        idOrdemCol.setCellValueFactory(ordensData -> new SimpleIntegerProperty(
                ordensData.getValue().getIdOrdem()).asObject());
        statusCol.setCellValueFactory(ordensData -> new ReadOnlyStringWrapper(
                ordensData.getValue().getStatusAtual()));
        tipoDeServicoCol.setCellValueFactory(ordensData -> new ReadOnlyStringWrapper(
                ordensData.getValue().getDescricaoServico().getTipoDeServico()));
        idTecnicoCol.setCellValueFactory(ordensData -> new SimpleIntegerProperty(
                ordensData.getValue().getIdTecnico()).asObject());
        idClienteCol.setCellValueFactory(ordensData -> new SimpleIntegerProperty(
                ordensData.getValue().getIdCliente()).asObject());

        DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        dataInicioCol.setCellValueFactory(ordensData -> new ReadOnlyStringWrapper((
                ordensData.getValue().getData().getDataInicio().format(formatadorData))));

        DateTimeFormatter formatadorHorario = DateTimeFormatter.ofPattern("HH:mm");
        horarioCol.setCellValueFactory(ordensData -> new ReadOnlyStringWrapper((
                ordensData.getValue().getData().getDataInicio().format(formatadorHorario))));

        this.tabelaOrdens.getColumns().addAll(idOrdemCol, statusCol, tipoDeServicoCol, idTecnicoCol,
                idClienteCol, dataInicioCol, horarioCol);
        this.tabelaOrdens.setItems(this.ordensData);

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


}
