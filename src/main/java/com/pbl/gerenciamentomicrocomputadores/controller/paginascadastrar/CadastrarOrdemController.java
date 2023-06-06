package com.pbl.gerenciamentomicrocomputadores.controller.paginascadastrar;

import com.pbl.gerenciamentomicrocomputadores.controller.MainController;
import com.pbl.gerenciamentomicrocomputadores.controller.paginasprincipais.OrdemController;
import com.pbl.gerenciamentomicrocomputadores.controller.paginasprincipais.TecnicoController;
import com.pbl.gerenciamentomicrocomputadores.dao.DAO;
import com.pbl.gerenciamentomicrocomputadores.model.OrdemDeServico;
import com.pbl.gerenciamentomicrocomputadores.model.Peca;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CadastrarOrdemController {

    @FXML private Button cadastrarBotao;
    @FXML private Button adicionarPecaBotao;
    @FXML private Button voltarBotao;

    @FXML private TextField idCliente;
    @FXML private ChoiceBox<String> choiceBox;
    @FXML private Pane paneListaPecas;
    @FXML private Label listaPecas;
    @FXML private ChoiceBox<String> choiceBoxItens;
    @FXML private Label maxQuantidade;
    @FXML private TextField quantidadePeca;

    @FXML private Label mensagemDeErroId;
    @FXML private Label mensagemDeErroLista;
    @FXML private Label mensagemDeErroQuantidade;

    private Map<String, Integer> pecasEscolhidas;

    @FXML
    void initialize() {

        choiceBox.getItems().add("Montagem/Instalação");
        choiceBox.getItems().add("Sistema operacional");
        choiceBox.getItems().add("Programas");
        choiceBox.getItems().add("Limpeza");

        choiceBox.setValue("Limpeza");

        pecasEscolhidas = new HashMap<>();

        paneListaPecas.setVisible(false);

        choiceBox.setOnAction(e -> {
            if (choiceBox.getValue().equals("Montagem/Instalação")) {

                paneListaPecas.setVisible(true);
                atualizarChoiceBoxs();
                atualizarMaxQuantidade();
            }
            else {

                paneListaPecas.setVisible(false);
            }});

        esconderMensagensDeErro();
    }

    public void atualizarChoiceBoxs () {

        List<Peca> estoque = DAO.getPeca().encontrarTodos();

        for (Peca peca: estoque) {

            choiceBoxItens.getItems().add(peca.getNome());
        }

        choiceBoxItens.getSelectionModel().selectFirst();

        atualizarLabelPecas();
    }

    public void atualizarMaxQuantidade () {

        maxQuantidade.setText(Integer.toString(DAO.getPeca().
                encontrarPorNome(choiceBoxItens.getValue()).getQuantidade()));
    }

    public void atualizarLabelPecas () {

        String texto = "";

        for (String nomePeca: this.pecasEscolhidas.keySet()) {

            texto = texto.concat("\n    " + nomePeca + " " + this.pecasEscolhidas.get(nomePeca) + "x");
        }

        listaPecas.setText(texto);

    }

    public void esconderMensagensDeErro() {

        mensagemDeErroId.setText("");
        mensagemDeErroLista.setText("");
        mensagemDeErroQuantidade.setText("");
    }

    @FXML
    void adicionarPecaAcao(ActionEvent event) {

        if (!( quantidadePeca.getText().matches("^[0-9]+$")) || quantidadePeca.getText().equals("")) {

            mensagemDeErroQuantidade.setText("Quantidade inválida.");
        }
        else if (Integer.parseInt(quantidadePeca.getText()) > Integer.parseInt(maxQuantidade.getText())) {

            mensagemDeErroQuantidade.setText("Quantidade excedida.");
        }
        else {

            pecasEscolhidas.put(choiceBoxItens.getValue(), Integer.parseInt(quantidadePeca.getText()));
            atualizarLabelPecas();
            esconderMensagensDeErro();
        }

    }

    @FXML
    void cadastrarAcao(ActionEvent event) {

        if (idCliente.getText().matches("^[0-9]+$")) {

            if (DAO.getCliente().checarPorId(Integer.parseInt(idCliente.getText()))) {

                if (choiceBox.getValue().equals("Montagem/Instalação")) {

                    if (pecasEscolhidas.size() != 0) {

                        criarOrdem();
                        esconderMensagensDeErro();
                        limparEntradas();

                        FXMLLoader fxmlLoader = MainController.getFXMLLoaderPrincipal();
                        String classeController = fxmlLoader.getController().getClass().getSimpleName();

                        if (classeController.equals("OrdemController")) {

                            OrdemController ordemController = fxmlLoader.getController();
                            ordemController.atualizarOrdensData();
                            ordemController.atualizarCards(ordemController.getOrdensData());
                        }

                        Stage stage = (Stage) voltarBotao.getScene().getWindow();
                        stage.close();
                    }
                    else {

                        mensagemDeErroLista.setText("Lista de peças vazia.");
                    }
                }
                else {

                    criarOrdem();
                    esconderMensagensDeErro();
                    limparEntradas();

                    FXMLLoader fxmlLoader = MainController.getFXMLLoaderPrincipal();
                    String classeController = fxmlLoader.getController().getClass().getSimpleName();

                    if (classeController.equals("OrdemController")) {

                        OrdemController ordemController = fxmlLoader.getController();
                        ordemController.atualizarOrdensData();
                        ordemController.atualizarCards(ordemController.getOrdensData());
                    }

                    Stage stage = (Stage) voltarBotao.getScene().getWindow();
                    stage.close();
                }
            }
            else {

                mensagemDeErroId.setText("Cliente não encontrado.");
            }
        }
        else {

            mensagemDeErroId.setText("ID inválido.");
        }

    }

    public void criarOrdem () {

        OrdemDeServico ordemDeServico = new OrdemDeServico(0,
                Integer.parseInt(idCliente.getText()));

        ordemDeServico.getDescricaoServico().setTipoDeServico(choiceBox.getValue());

        if (choiceBox.getValue().equals("Montagem/Instalação")) {

            for (String nomePeca : this.pecasEscolhidas.keySet()) {

                ordemDeServico.getDescricaoServico().
                        setMapItens(nomePeca, pecasEscolhidas.get(nomePeca));
            }
        }

        DAO.getOrdemDeServico().criar(ordemDeServico);
    }

    @FXML
    void fecharAbaCadastrar(ActionEvent event) {

        esconderMensagensDeErro();
        Stage stage = (Stage) voltarBotao.getScene().getWindow();
        stage.close();

    }

    public void limparEntradas () {

        idCliente.setText("");
        listaPecas.setText("");
        pecasEscolhidas.clear();
        quantidadePeca.setText("");
    }

}
