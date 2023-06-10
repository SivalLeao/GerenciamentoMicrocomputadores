package com.pbl.gerenciamentomicrocomputadores.controller;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class MainController {

    private static Stage stageInicio;

    private static FXMLLoader fxmlLoaderPrincipal;

    private static Stage stageLogin;

    private static Stage stageCadastroTecnico;

    private static Stage stageCadastroCliente;

    private static Stage stageCadastroOrdem;

    private static Stage stageCadastroPeca;

    private static Stage stageConfirmacao;

    public static Stage getStageInicio () { return stageInicio; }

    public static void setStageInicio (Stage stageInicio) { MainController.stageInicio = stageInicio; }

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

    public static Stage getStageConfirmacao () { return stageConfirmacao; }

    public static void setStageConfirmacao (Stage stageConfirmacao) { MainController.stageConfirmacao = stageConfirmacao; }

}