package com.pbl.gerenciamentomicrocomputadores.controller.paginasprincipais;

import com.pbl.gerenciamentomicrocomputadores.MainApplication;
import com.pbl.gerenciamentomicrocomputadores.controller.*;
import com.pbl.gerenciamentomicrocomputadores.controller.cards.CardClienteController;
import com.pbl.gerenciamentomicrocomputadores.controller.cards.MiniCardOrdemController;
import com.pbl.gerenciamentomicrocomputadores.controller.confirmacao.ConfirmacaoController;
import com.pbl.gerenciamentomicrocomputadores.controller.mensagem.MensagemController;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.util.List;

public class ClienteController {

    @FXML private Button inicioBotao;
    @FXML private Button tecnicoBotao;
    @FXML private Button ordemBotao;
    @FXML private Button estoqueBotao;
    @FXML private Button PagamentoBotao;

    @FXML private Pane paneCantoInicio;
    @FXML private Button cadastrarBotao;
    @FXML private Button loginBotao;

    @FXML private Pane paneTecnicoLogado;
    @FXML private Label nomeTecnico;
    @FXML private Label idTecnico;
    @FXML private Button deslogarBotao;

    @FXML private Pane paneSemClientes;

    @FXML private Pane paneDadosCliente;
    @FXML private Label idCliente;
    @FXML private TextField nomeCliente;
    @FXML private TextField enderecoCliente;
    @FXML private TextField telefoneCliente;
    @FXML private TextField cpfCliente;

    @FXML private Label mensagemDeErroNome;
    @FXML private Label mensagemDeErroCpf;
    @FXML private Label mensagemDeErroEndereco;
    @FXML private Label mensagemDeErroTelefone;

    @FXML private GridPane gridMiniOrdens;

    @FXML private Pane paneSemCardsClientes;
    @FXML private Pane paneCardsClientes;
    @FXML private GridPane gridContainer;

    @FXML private Button cadastrarClienteBotao;
    @FXML private Button atualizarBotao;
    @FXML private Button removerBotao;

    @FXML private TextField barraDePesquisa;
    @FXML private Label mensagemPesquisa;
    @FXML private Button pesquisarBotao;

    @FXML private Label adicionar;

    private MyListener<Cliente> myListener;

    String cpfAtual;

    @FXML
    void pressed(MouseEvent event) {

        if (MainController.getStageConfirmacao() != null) {

            MainController.getStageConfirmacao().close();
        }

    }

    @FXML
    void initialize() {

        paneCantoInicio.setVisible(true);
        paneTecnicoLogado.setVisible(false);

        adicionar.setVisible(false);

        pesquisarBotao.setOnMouseEntered(mouseEvent -> {

        });
        pesquisarBotao.setOnMouseExited(event -> {

        });

        cadastrarClienteBotao.setOnMouseEntered(mouseEvent -> {
            adicionar.setVisible(true);
        });
        cadastrarClienteBotao.setOnMouseExited(event -> {
            adicionar.setVisible(false);
        });

        esconderMensagensDeErro();
        atualizarCards();
        atualizarMiniOrdens();

    }

    public void atualizarCards () {

        gridContainer.getChildren().clear();

        List<Cliente> clientesData = DAO.getCliente().encontrarTodos();

        if (clientesData.size() > 0) {

            paneDadosCliente.setVisible(true);
            paneSemClientes.setVisible(false);
            paneCardsClientes.setVisible(true);
            paneSemCardsClientes.setVisible(false);

            setClienteEscolhido(clientesData.get(0));

            this.myListener = new MyListener<Cliente>() {
                @Override
                public void onClickListener(Cliente cliente) {

                    setClienteEscolhido(cliente);
                    atualizarMiniOrdens();
                    esconderMensagensDeErro();
                }
            };

        }
        else {

            paneDadosCliente.setVisible(false);
            paneSemClientes.setVisible(true);
            paneCardsClientes.setVisible(false);
            paneSemCardsClientes.setVisible(true);

        }

        try {

            int linhaAtual = 1;

            for (int i = 0; i < clientesData.size(); i++) {

                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("CardClienteView.fxml"));
                Pane novoCard = fxmlLoader.load();

                CardClienteController cardClienteController = fxmlLoader.getController();
                cardClienteController.setInfo(clientesData.get(i), myListener);

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

    public void atualizarMiniOrdens() {

        gridMiniOrdens.getChildren().clear();

        if (! (paneSemClientes.isVisible())) {

            List<OrdemDeServico> listaOrdens = DAO.getOrdemDeServico().encontrarPorIdCliente(Integer.parseInt(idCliente.getText()));

            try {

                int colunaAtual = 0;

                for (int i = 0; i < listaOrdens.size(); i++) {

                    FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("MiniCardOrdemView.fxml"));
                    Pane novoCard = fxmlLoader.load();

                    MiniCardOrdemController miniCardOrdemController = fxmlLoader.getController();
                    miniCardOrdemController.setInfo(listaOrdens.get(i));

                    this.gridMiniOrdens.add(novoCard, colunaAtual++, 1);

                    this.gridMiniOrdens.setMinWidth(Region.USE_COMPUTED_SIZE);
                    this.gridMiniOrdens.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    this.gridMiniOrdens.setMaxWidth(Region.USE_COMPUTED_SIZE);

                    this.gridMiniOrdens.setMinHeight(Region.USE_COMPUTED_SIZE);
                    this.gridMiniOrdens.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    this.gridMiniOrdens.setMaxHeight(Region.USE_COMPUTED_SIZE);

                    GridPane.setMargin(novoCard, new Insets(5));

                }

            } catch (java.io.IOException e) {
                e.printStackTrace();
            }

        }

    }

    public void esconderMensagensDeErro() {

        mensagemDeErroNome.setVisible(false);
        mensagemDeErroEndereco.setVisible(false);
        mensagemDeErroTelefone.setVisible(false);
        mensagemDeErroCpf.setVisible(false);
        mensagemPesquisa.setText("");

    }


    @FXML
    void abaInicio(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("InicioView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1280, 650);
            Stage stage = MainController.getStagePrincipal();
            stage.setScene(scene);

            MainController.setStagePrincipal(stage);
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
    void abaTecnico(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("TecnicoView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1280, 650);
            Stage stage = MainController.getStagePrincipal();
            stage.setScene(scene);

            MainController.setStagePrincipal(stage);
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
            Stage stage = MainController.getStagePrincipal();
            stage.setScene(scene);

            MainController.setStagePrincipal(stage);
            MainController.setFXMLLoaderPrincipal(fxmlLoader);

            if (!(idTecnico.getText().equals(""))) {

                EstoqueController estoqueController = fxmlLoader.getController();
                estoqueController.fazendoMudancaLogin(DAO.getTecnico().encontrarPorId(
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
            Stage stage = MainController.getStagePrincipal();
            stage.setScene(scene);

            MainController.setStagePrincipal(stage);
            MainController.setFXMLLoaderPrincipal(fxmlLoader);

            if (!(idTecnico.getText().equals(""))) {

                OrdemController ordemController = fxmlLoader.getController();
                ordemController.fazendoMudancaLogin(DAO.getTecnico().encontrarPorId(
                        Integer.parseInt(idTecnico.getText())));
            }

            stage.show();
        }
        catch (java.io.IOException e) {

        }

    }

    @FXML
    void abaPagamento(ActionEvent event) {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("PagamentoView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1280, 650);
            Stage stage = MainController.getStagePrincipal();
            stage.setScene(scene);

            MainController.setStagePrincipal(stage);
            MainController.setFXMLLoaderPrincipal(fxmlLoader);

            if (!(idTecnico.getText().equals(""))) {

                PagamentoController pagamentoController = fxmlLoader.getController();
                pagamentoController.fazendoMudancaLogin(DAO.getTecnico().encontrarPorId(
                        Integer.parseInt(idTecnico.getText())));
            }

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

        esconderMensagensDeErro();

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
        esconderMensagensDeErro();
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

        esconderMensagensDeErro();

        if (idTecnico.getText().equals("")) {

            exibirMensagem("             " +
                    "O técnico não está logado.\nFaça o login para alterar dados do cliente.");

        }
        else {

            int qtdErros = validarEntradas();

            if (qtdErros == 0) {

                try {

                    FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("ConfirmacaoView.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    Stage stage = new Stage();
                    stage.setResizable(false);
                    stage.getIcons().add(new Image(MainApplication.class.getResourceAsStream("/com/pbl/gerenciamentomicrocomputadores/Icones/Icone.png")));
                    stage.setScene(scene);
                    stage.setAlwaysOnTop(true);

                    ConfirmacaoController confirmacaoController = fxmlLoader.getController();
                    confirmacaoController.setTexto("Deseja atualizar as informações do cliente?");
                    confirmacaoController.setTipoDeAcao("atualizar");

                    MainController.setStageConfirmacao(stage);

                    stage.show();
                }
                catch (java.io.IOException e) {

                }
            }

        }

    }

    public int validarEntradas () {

        int qtdErros = 0;

        if (!((nomeCliente.getText().matches("^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$"))
                && (nomeCliente.getText().replaceAll("\\s+", "").length() >= 3))) {

            mensagemDeErroNome.setVisible(true);
            qtdErros++;

        } else {
            mensagemDeErroNome.setVisible(false);
        }

        if (!((enderecoCliente.getText().replaceAll("\\s+", "").length() >= 3))) {

            mensagemDeErroEndereco.setVisible(true);
            qtdErros++;

        } else {
            mensagemDeErroEndereco.setVisible(false);
        }

        if (!((telefoneCliente.getText().matches("^[0-9]+$")) &&
                (telefoneCliente.getText().length() == 11))) {

            mensagemDeErroTelefone.setVisible(true);
            qtdErros++;

        } else {
            mensagemDeErroTelefone.setVisible(false);
        }

        if (!((cpfCliente.getText().matches("^[0-9]+$")) &&
                (cpfCliente.getText().length() == 11))) {

            mensagemDeErroCpf.setVisible(true);
            mensagemDeErroCpf.setText("Apenas números. Deve conter 11 caracteres.");
            qtdErros++;

        } else {
            mensagemDeErroCpf.setVisible(false);
        }

        if (! (cpfAtual.equals(cpfCliente.getText()))) {

            if (DAO.getCliente().checarPorCpf(cpfCliente.getText())) {

                mensagemDeErroCpf.setVisible(true);
                mensagemDeErroCpf.setText("CPF já cadastrado.");
                qtdErros++;

            }
        }

        return qtdErros;
    }

    public void atualizarCliente () {

        Cliente cliente = new Cliente(nomeCliente.getText(), enderecoCliente.getText(), telefoneCliente.getText(),
                cpfCliente.getText());
        cliente.setId(Integer.parseInt(idCliente.getText()));

        DAO.getCliente().atualizar(cliente);

        atualizarCards();
        setClienteEscolhido(cliente);
        atualizarMiniOrdens();

        exibirMensagem("  Cliente Atualizado.");

    }

    @FXML
    void removerClienteAcao(ActionEvent event) {

        esconderMensagensDeErro();

        if (idTecnico.getText().equals("")) {

            exibirMensagem("             " +
                    "O técnico não está logado.\nFaça o login para alterar dados do cliente.");

        }
        else if (DAO.getOrdemDeServico().encontrarPorIdCliente(Integer.parseInt(idCliente.getText())).size() > 0) {

            exibirMensagem("  O cliente possui serviços registrados \n" +
                        "no sistema. Não é permitido removê-lo.");

        }
        else {

            try {

                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("ConfirmacaoView.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setResizable(false);
                stage.getIcons().add(new Image(MainApplication.class.getResourceAsStream("/com/pbl/gerenciamentomicrocomputadores/Icones/Icone.png")));
                stage.setScene(scene);
                stage.setAlwaysOnTop(true);

                ConfirmacaoController confirmacaoController = fxmlLoader.getController();
                confirmacaoController.setTexto("Deseja remover o cliente?");
                confirmacaoController.setTipoDeAcao("remover");

                MainController.setStageConfirmacao(stage);

                stage.show();
            }
            catch (java.io.IOException e) {

            }

        }

    }

    public void removerCliente () {

        DAO.getCliente().remover(Integer.parseInt(idCliente.getText()));

        idCliente.setText("");
        nomeCliente.setText("");
        enderecoCliente.setText("");
        telefoneCliente.setText("");
        cpfCliente.setText("");

        atualizarCards();
        atualizarMiniOrdens();

        exibirMensagem("  Cliente Removido.");

    }


    @FXML
    void pesquisarAcao(ActionEvent event) {

        esconderMensagensDeErro();

        String cpfPesquisado = barraDePesquisa.getText();

        if (DAO.getCliente().checarPorCpf(cpfPesquisado)) {

            setClienteEscolhido(DAO.getCliente().encontrarPorCpf(cpfPesquisado));
            atualizarMiniOrdens();
            esconderMensagensDeErro();
            mensagemPesquisa.setText("Cliente encontrado.");
        }
        else if ((cpfPesquisado.matches("^[0-9]+$")) &&
                (cpfPesquisado.length() == 11)) {

            mensagemPesquisa.setText("Cliente não encontrado.");
        }
        else {

            mensagemPesquisa.setText("Dado inválido.");
        }

    }

    public void exibirMensagem (String mensagem) {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("MensagemView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.getIcons().add(new Image(MainApplication.class.getResourceAsStream("/com/pbl/gerenciamentomicrocomputadores/Icones/Icone.png")));
            stage.setScene(scene);

            MensagemController mensagemController = fxmlLoader.getController();
            mensagemController.setMensagem(mensagem);

            stage.show();

        }
        catch (java.io.IOException e) {

        }

    }

    private void setClienteEscolhido (Cliente cliente) {

        cpfAtual = cliente.getCpf();

        idCliente.setText(Integer.toString(cliente.getId()));
        nomeCliente.setText(cliente.getNome());
        enderecoCliente.setText(cliente.getEndereco());
        telefoneCliente.setText(cliente.getTelefone());
        cpfCliente.setText(cliente.getCpf());

    }

}
