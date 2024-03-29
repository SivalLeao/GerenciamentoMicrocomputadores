package com.pbl.gerenciamentomicrocomputadores.controller;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class MainController {

    private static Stage stagePrincipal;

    private static FXMLLoader fxmlLoaderPrincipal;

    private static Stage stageLogin;

    private static Stage stageCadastroTecnico;

    private static Stage stageCadastroCliente;

    private static Stage stageCadastroOrdem;

    private static Stage stageCadastroPeca;

    private static FXMLLoader fxmlLoaderAtualizarPeca;

    private static Stage stageAtualizarPeca;

    private static Stage stageConfirmacao;

    private static Stage stagePedidoSatisfacao;

    public static Stage getStagePrincipal() { return stagePrincipal; }

    public static void setStagePrincipal(Stage stagePrincipal) { MainController.stagePrincipal = stagePrincipal; }

    public static FXMLLoader getFXMLLoaderPrincipal() { return fxmlLoaderPrincipal; }

    public static void setFXMLLoaderPrincipal(FXMLLoader fxmlLoaderPrincipal) { MainController.fxmlLoaderPrincipal = fxmlLoaderPrincipal; }

    public static Stage getStageLogin () { return stageLogin; }

    public static void setStageLogin (Stage stageLogin) { MainController.stageLogin = stageLogin; }

    public static Stage getStageCadastroTecnico () { return stageCadastroTecnico; }

    public static void setStageCadastroTecnico (Stage stageCadastroTecnico) { MainController.stageCadastroTecnico = stageCadastroTecnico; }

    public static Stage getStageCadastroCliente () { return stageCadastroCliente; }

    public static void setStageCadastroCliente (Stage stageCadastroCliente) { MainController.stageCadastroCliente = stageCadastroCliente; }

    public static Stage getStageCadastroOrdem () { return stageCadastroOrdem; }

    public static void setStageCadastroOrdem (Stage stageCadastroOrdem) { MainController.stageCadastroOrdem = stageCadastroOrdem; }

    public static Stage getStageCadastroPeca () { return stageCadastroPeca; }

    public static void setStageCadastroPeca (Stage stageCadastroPeca) { MainController.stageCadastroPeca = stageCadastroPeca; }

    public static FXMLLoader getFXMLLoaderAtualizarPeca() { return fxmlLoaderAtualizarPeca; }

    public static void setFXMLLoaderAtualizarPeca(FXMLLoader fxmlLoaderAtualizarPeca) { MainController.fxmlLoaderAtualizarPeca = fxmlLoaderAtualizarPeca; }

    public static Stage getStageAtualizarPeca() { return stageAtualizarPeca; }

    public static void setStageAtualizarPeca (Stage stageAtualizarPeca) { MainController.stageAtualizarPeca = stageAtualizarPeca; }

    public static Stage getStageConfirmacao () { return stageConfirmacao; }

    public static void setStageConfirmacao (Stage stageConfirmacao) { MainController.stageConfirmacao = stageConfirmacao; }

    public static Stage getStagePedidoSatisfacao () { return stagePedidoSatisfacao; }

    public static void setStagePedidoSatisfacao (Stage stagePedidoSatisfacao) { MainController.stagePedidoSatisfacao = stagePedidoSatisfacao; }

}