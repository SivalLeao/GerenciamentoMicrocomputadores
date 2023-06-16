package com.pbl.gerenciamentomicrocomputadores.controller.paginasprincipais;

import com.pbl.gerenciamentomicrocomputadores.MainApplication;
import com.pbl.gerenciamentomicrocomputadores.controller.*;
import com.pbl.gerenciamentomicrocomputadores.controller.cards.CardPecaController;
import com.pbl.gerenciamentomicrocomputadores.dao.DAO;
import com.pbl.gerenciamentomicrocomputadores.model.Peca;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;

public class EstoqueController {

    @FXML private Button inicioBotao;
    @FXML private Button clienteBotao;
    @FXML private Button tecnicoBotao;
    @FXML private Button ordemBotao;
    @FXML private Button PagamentoBotao;

    @FXML private Pane paneCantoInicio;
    @FXML private Button cadastrarBotao;
    @FXML private Button loginBotao;

    @FXML private Pane paneTecnicoLogado;
    @FXML private Label nomeTecnico;
    @FXML private Label idTecnico;
    @FXML private Button deslogarBotao;

    @FXML private Pane paneCardsPecas;
    @FXML private Button novaPecaBotao;
    @FXML private GridPane gridContainer;

    @FXML private Pane paneSemPeca;

    @FXML private TextField barraDePesquisa;
    @FXML private Button pesquisarBotao;
    @FXML private Label mensagemPesquisa;

    @FXML private Pane paneDadosPeca;
    @FXML private Label nomePeca;
    @FXML private Label valorPeca;
    @FXML private Label custoPeca;
    @FXML private Label qtdDisponivelPeca;
    @FXML private Label qtdReservadaPeca;
    @FXML private Label listaOrdemQtd;

    @FXML private Pane paneSemDadosPeca;

    @FXML private Button adicionarQtdBotao;
    @FXML private Button atualizarPecaBotao;
    @FXML private Button removerPecaBotao;

    private MyListener<Peca> myListener;

    @FXML
    void pressed(MouseEvent event) {

        if (MainController.getStageConfirmacao() != null) {

            MainController.getStageConfirmacao().close();
        }
        if (MainController.getStageAtualizarPeca() != null) {

            MainController.getStageAtualizarPeca().close();
        }

    }

    @FXML
    void initialize() {

        paneCantoInicio.setVisible(true);
        paneTecnicoLogado.setVisible(false);

        atualizarCards();
        mensagemPesquisa.setText("");
    }

    public void atualizarCards () {

        gridContainer.getChildren().clear();

        List<Peca> pecasData = DAO.getPeca().encontrarTodos();

        if (pecasData.size() > 0) {

            paneCardsPecas.setVisible(true);
            paneSemPeca.setVisible(false);
            paneDadosPeca.setVisible(true);
            paneSemDadosPeca.setVisible(false);

            if (nomePeca.getText().equals("") || !(DAO.getPeca().checarPorNome(nomePeca.getText()))) {

                setPecaEscolhida(pecasData.get(0));
            }
            else {

                setPecaEscolhida(DAO.getPeca().encontrarPorNome(nomePeca.getText()));
            }

            this.myListener = new MyListener<Peca>() {
                @Override
                public void onClickListener(Peca peca) {

                    setPecaEscolhida(peca);
                }
            };

        }
        else {

            paneCardsPecas.setVisible(false);
            paneSemPeca.setVisible(true);
            paneDadosPeca.setVisible(false);
            paneSemDadosPeca.setVisible(true);
        }

        try {

            int linhaAtual = 1;
            int colunaAtual= 0;

            for (int i = 0; i < pecasData.size(); i++) {

                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("CardPecaView.fxml"));
                AnchorPane novoCard = fxmlLoader.load();

                CardPecaController cardPecaController = fxmlLoader.getController();
                cardPecaController.setInfo(pecasData.get(i), myListener);

                if (colunaAtual == 3) {

                    linhaAtual++;
                    colunaAtual = 0;
                }

                this.gridContainer.add(novoCard, colunaAtual++, linhaAtual);

                this.gridContainer.setMinWidth(Region.USE_COMPUTED_SIZE);
                this.gridContainer.setPrefWidth(Region.USE_COMPUTED_SIZE);
                this.gridContainer.setMaxWidth(Region.USE_COMPUTED_SIZE);

                this.gridContainer.setMinHeight(Region.USE_COMPUTED_SIZE);
                this.gridContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
                this.gridContainer.setMaxHeight(Region.USE_COMPUTED_SIZE);

                GridPane.setMargin(novoCard, new Insets(16));

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
    void abaOrdem (ActionEvent event) {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("OrdemView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1280, 650);
            Stage stage = MainController.getStageInicio();
            stage.setScene(scene);

            MainController.setStageInicio(stage);
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
            Stage stage = MainController.getStageInicio();
            stage.setScene(scene);

            MainController.setStageInicio(stage);
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

        mensagemPesquisa.setText("");

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

        mensagemPesquisa.setText("");

        paneCantoInicio.setVisible(false);
        paneTecnicoLogado.setVisible(true);

        nomeTecnico.setText(tecnico.getNome());
        idTecnico.setText(Integer.toString(tecnico.getId()));

    }

    @FXML
    void abaCadastrar(ActionEvent event) {

        mensagemPesquisa.setText("");

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
        mensagemPesquisa.setText("");

    }

    public void setPecaEscolhida (Peca peca) {

        nomePeca.setText(peca.getNome());
        valorPeca.setText(Double.toString(peca.getValor()));
        custoPeca.setText(Double.toString(peca.getCusto()));
        qtdDisponivelPeca.setText(Integer.toString(peca.getQuantidade()));

        Map<Integer, Integer> mapOrdemQtd = DAO.getOrdemDeServico().ordensUtilizandoPeca(nomePeca.getText());

        int qtdUsada = 0;

        for (int chave: mapOrdemQtd.keySet()) {

            qtdUsada += mapOrdemQtd.get(chave);
        }

        qtdReservadaPeca.setText(Integer.toString(qtdUsada));

        setListaOrdemQtd (qtdUsada, mapOrdemQtd);

    }

    public void setListaOrdemQtd (int qtdUsada, Map<Integer, Integer> mapOrdemQtd) {

        String texto;

        if (qtdUsada == 0) {

            texto = "\n    Nenhuma quantidade está sendo \n    utilizada.";
        }
        else {

            texto = "\n    ID serviço : quantidade\n";

            for (int idOrdem: mapOrdemQtd.keySet()) {

                texto = texto.concat("\n    " + Integer.toString(idOrdem) + " : " +
                        Integer.toString(mapOrdemQtd.get(idOrdem)));
            }
        }

        listaOrdemQtd.setText(texto);
    }


    @FXML
    void abaCadastrarPeca(ActionEvent event) {

        try {

            if (MainController.getStageCadastroPeca() == null) {

                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("CadastrarPecaView.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setResizable(false);
                stage.getIcons().add(new Image(MainApplication.class.getResourceAsStream("/com/pbl/gerenciamentomicrocomputadores/Icones/Icone.png")));
                stage.setScene(scene);

                MainController.setStageCadastroPeca(stage);

                stage.show();
            }
            else {

                Stage stage = MainController.getStageCadastroPeca();
                stage.show();
                stage.toFront();
            }
        }
        catch (java.io.IOException e) {

        }

    }

    @FXML
    void pesquisarAcao(ActionEvent event) {

        String nomePesquisado = barraDePesquisa.getText();

        if (DAO.getPeca().checarPorNome(nomePesquisado)) {

            setPecaEscolhida(DAO.getPeca().encontrarPorNome(nomePesquisado));
            mensagemPesquisa.setText("Peça encontrada.");
        }
        else if (nomePesquisado.replaceAll("\\s+", "").length() >= 2) {

            mensagemPesquisa.setText("Peça não encontrada.");
        }
        else {

            mensagemPesquisa.setText("Dado inválido.");
        }

    }

    @FXML
    void adicionarQtdAcao(ActionEvent event) {

        if (idTecnico.getText().equals("")) {

            exibirMensagem("             " +
                    "O técnico não está logado.\nFaça o login para alterar dados do cliente.");

        }
        else {

            try {

                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("AdicionarQtdView.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setResizable(false);
                stage.getIcons().add(new Image(MainApplication.class.getResourceAsStream("/com/pbl/gerenciamentomicrocomputadores/Icones/Icone.png")));
                stage.setScene(scene);

                AdicionarQtdController adicionarQtdController = fxmlLoader.getController();
                adicionarQtdController.setNomePeca(nomePeca.getText());

                stage.show();
            } catch (java.io.IOException e) {

            }

        }

    }

    @FXML
    void atualizarPecaAcao(ActionEvent event) {

        if (idTecnico.getText().equals("")) {

            exibirMensagem("             " +
                    "O técnico não está logado.\nFaça o login para alterar dados do cliente.");

        }
        else {

            try {

                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("AtualizarPecaView.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setResizable(false);
                stage.getIcons().add(new Image(MainApplication.class.getResourceAsStream("/com/pbl/gerenciamentomicrocomputadores/Icones/Icone.png")));
                stage.setScene(scene);

                MainController.setFXMLLoaderAtualizarPeca(fxmlLoader);
                MainController.setStageAtualizarPeca(stage);

                AtualizarPecaController atualizarPecaController = fxmlLoader.getController();
                atualizarPecaController.setDadosPeca(nomePeca.getText());

                stage.show();

            } catch (java.io.IOException e) {

            }

        }

    }

    @FXML
    void removerPecaAcao(ActionEvent event) {

        if (idTecnico.getText().equals("")) {

            exibirMensagem("             " +
                    "O técnico não está logado.\nFaça o login para alterar dados do cliente.");

        }
        else if (DAO.getOrdemDeServico().ordensUtilizandoPeca(nomePeca.getText()).size() > 0) {

            exibirMensagem("           " +
                    "Peça está sendo utilizada, \nnão é possível removê-la do sistema.");

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
                confirmacaoController.setTexto("Deseja excluir a peça?");
                confirmacaoController.setTipoDeAcao("remover");

                MainController.setStageConfirmacao(stage);

                stage.show();
            }
            catch (java.io.IOException e) {

            }

        }

    }

    public void removerPeca () {

        DAO.getPeca().removerPeca(nomePeca.getText());
        atualizarCards();

        exibirMensagem("Peca removida.");

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

}
