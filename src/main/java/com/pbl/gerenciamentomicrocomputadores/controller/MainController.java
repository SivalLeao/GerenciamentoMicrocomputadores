package com.pbl.gerenciamentomicrocomputadores.controller;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class MainController {

    private static Stage stageInicio;

    private static FXMLLoader fxmlLoaderPrincipal;

    private static Stage stageLogin;

    private static Stage stageCadastroTecnico;

    private static Stage stageCadastroCliente;

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

}