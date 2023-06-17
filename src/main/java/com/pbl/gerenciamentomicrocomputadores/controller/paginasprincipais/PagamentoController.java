package com.pbl.gerenciamentomicrocomputadores.controller.paginasprincipais;

import com.pbl.gerenciamentomicrocomputadores.MainApplication;
import com.pbl.gerenciamentomicrocomputadores.controller.MainController;
import com.pbl.gerenciamentomicrocomputadores.controller.MyListener;
import com.pbl.gerenciamentomicrocomputadores.controller.cards.CardClienteController;
import com.pbl.gerenciamentomicrocomputadores.controller.cards.CardFaturaController;
import com.pbl.gerenciamentomicrocomputadores.dao.DAO;
import com.pbl.gerenciamentomicrocomputadores.model.Cliente;
import com.pbl.gerenciamentomicrocomputadores.model.OrdemDeServico;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.lang.runtime.ObjectMethods;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class PagamentoController {

    @FXML private Button inicioBotao;
    @FXML private Button clienteBotao;
    @FXML private Button tecnicoBotao;
    @FXML private Button estoqueBotao;
    @FXML private Button ordemBotao;

    @FXML private Pane paneCantoInicio;
    @FXML private Button cadastrarBotao;
    @FXML private Button loginBotao;

    @FXML private Pane paneTecnicoLogado;
    @FXML private Label nomeTecnico;
    @FXML private Label idTecnico;
    @FXML private Button deslogarBotao;

    @FXML private GridPane gridContainer;

    @FXML private Label nomeCliente;
    @FXML private Label enderecoCliente;
    @FXML private Label cpfCliente;
    @FXML private Label telefoneCliente;
    @FXML private Label valorFatura;
    @FXML private Label dataFinalizacaoServico;
    @FXML private Label dataInicioServico;
    @FXML private Label formaDePagamento;
    @FXML private Label nomePagador;
    @FXML private Label idPagador;
    @FXML private Label nomeEmissor;
    @FXML private Label idEmissor;

    @FXML private Label nomeServicoTexto;
    @FXML private Label qtdTexto;
    @FXML private Label valorUnitarioServicoTexto;
    @FXML private Label valorTotalServicoTexto;

    @FXML private Label valorTotal;

    private MyListener<OrdemDeServico> myListener;

    @FXML
    void initialize () {

        paneCantoInicio.setVisible(true);
        paneTecnicoLogado.setVisible(false);

        atualizarCards();
    }

    public void atualizarCards () {

        gridContainer.getChildren().clear();

        List<OrdemDeServico> ordensFinalizadasData = DAO.getOrdemDeServico().ordensFinalizadas();

        if (ordensFinalizadasData.size() > 0) {

            setFaturaEscolhida(ordensFinalizadasData.get(0));

            this.myListener = new MyListener<OrdemDeServico>() {
                @Override
                public void onClickListener(OrdemDeServico ordemDeServico) {

                    setFaturaEscolhida(ordemDeServico);
                }
            };
        }

        try {

            int linhaAtual = 1;
            int colunaAtual = 0;

            for (int i = 0; i < ordensFinalizadasData.size(); i++) {

                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("CardFaturaView.fxml"));
                Pane novoCard = fxmlLoader.load();

                CardFaturaController cardFaturaController = fxmlLoader.getController();
                cardFaturaController.setInfo(ordensFinalizadasData.get(i), myListener);

                if (colunaAtual == 3) {

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

                GridPane.setMargin(novoCard, new Insets(10));

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
    void abaEstoque (ActionEvent event) {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("EstoqueView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1280, 650);
            Stage stage = MainController.getStageInicio();
            stage.setScene(scene);

            MainController.setStageInicio(stage);
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

    public void setFaturaEscolhida (OrdemDeServico ordemDeServico) {

        Cliente cliente = DAO.getCliente().encontrarPorId(ordemDeServico.getIdCliente());
        Tecnico tecnico = DAO.getTecnico().encontrarPorId(ordemDeServico.getIdTecnico());

        String cpf = cliente.getCpf().substring(0,3) + "." + cliente.getCpf().substring(3,6)
                + "." + cliente.getCpf().substring(6,9) + "-" + cliente.getCpf().substring(9);

        String telefone = "(" + cliente.getTelefone().substring(0, 2) + ") " + cliente.getTelefone()
                .substring(2, 7) + "-" +cliente.getTelefone().substring(7);

        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        nomeCliente.setText(cliente.getNome());
        cpfCliente.setText(cpf);
        telefoneCliente.setText(telefone);
        enderecoCliente.setText(cliente.getEndereco());
        valorFatura.setText(Double.toString(ordemDeServico.calcularValorServico(DAO.getPeca().encontrarTodosMap())));
        dataFinalizacaoServico.setText(ordemDeServico.getData().getDataFim().format(formatador));
        dataInicioServico.setText(ordemDeServico.getData().getDataInicio().format(formatador));
        formaDePagamento.setText(ordemDeServico.getFormaPagamento());
        nomePagador.setText(cliente.getNome());
        idPagador.setText(Integer.toString(cliente.getId()));
        nomeEmissor.setText(tecnico.getNome());
        idEmissor.setText(Integer.toString(tecnico.getId()));
        valorTotal.setText(Double.toString(ordemDeServico.calcularValorServico(DAO.getPeca().encontrarTodosMap())));

        atualizarDadosServico(ordemDeServico);

    }

    public void atualizarDadosServico(OrdemDeServico ordemDeServico) {

        String textoServico = "";
        String textoQtd = "";
        String textoValorUnitario = "";
        String textoValorTotal = "";

        Map<String, Peca> estoque = DAO.getPeca().encontrarTodosMap();

        if (ordemDeServico.getDescricaoServico().getTipoDeServico().equals("Montagem/Instalação")) {

            Map<String, Integer> pecasUsadas = ordemDeServico.getDescricaoServico().getMapItens();

            for (String nomePeca : pecasUsadas.keySet()) {

                textoServico = textoServico.concat("\n " + nomePeca);
                textoQtd = textoQtd.concat("\n" + pecasUsadas.get(nomePeca));
                textoValorUnitario = textoValorUnitario.concat("\nR$ " + estoque.get(nomePeca).getValor());
                textoValorTotal = textoValorTotal.concat("\nR$ " + (estoque.get(nomePeca).getValor() *
                        pecasUsadas.get(nomePeca)));

            }

        }
        else {

            textoServico = textoServico.concat("\n " + ordemDeServico.getDescricaoServico().getTipoDeServico());
            textoValorUnitario = textoValorUnitario.concat("\nR$ " +
                    ordemDeServico.calcularValorServico(estoque));
            textoValorTotal = textoValorTotal.concat("\nR$ " + (ordemDeServico.calcularValorServico(estoque)));

        }

        nomeServicoTexto.setPrefHeight(Region.USE_COMPUTED_SIZE);
        qtdTexto.setPrefHeight(Region.USE_COMPUTED_SIZE);
        valorUnitarioServicoTexto.setPrefHeight(Region.USE_COMPUTED_SIZE);
        valorTotalServicoTexto.setPrefHeight(Region.USE_COMPUTED_SIZE);

        nomeServicoTexto.setText(textoServico);
        qtdTexto.setText(textoQtd);
        valorUnitarioServicoTexto.setText(textoValorUnitario);
        valorTotalServicoTexto.setText(textoValorTotal);

    }

}
