package com.pbl.gerenciamentomicrocomputadores.controller.paginasprincipais;

import com.pbl.gerenciamentomicrocomputadores.MainApplication;
import com.pbl.gerenciamentomicrocomputadores.controller.MainController;
import com.pbl.gerenciamentomicrocomputadores.controller.MensagemController;
import com.pbl.gerenciamentomicrocomputadores.controller.cards.CardTecnicoController;
import com.pbl.gerenciamentomicrocomputadores.controller.paginasprincipais.ClienteController;
import com.pbl.gerenciamentomicrocomputadores.controller.paginasprincipais.InicioController;
import com.pbl.gerenciamentomicrocomputadores.dao.DAO;
import com.pbl.gerenciamentomicrocomputadores.model.Cliente;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.util.List;

public class TecnicoController {

    @FXML private Button inicioBotao;
    @FXML private Button clienteBotao;
    @FXML private Button estoqueBotao;
    @FXML private Button ordemBotao;
    @FXML private Button PagamentoBotao;

    @FXML private Pane paneCantoInicio;
    @FXML private Button cadastrarBotao;
    @FXML private Button loginBotao;

    @FXML private Pane paneTecnicoLogado;
    @FXML private Label nomeTecnico;
    @FXML private Label idTecnico;
    @FXML private Button deslogarBotao;

    @FXML private Pane paneDadosTecnico;
    @FXML private Label idPerfil;
    @FXML private TextField nomePerfil;
    @FXML private TextField enderecoPerfil;
    @FXML private TextField telefonePerfil;
    @FXML private TextField cpfPerfil;

    @FXML private Label mensagemDeErroNome;
    @FXML private Label mensagemDeErroCpf;
    @FXML private Label mensagemDeErroEndereco;
    @FXML private Label mensagemDeErroTelefone;
    
    @FXML private Pane paneMensagemPedidoLogin;

    @FXML private Button atualizarBotao;
    @FXML private Button removerBotao;

    @FXML private GridPane gridContainer;

    private List<Tecnico> tecnicosData;

    @FXML
    void initialize() {

        paneCantoInicio.setVisible(true);
        paneTecnicoLogado.setVisible(false);

        paneMensagemPedidoLogin.setVisible(true);
        paneDadosTecnico.setVisible(false);

        esconderMensagensDeErro();
        atualizarCards();

    }

    public void atualizarCards () {

        gridContainer.getChildren().clear();

        this.tecnicosData = DAO.getTecnico().encontrarTodos();

        try {

            int linhaAtual = 1;

            for (int i = 0; i < tecnicosData.size(); i++) {

                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("CardTecnicoView.fxml"));
                Pane novoCard = fxmlLoader.load();

                CardTecnicoController cardTecnicoController = fxmlLoader.getController();
                cardTecnicoController.setInfo(this.tecnicosData.get(i));

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

    public void esconderMensagensDeErro() {

        mensagemDeErroNome.setVisible(false);
        mensagemDeErroEndereco.setVisible(false);
        mensagemDeErroTelefone.setVisible(false);
        mensagemDeErroCpf.setVisible(false);

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

        setDadosPerfil(tecnico);

        paneMensagemPedidoLogin.setVisible(false);
        paneDadosTecnico.setVisible(true);

        paneCantoInicio.setVisible(false);
        paneTecnicoLogado.setVisible(true);

        nomeTecnico.setText(tecnico.getNome());
        idTecnico.setText(Integer.toString(tecnico.getId()));

    }

    @FXML
    void deslogarAcao(ActionEvent event) {

        deslogar();
    }

    public void deslogar() {

        paneMensagemPedidoLogin.setVisible(true);
        paneDadosTecnico.setVisible(false);

        paneCantoInicio.setVisible(true);
        paneTecnicoLogado.setVisible(false);
        nomeTecnico.setText("");
        idTecnico.setText("");
        esconderMensagensDeErro();
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
    void abaPagamento(ActionEvent event) {

    }

    @FXML
    void atualizarPerfilAcao(ActionEvent event) {

        esconderMensagensDeErro();

        int qtdErros = 0;

        if (!((nomePerfil.getText().matches("^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$"))
                && (nomePerfil.getText().replaceAll("\\s+", "").length() >= 3))) {

            mensagemDeErroNome.setVisible(true);
            qtdErros++;
        } else {
            mensagemDeErroNome.setVisible(false);
        }

        if (!((enderecoPerfil.getText().replaceAll("\\s+", "").length() >= 3))) {

            mensagemDeErroEndereco.setVisible(true);
            qtdErros++;
        } else {
            mensagemDeErroEndereco.setVisible(false);
        }

        if (!((telefonePerfil.getText().matches("^[0-9() -]+$")) &&
                (telefonePerfil.getText().replaceAll("\\s+|\\(+|\\)+|-+", "").length() == 11))) {

            mensagemDeErroTelefone.setVisible(true);
            qtdErros++;
        } else {
            mensagemDeErroTelefone.setVisible(false);
        }

        if (!((cpfPerfil.getText().matches("^[0-9 .-]+$")) &&
                (cpfPerfil.getText().replaceAll("\\s+|\\.+|-+", "").length() == 11))) {

            mensagemDeErroCpf.setVisible(true);
            qtdErros++;
        } else {
            mensagemDeErroCpf.setVisible(false);
        }

        if (qtdErros == 0) {

            Tecnico tecnico = new Tecnico(nomePerfil.getText(), enderecoPerfil.getText(), telefonePerfil.getText(),
                    cpfPerfil.getText());
            tecnico.setId(Integer.parseInt(idPerfil.getText()));

            DAO.getTecnico().atualizar(tecnico);

            atualizarCards();

            try {

                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("MensagemView.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setResizable(false);
                stage.getIcons().add(new Image(MainApplication.class.getResourceAsStream("/com/pbl/gerenciamentomicrocomputadores/Icones/Icone.png")));
                stage.setScene(scene);

                MensagemController mensagemController = fxmlLoader.getController();
                mensagemController.setMensagem("     Perfil Atualizado.");

                stage.show();

            } catch (java.io.IOException e) {

            }
        }
    }

    @FXML
    void removerPerfilAcao(ActionEvent event) {

        esconderMensagensDeErro();

        DAO.getTecnico().remover(Integer.parseInt(idPerfil.getText()));

        idPerfil.setText("");
        nomePerfil.setText("");
        enderecoPerfil.setText("");
        telefonePerfil.setText("");
        cpfPerfil.setText("");

        atualizarCards();

        deslogar();

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("MensagemView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.getIcons().add(new Image(MainApplication.class.getResourceAsStream("/com/pbl/gerenciamentomicrocomputadores/Icones/Icone.png")));
            stage.setScene(scene);

            MensagemController mensagemController = fxmlLoader.getController();
            mensagemController.setMensagem("     Perfil Removido.");

            stage.show();

        } catch (java.io.IOException e) {

        }
    }

    private void setDadosPerfil (Tecnico tecnico) {

        idPerfil.setText(Integer.toString(tecnico.getId()));
        nomePerfil.setText(tecnico.getNome());
        enderecoPerfil.setText(tecnico.getEndereco());
        telefonePerfil.setText(tecnico.getTelefone());
        cpfPerfil.setText(tecnico.getCpf());

    }

}
