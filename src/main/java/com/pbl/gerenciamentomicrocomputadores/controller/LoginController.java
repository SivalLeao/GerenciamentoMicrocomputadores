package com.pbl.gerenciamentomicrocomputadores.controller;

import com.pbl.gerenciamentomicrocomputadores.MainApplication;
import com.pbl.gerenciamentomicrocomputadores.dao.DAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class LoginController {

    private static Stage stage;


    @FXML
    private Button Cadastrar;
    @FXML
    private TextField cpfTecnico;

    @FXML
    private Label mensagemDeErro;

    @FXML
    private Button entrarBotao;

    @FXML
    private Button voltarBotao;

    @FXML
    void acessoTecnico (ActionEvent event) {

        if (!(cpfTecnico.getText().matches("^[0-9]+$"))) {

            mensagemDeErro.setText("Entrada inválida");
        }
        else if (DAO.getTecnico().checarPorCpf(cpfTecnico.getText())) {


        }
        else {

            mensagemDeErro.setText("Usuário não encontrado");
        }

    }

    @FXML
    void fecharAbaLogin (ActionEvent event) {
        Stage stage = (Stage) voltarBotao.getScene().getWindow();
        stage.close();
    }
    @FXML
    void novoCadastro(ActionEvent event) {
        Stage stage = (Stage) voltarBotao.getScene().getWindow();
        stage.close();
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("CadastrarTecnicoView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setResizable(false);
            stage.getIcons().add(new Image(MainApplication.class.getResourceAsStream("/com/pbl/gerenciamentomicrocomputadores/Icones/Icone.png")));
            stage.setScene(scene);

            LoginController.setStage(stage);

            stage.show();
        }
        catch (java.io.IOException e) {

        }

    }

    public static Stage getStage () { return stage; }

    public static void setStage (Stage stage) { LoginController.stage = stage; }



}
