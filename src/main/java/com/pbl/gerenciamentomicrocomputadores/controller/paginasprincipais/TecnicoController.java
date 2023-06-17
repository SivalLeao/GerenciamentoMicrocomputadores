package com.pbl.gerenciamentomicrocomputadores.controller.paginasprincipais;

import com.pbl.gerenciamentomicrocomputadores.MainApplication;
import com.pbl.gerenciamentomicrocomputadores.controller.confirmacao.ConfirmacaoController;
import com.pbl.gerenciamentomicrocomputadores.controller.MainController;
import com.pbl.gerenciamentomicrocomputadores.controller.mensagem.MensagemController;
import com.pbl.gerenciamentomicrocomputadores.controller.finalizacaoservico.PedidoFinalizacaoController;
import com.pbl.gerenciamentomicrocomputadores.controller.cards.CardTecnicoController;
import com.pbl.gerenciamentomicrocomputadores.dao.DAO;
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

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

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
    @FXML private Pane paneSemServico;
    @FXML private Pane paneBotaoColetarServico;
    @FXML private Button iniciarServicoBotao;
    @FXML private Label mensagemSemServico;
    @FXML private Pane paneDadosServico;
    @FXML private Button finalizarServicoBotao;
    @FXML private Button dispensarServicoBotao;

    @FXML private Label idOrdem;
    @FXML private Label idClienteOrdem;
    @FXML private Label dataPedidoOrdem;
    @FXML private Label tipoDeServicoOrdem;
    @FXML private Label listaPecas;

    @FXML private GridPane gridContainer;

    String cpfAtual;

    @FXML
    void pressed(MouseEvent event) {

        if (MainController.getStageConfirmacao() != null) {

            MainController.getStageConfirmacao().close();
        }
        if (MainController.getStagePedidoSatisfacao() != null) {

            MainController.getStagePedidoSatisfacao().close();
        }

    }

    @FXML
    void initialize() {

        paneCantoInicio.setVisible(true);
        paneTecnicoLogado.setVisible(false);

        paneMensagemPedidoLogin.setVisible(true);
        paneDadosTecnico.setVisible(false);

        esconderMensagensDeErro();
        modificarAbaServico();
        atualizarCards();

    }

    public void modificarAbaServico () {

        if (!(idTecnico.getText().equals(""))) {

            paneSemServico.setVisible(false);
            paneDadosServico.setVisible(false);
            paneBotaoColetarServico.setVisible(false);

            if (DAO.getOrdemDeServico().checarStatusEmAndamento(Integer.parseInt(idTecnico.getText()))) {

                setDadosServico();

                paneDadosServico.setVisible(true);
                paneBotaoColetarServico.setVisible(false);
            }
            else {

                paneDadosServico.setVisible(false);
                paneBotaoColetarServico.setVisible(true);
            }
        }
        else {

            paneSemServico.setVisible(true);
        }
    }

    public void atualizarCards () {

        gridContainer.getChildren().clear();

        List<Tecnico> tecnicosData = DAO.getTecnico().encontrarTodos();

        try {

            int colunaAtual= 0;

            for (int i = 0; i < tecnicosData.size(); i++) {

                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("CardTecnicoView.fxml"));
                Pane novoCard = fxmlLoader.load();

                CardTecnicoController cardTecnicoController = fxmlLoader.getController();
                cardTecnicoController.setInfo(tecnicosData.get(i));

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

    public void esconderMensagensDeErro() {

        mensagemDeErroNome.setVisible(false);
        mensagemDeErroEndereco.setVisible(false);
        mensagemDeErroTelefone.setVisible(false);
        mensagemDeErroCpf.setVisible(false);
        mensagemSemServico.setVisible(false);

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
        modificarAbaServico();

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
        modificarAbaServico();
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
    void abaCliente (ActionEvent event) {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("ClienteView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1280, 650);
            Stage stage = MainController.getStagePrincipal();
            stage.setScene(scene);

            MainController.setStagePrincipal(stage);
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
    void atualizarPerfilAcao(ActionEvent event) {

        esconderMensagensDeErro();

        if (validarEntradas() == 0) {

            try {

                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("ConfirmacaoView.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setResizable(false);
                stage.getIcons().add(new Image(MainApplication.class.getResourceAsStream("/com/pbl/gerenciamentomicrocomputadores/Icones/Icone.png")));
                stage.setScene(scene);
                stage.setAlwaysOnTop(true);

                ConfirmacaoController confirmacaoController = fxmlLoader.getController();
                confirmacaoController.setTexto("Deseja atualizar as informações de perfil?");
                confirmacaoController.setTipoDeAcao("atualizar");

                MainController.setStageConfirmacao(stage);

                stage.show();
            }
            catch (java.io.IOException e) {

            }

        }

    }

    public int validarEntradas () {

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

        if (!((telefonePerfil.getText().matches("^[0-9]+$")) &&
                (telefonePerfil.getText().length() == 11))) {

            mensagemDeErroTelefone.setVisible(true);
            qtdErros++;

        } else {
            mensagemDeErroTelefone.setVisible(false);
        }

        if (!((cpfPerfil.getText().matches("^[0-9]+$")) &&
                (cpfPerfil.getText().length() == 11))) {

            mensagemDeErroCpf.setVisible(true);
            mensagemDeErroCpf.setText("Apenas números. Deve conter 11 caracteres.");
            qtdErros++;

        } else {
            mensagemDeErroCpf.setVisible(false);
        }

        if (! (cpfAtual.equals(cpfPerfil.getText()))) {

            if (DAO.getTecnico().checarPorCpf(cpfPerfil.getText())) {

                mensagemDeErroCpf.setVisible(true);
                mensagemDeErroCpf.setText("CPF já cadastrado.");
                qtdErros++;

            }
        }

        return qtdErros;
    }

    public void atualizarPerfil () {

        Tecnico tecnico = new Tecnico(nomePerfil.getText(), enderecoPerfil.getText(), telefonePerfil.getText(),
                cpfPerfil.getText());
        tecnico.setId(Integer.parseInt(idPerfil.getText()));

        DAO.getTecnico().atualizar(tecnico);

        atualizarCards();
        fazendoMudancaLogin(DAO.getTecnico().encontrarPorId(Integer.parseInt(idPerfil.getText())));

        exibirMensagem("Perfil Atualizado.");

    }

    @FXML
    void removerPerfilAcao(ActionEvent event) {

        esconderMensagensDeErro();

        if (DAO.getOrdemDeServico().checarStatusEmAndamento(Integer.parseInt(idPerfil.getText()))) {

            exibirMensagem(" O técnico está realizando um serviço.\n" +
                        "        Não pode ser removido.");

        }
        else if (DAO.getOrdemDeServico().encontrarPorIdTecnico(
                Integer.parseInt(idPerfil.getText())).size() != 0) {

            exibirMensagem(" O técnico possui serviços no sistema.\n" +
                    "        Não pode ser removido.");
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
                confirmacaoController.setTexto("Deseja excluir o perfil?");
                confirmacaoController.setTipoDeAcao("remover");

                MainController.setStageConfirmacao(stage);

                stage.show();
            }
            catch (java.io.IOException e) {

            }

        }
    }

    public void removerPerfil () {

        DAO.getTecnico().remover(Integer.parseInt(idPerfil.getText()));

        idPerfil.setText("");
        nomePerfil.setText("");
        enderecoPerfil.setText("");
        telefonePerfil.setText("");
        cpfPerfil.setText("");

        atualizarCards();

        deslogar();

        exibirMensagem("Perfil removido.");

    }

    public void setDadosPerfil (Tecnico tecnico) {

        cpfAtual = tecnico.getCpf();

        idPerfil.setText(Integer.toString(tecnico.getId()));
        nomePerfil.setText(tecnico.getNome());
        enderecoPerfil.setText(tecnico.getEndereco());
        telefonePerfil.setText(tecnico.getTelefone());
        cpfPerfil.setText(tecnico.getCpf());

    }

    @FXML
    void iniciarServicoAcao(ActionEvent event) {

        OrdemDeServico ordemDeServico = DAO.getOrdemDeServico().coletarOrdem();

        if (ordemDeServico == null) {

            mensagemSemServico.setVisible(true);
        }
        else {

            mensagemSemServico.setVisible(false);
            ordemDeServico.setIdTecnico(Integer.parseInt(idTecnico.getText()));
            DAO.getOrdemDeServico().atualizarStatus(ordemDeServico.getIdOrdem(), "Em andamento");
            DAO.getOrdemDeServico().atualizar(ordemDeServico);
            setDadosServico();
            paneDadosServico.setVisible(true);
            paneBotaoColetarServico.setVisible(false);
        }

    }

    public void setDadosServico() {

        OrdemDeServico ordemDeServico = DAO.getOrdemDeServico().
                conseguirOrdemTecnico(Integer.parseInt(idTecnico.getText()));

        idOrdem.setText(Integer.toString(ordemDeServico.getIdOrdem()));
        idClienteOrdem.setText(Integer.toString(ordemDeServico.getIdCliente()));

        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        dataPedidoOrdem.setText(ordemDeServico.getData().getDataInicio().format(formatador));

        tipoDeServicoOrdem.setText(ordemDeServico.getDescricaoServico().getTipoDeServico());
        setListaPecas();

    }

    public void setListaPecas () {

        String textoLista = "";

        Map<String, Integer> itensUtilizados = DAO.getOrdemDeServico().encontrarPorId(
                Integer.parseInt(idOrdem.getText())).getDescricaoServico().getMapItens();

        if (itensUtilizados.size() == 0) {

            textoLista = "\n    Nenhuma peça foi solicitada.";
        }
        else {

            for (String nomePeca : itensUtilizados.keySet()) {

                textoLista = textoLista.concat("\n    " + nomePeca + " " + itensUtilizados.get(nomePeca) + "x.");
            }
        }

        listaPecas.setText(textoLista);

    }


    @FXML
    void finalizarServicoAcao(ActionEvent event) {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("PedidoFinalizacaoView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.getIcons().add(new Image(MainApplication.class.getResourceAsStream("/com/pbl/gerenciamentomicrocomputadores/Icones/Icone.png")));
            stage.setScene(scene);
            stage.setAlwaysOnTop(true);

            PedidoFinalizacaoController pedidoFinalizacaoController = fxmlLoader.getController();
            pedidoFinalizacaoController.setIdOrdem(Integer.parseInt(idOrdem.getText()));

            MainController.setStagePedidoSatisfacao(stage);

            stage.show();
        }
        catch (java.io.IOException e) {

        }

    }

    public void finalizarServico () {

        DAO.getOrdemDeServico().atualizarStatus(Integer.parseInt(idOrdem.getText()), "Finalizado");
        modificarAbaServico();
    }

    @FXML
    void dispensarServicoAcao(ActionEvent event) {

        OrdemDeServico ordemDeServico = DAO.getOrdemDeServico().encontrarPorId(Integer.parseInt(idOrdem.getText()));

        ordemDeServico.setStatusAtual("Em espera");
        ordemDeServico.setIdTecnico(0);

        modificarAbaServico();
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
