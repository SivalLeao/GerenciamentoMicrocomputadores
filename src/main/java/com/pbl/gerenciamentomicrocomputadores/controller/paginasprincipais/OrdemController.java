package com.pbl.gerenciamentomicrocomputadores.controller.paginasprincipais;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pbl.gerenciamentomicrocomputadores.MainApplication;
import com.pbl.gerenciamentomicrocomputadores.controller.MainController;
import com.pbl.gerenciamentomicrocomputadores.controller.MyListener;
import com.pbl.gerenciamentomicrocomputadores.controller.cards.CardClienteController;
import com.pbl.gerenciamentomicrocomputadores.controller.cards.CardOrdemController;
import com.pbl.gerenciamentomicrocomputadores.dao.DAO;
import com.pbl.gerenciamentomicrocomputadores.model.Cliente;
import com.pbl.gerenciamentomicrocomputadores.model.OrdemDeServico;
import com.pbl.gerenciamentomicrocomputadores.model.Tecnico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class OrdemController {

    @FXML private Button inicioBotao;
    @FXML private Button clienteBotao;
    @FXML private Button tecnicoBotao;
    @FXML private Button estoqueBotao;
    @FXML private Button PagamentoBotao;

    @FXML private Pane paneCantoInicio;
    @FXML private Button cadastrarBotao;
    @FXML private Button loginBotao;

    @FXML private Pane paneTecnicoLogado;
    @FXML private Button deslogarBotao;
    @FXML private Label idTecnico;
    @FXML private Label nomeTecnico;

    @FXML private Button todosBotao;
    @FXML private Button emAndamentoBotao;
    @FXML private Button emEsperaBotao;
    @FXML private Button finalizadosBotao;
    @FXML private Button canceladosBotao;
    @FXML GridPane gridContainer;

    @FXML private Label idOrdem;
    @FXML private Label idClienteOrdem;
    @FXML private Label dataPedidoOrdem;
    @FXML private Label dataFinalizacaoOrdem;
    @FXML private Label statusOrdem;
    @FXML private Label tipoDeServicoOrdem;
    @FXML private Label listaPecas;

    private List<OrdemDeServico> ordensData;

    private MyListener<OrdemDeServico> myListener;

    @FXML
    void initialize() {

        paneCantoInicio.setVisible(true);
        paneTecnicoLogado.setVisible(false);

        this.ordensData = DAO.getOrdemDeServico().encontrarTodos();
        atualizarCards(this.ordensData);
    }

    public void atualizarCards (List<OrdemDeServico> ordensData) {

        gridContainer.getChildren().clear();

        if (this.ordensData.size() > 0) {

            setOrdemEscolhida(this.ordensData.get(0));

            this.myListener = new MyListener<OrdemDeServico>() {
                @Override
                public void onClickListener(OrdemDeServico ordemDeServico) {

                    setOrdemEscolhida(ordemDeServico);
                }
            };

        }

        try {

            int colunaAtual = 0;

            for (int i = 0; i < ordensData.size(); i++) {

                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("CardOrdemView.fxml"));
                AnchorPane novoCard = fxmlLoader.load();

                CardOrdemController cardOrdemController = fxmlLoader.getController();
                cardOrdemController.setInfo(ordensData.get(i), myListener);

                this.gridContainer.add(novoCard, colunaAtual++, 1);

                this.gridContainer.setMinWidth(Region.USE_COMPUTED_SIZE);
                this.gridContainer.setPrefWidth(Region.USE_COMPUTED_SIZE);
                this.gridContainer.setMaxWidth(Region.USE_COMPUTED_SIZE);

                this.gridContainer.setMinHeight(Region.USE_COMPUTED_SIZE);
                this.gridContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
                this.gridContainer.setMaxHeight(Region.USE_COMPUTED_SIZE);

                GridPane.setMargin(novoCard, new Insets(5));

            }

        }
        catch ( java.io.IOException e) {
            e.printStackTrace();
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
            MainController.setFXMLLoaderPrincipal(fxmlLoader);

            if (!(idTecnico.getText().equals(""))) {

                InicioController inicioController = fxmlLoader.getController();
                inicioController.fazendoMudancaLogin(DAO.getTecnico().encontrarPorId(
                        Integer.parseInt(idTecnico.getText())));
            }

            stage.show();
        }
        catch (java.io.IOException e) {

        }
    }

    @FXML
    void abaCliente (ActionEvent event) {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("ClienteView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1280, 650);
            Stage stage = MainController.getStageInicio();
            stage.setScene(scene);

            MainController.setStageInicio(stage);
            MainController.setFXMLLoaderPrincipal(fxmlLoader);

            if (!(idTecnico.getText().equals(""))) {

                ClienteController clienteController = fxmlLoader.getController();
                clienteController.fazendoMudancaLogin(DAO.getTecnico().encontrarPorId(
                        Integer.parseInt(idTecnico.getText())));
            }

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
            MainController.setFXMLLoaderPrincipal(fxmlLoader);

            if (!(idTecnico.getText().equals(""))) {

                TecnicoController tecnicoController = fxmlLoader.getController();
                tecnicoController.fazendoMudancaLogin(DAO.getTecnico().encontrarPorId(
                        Integer.parseInt(idTecnico.getText())));
            }

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
    void abaPagamento(ActionEvent event) {

    }

    @FXML
    void abaLogin (ActionEvent event) {

        try {

            if (MainController.getStageLogin() == null) {

                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("LoginView.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setResizable(false);
                stage.getIcons().add(new Image(MainApplication.class.getResourceAsStream("/com/pbl/gerenciamentomicrocomputadores/Icones/Icone.png")));
                stage.setScene(scene);

                MainController.setStageLogin(stage);

                stage.show();
            }
            else {

                Stage stage = MainController.getStageLogin();
                stage.show();
                stage.toFront();
            }
        }
        catch (java.io.IOException e) {

        }
    }

    public void fazendoMudancaLogin (Tecnico tecnico) {

        paneCantoInicio.setVisible(false);
        paneTecnicoLogado.setVisible(true);

        nomeTecnico.setText(tecnico.getNome());
        idTecnico.setText(Integer.toString(tecnico.getId()));

    }


    @FXML
    void abaCadastrar(ActionEvent event) {

        try {

            if (MainController.getStageCadastroTecnico() == null) {

                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("CadastrarTecnicoView.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setResizable(false);
                stage.getIcons().add(new Image(MainApplication.class.getResourceAsStream("/com/pbl/gerenciamentomicrocomputadores/Icones/Icone.png")));
                stage.setScene(scene);

                MainController.setStageCadastroTecnico(stage);

                stage.show();
            }
            else {

                Stage stage = MainController.getStageCadastroTecnico();
                stage.show();
                stage.toFront();
            }
        }
        catch (java.io.IOException e) {

        }

    }

    @FXML
    void deslogarAcao(ActionEvent event) {

        paneCantoInicio.setVisible(true);
        paneTecnicoLogado.setVisible(false);
        nomeTecnico.setText("");
        idTecnico.setText("");
    }

    @FXML
    void todosAcao(ActionEvent event) {

        atualizarCards(this.ordensData);
    }

    @FXML
    void emAndamentoAcao(ActionEvent event) {

        List<OrdemDeServico> listaFormatada = new ArrayList<>();

        for (OrdemDeServico ordemDeServico: this.ordensData) {

            if (ordemDeServico.getStatusAtual().equals("Em andamento")) {

                listaFormatada.add(ordemDeServico);
            }
        }

        atualizarCards(listaFormatada);
    }

    @FXML
    void emEsperaAcao(ActionEvent event) {

        List<OrdemDeServico> listaFormatada = new ArrayList<>();

        for (OrdemDeServico ordemDeServico: this.ordensData) {

            if (ordemDeServico.getStatusAtual().equals("Em espera")) {

                listaFormatada.add(ordemDeServico);
            }
        }

        atualizarCards(listaFormatada);
    }

    @FXML
    void finalizadosAcao(ActionEvent event) {

        List<OrdemDeServico> listaFormatada = new ArrayList<>();

        for (OrdemDeServico ordemDeServico: this.ordensData) {

            if (ordemDeServico.getStatusAtual().equals("Finalizado")) {

                listaFormatada.add(ordemDeServico);
            }
        }

        atualizarCards(listaFormatada);
    }

    @FXML
    void canceladosAcao(ActionEvent event) {

        List<OrdemDeServico> listaFormatada = new ArrayList<>();

        for (OrdemDeServico ordemDeServico: this.ordensData) {

            if (ordemDeServico.getStatusAtual().equals("Cancelado")) {

                listaFormatada.add(ordemDeServico);
            }
        }

        atualizarCards(listaFormatada);
    }

    public void setOrdemEscolhida (OrdemDeServico ordemDeServico) {

        idOrdem.setText(Integer.toString(ordemDeServico.getIdOrdem()));
        idClienteOrdem.setText(Integer.toString(ordemDeServico.getIdCliente()));

        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        dataPedidoOrdem.setText(ordemDeServico.getData().getDataInicio().format(formatador));

        if (ordemDeServico.getData().getDataFim() == null) {

            dataFinalizacaoOrdem.setText("Não foi finalizada");
        }
        else {

            dataFinalizacaoOrdem.setText(ordemDeServico.getData().getDataFim().format(formatador));
        }

        statusOrdem.setText(ordemDeServico.getStatusAtual());
        tipoDeServicoOrdem.setText(ordemDeServico.getDescricaoServico().getTipoDeServico());
        setListaPecas();

    }

    public void setListaPecas () {

        String textoLista = "";

        Map<String, Integer> itensUtilizados = DAO.getOrdemDeServico().encontrarPorId(
                Integer.parseInt(idOrdem.getText())).getDescricaoServico().getMapItens();

        if (itensUtilizados.size() == 0) {

            textoLista = "\n    Nenhuma peça foi utilizada.";
        }
        else {

            for (String nomePeca : itensUtilizados.keySet()) {

                textoLista = textoLista.concat("\n    " + nomePeca + " " + itensUtilizados.get(nomePeca) + "x.");
            }
        }

        listaPecas.setText(textoLista);

    }

}
