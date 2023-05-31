package com.pbl.gerenciamentomicrocomputadores.controller;

import com.pbl.gerenciamentomicrocomputadores.MainApplication;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.util.List;

public class ClienteController {

    @FXML
    private Button atualizarBotao;

    @FXML
    private Button cadastrar;

    @FXML
    private Button cadastrarCliente;

    @FXML
    private TextField cpfCliente;

    @FXML
    private Button deslogarBotao;

    @FXML
    private TextField enderecoCliente;

    @FXML
    private Button estoqueBotao;

    @FXML
    private GridPane gridContainer;

    @FXML
    private Label idTecnico;

    @FXML
    private Button inicioBotao;

    @FXML
    private Button loginBotao;

    @FXML
    private TextField nomeCliente;

    @FXML
    private Label nomeTecnico;

    @FXML
    private Button ordemBotao;

    @FXML
    private Pane paneCantoInicio;

    @FXML
    private Pane paneTecnicoLogado;

    @FXML
    private Button removerBotao;

    @FXML
    private Button tecnicoBotao;

    @FXML
    private TextField telefoneCliente;

    @FXML
    private Label idCliente;

    @FXML
    private Label mensagemDeErroCpf;

    @FXML
    private Label mensagemDeErroEndereco;

    @FXML
    private Label mensagemDeErroNome;

    @FXML
    private Label mensagemDeErroTelefone;

    @FXML
    private Button pesquisarBotao;

    @FXML
    private Label mensagemPesquisa;

    @FXML
    private TextField infoPesquisa;

    @FXML
    private GridPane gridMiniCards;

    private MyListener<Cliente> myListener;

    private List<Cliente> clientesData;

    @FXML
    void initialize() {

        paneCantoInicio.setVisible(true);
        paneTecnicoLogado.setVisible(false);

        atualizarCards();
        atualizarMiniCards();

    }

    public void atualizarCards () {

        gridContainer.getChildren().clear();

        this.clientesData = DAO.getCliente().encontrarTodos();

        if (this.clientesData.size() > 0) {

            setClienteEscolhido(this.clientesData.get(0));

            this.myListener = new MyListener<Cliente>() {
                @Override
                public void onClickListener(Cliente cliente) {

                    setClienteEscolhido(cliente);
                    atualizarMiniCards();
                    mensagemDeErroNome.setText("");
                    mensagemDeErroEndereco.setText("");
                    mensagemDeErroTelefone.setText("");
                    mensagemDeErroCpf.setText("");
                }
            };

        }

        try {

            int linhaAtual = 1;

            for (int i = 0; i < clientesData.size(); i++) {

                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("CardClienteView.fxml"));
                Pane novoCard = fxmlLoader.load();

                CardClienteController cardClienteController = fxmlLoader.getController();
                cardClienteController.setInfo(this.clientesData.get(i), myListener);

                this.gridContainer.add(novoCard, 0, linhaAtual++);

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

    public void atualizarMiniCards () {

        gridMiniCards.getChildren().clear();

        List<OrdemDeServico> listaOrdens = DAO.getOrdemDeServico().encontrarPorIdCliente(Integer.parseInt(idCliente.getText()));

        try {

            int colunaAtual = 0;

            for (int i = 0; i < listaOrdens.size(); i++) {

                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("MiniCardOrdemView.fxml"));
                AnchorPane novoCard = fxmlLoader.load();

                MiniCardOrdemController miniCardOrdemController = fxmlLoader.getController();
                miniCardOrdemController.setInfo(listaOrdens.get(i));

                this.gridMiniCards.add(novoCard, colunaAtual++, 1);

                this.gridMiniCards.setMinWidth(Region.USE_COMPUTED_SIZE);
                this.gridMiniCards.setPrefWidth(Region.USE_COMPUTED_SIZE);
                this.gridMiniCards.setMaxWidth(Region.USE_COMPUTED_SIZE);

                this.gridMiniCards.setMinHeight(Region.USE_COMPUTED_SIZE);
                this.gridMiniCards.setPrefHeight(Region.USE_COMPUTED_SIZE);
                this.gridMiniCards.setMaxHeight(Region.USE_COMPUTED_SIZE);

                GridPane.setMargin(novoCard, new Insets(5));

            }

        }
        catch ( java.io.IOException e) {
            e.printStackTrace();
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
    void abaOrdem(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("OrdemView.fxml"));
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
        int id = tecnico.getId();
        idTecnico.setText(Integer.toString(id));

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
    void abaCadastrarCliente(ActionEvent event) {

        try {

            if (MainController.getStageCadastroCliente() == null) {

                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("CadastrarClienteView.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setResizable(false);
                stage.getIcons().add(new Image(MainApplication.class.getResourceAsStream("/com/pbl/gerenciamentomicrocomputadores/Icones/Icone.png")));
                stage.setScene(scene);

                MainController.setStageCadastroCliente(stage);

                stage.show();
            }
            else {

                Stage stage = MainController.getStageCadastroCliente();
                stage.show();
                stage.toFront();
            }
        }
        catch (java.io.IOException e) {

        }

    }

    @FXML
    void atualizarClienteAcao(ActionEvent event) {

        int qtdErros = 0;

        if (! ((nomeCliente.getText().matches("^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$"))
                && (nomeCliente.getText().replaceAll("\\s+", "").length() >= 3))) {

            mensagemDeErroNome.setText("Entrada inválida");
            qtdErros++;
        }
        else {
            mensagemDeErroNome.setText("");
        }

        if (! ((enderecoCliente.getText().replaceAll("\\s+", "").length() >= 3))) {

            mensagemDeErroEndereco.setText("Entrada inválida");
            qtdErros++;
        }
        else {
            mensagemDeErroEndereco.setText("");
        }

        if (! ((telefoneCliente.getText().matches("^[0-9() -]+$")) &&
                (telefoneCliente.getText().replaceAll("\\s+|\\(+|\\)+|-+", "").length() == 11)))  {

            mensagemDeErroTelefone.setText("Entrada inválida");
            qtdErros++;
        }
        else {
            mensagemDeErroTelefone.setText("");
        }

        if (! ((cpfCliente.getText().matches("^[0-9 .-]+$")) &&
                (cpfCliente.getText().replaceAll("\\s+|\\.+|-+", "").length() == 11)))  {

            mensagemDeErroCpf.setText("Entrada inválida");
            qtdErros++;
        }
        else {
            mensagemDeErroCpf.setText("");
        }

        if (qtdErros == 0) {

            Cliente cliente = new Cliente(nomeCliente.getText(), enderecoCliente.getText(), telefoneCliente.getText(),
                    cpfCliente.getText());
            cliente.setId(Integer.parseInt(idCliente.getText()));

            DAO.getCliente().atualizar(cliente);

            atualizarCards();

            try {

                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("MensagemView.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setResizable(false);
                stage.getIcons().add(new Image(MainApplication.class.getResourceAsStream("/com/pbl/gerenciamentomicrocomputadores/Icones/Icone.png")));
                stage.setScene(scene);

                MensagemController mensagemController = fxmlLoader.getController();
                mensagemController.setMensagem("     Cliente Atualizado.");

                stage.show();
            }
            catch (java.io.IOException e) {

            }

        }

    }

    @FXML
    void removerClienteAcao(ActionEvent event) {

        DAO.getCliente().remover(Integer.parseInt(idCliente.getText()));

        idCliente.setText("");
        nomeCliente.setText("");
        enderecoCliente.setText("");
        telefoneCliente.setText("");
        cpfCliente.setText("");

        atualizarCards();

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("MensagemView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.getIcons().add(new Image(MainApplication.class.getResourceAsStream("/com/pbl/gerenciamentomicrocomputadores/Icones/Icone.png")));
            stage.setScene(scene);

            MensagemController mensagemController = fxmlLoader.getController();
            mensagemController.setMensagem("     Cliente Removido.");

            stage.show();
        }
        catch (java.io.IOException e) {

        }

    }


    @FXML
    void pesquisarAcao(ActionEvent event) {

        String cpfPesquisado = infoPesquisa.getText();

        if (DAO.getCliente().checarPorCpf(cpfPesquisado)) {

            setClienteEscolhido(DAO.getCliente().encontrarPorCpf(cpfPesquisado));
            mensagemPesquisa.setText("");
        }
        else if ((cpfPesquisado.matches("^[0-9 .-]+$")) &&
                (cpfPesquisado.replaceAll("\\s+|\\.+|-+", "").length() == 11)) {

            mensagemPesquisa.setText("Cliente não encontrado.");
        }
        else {

            mensagemPesquisa.setText("Dado inválido.");
        }

    }


    private void setClienteEscolhido (Cliente cliente) {

        idCliente.setText(Integer.toString(cliente.getId()));
        nomeCliente.setText(cliente.getNome());
        enderecoCliente.setText(cliente.getEndereco());
        telefoneCliente.setText(cliente.getTelefone());
        cpfCliente.setText(cliente.getCpf());

    }

}
