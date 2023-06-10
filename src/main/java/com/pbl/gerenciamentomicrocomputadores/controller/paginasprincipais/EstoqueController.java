package com.pbl.gerenciamentomicrocomputadores.controller.paginasprincipais;

import com.pbl.gerenciamentomicrocomputadores.MainApplication;
import com.pbl.gerenciamentomicrocomputadores.controller.MainController;
import com.pbl.gerenciamentomicrocomputadores.controller.MensagemController;
import com.pbl.gerenciamentomicrocomputadores.controller.MyListener;
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
import javafx.scene.image.Image;
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

    @FXML private Button novaPecaBotao;

    @FXML private GridPane gridContainer;

    @FXML private Label nomePeca;
    @FXML private Label valorPeca;
    @FXML private Label custoPeca;
    @FXML private Label qtdDisponivelPeca;
    @FXML private Label qtdReservadaPeca;
    @FXML private Label listaOrdemQtd;

    @FXML private Button removerPecaBotao;

    private MyListener<Peca> myListener;

    @FXML
    void initialize() {

        paneCantoInicio.setVisible(true);
        paneTecnicoLogado.setVisible(false);

        atualizarCards();
    }

    public void atualizarCards () {

        gridContainer.getChildren().clear();

        List<Peca> pecasData = DAO.getPeca().encontrarTodos();

        if (pecasData.size() > 0) {

            setPecaEscolhida(pecasData.get(0));

            this.myListener = new MyListener<Peca>() {
                @Override
                public void onClickListener(Peca peca) {

                    setPecaEscolhida(peca);
                }
            };

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

    // Atualizar quando fizer a página de pagamento
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

    public void setPecaEscolhida (Peca peca) {

        nomePeca.setText(peca.getNome());
        valorPeca.setText(Double.toString(peca.getValor()));
        custoPeca.setText(Double.toString(peca.getCusto()));
        qtdDisponivelPeca.setText(Integer.toString(peca.getQuantidade()));

        Map<Integer, Integer> mapOrdemQtd = DAO.getOrdemDeServico().OrdensUtilizandoPeca(nomePeca.getText());

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
    void removerPecaAcao(ActionEvent event) {

        String mensagem = "";

        if (idTecnico.getText().equals("")) {

            mensagem = "O técnico não está logado.\nFaça o login para alterar peça.";

        }
        else if (DAO.getOrdemDeServico().OrdensUtilizandoPeca(nomePeca.getText()).size() > 0) {

            mensagem = "Peça está sendo utilizada, \nnão é possível removê-la do sistema.";
        }
        else {

            DAO.getPeca().removerPeca(nomePeca.getText());
            atualizarCards();

            mensagem = "Peça foi removida.";
        }

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
