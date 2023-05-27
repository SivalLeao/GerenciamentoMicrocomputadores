package com.pbl.gerenciamentomicrocomputadores.controller;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class MainController {

    private static Stage stageInicio;

    private static FXMLLoader fxmlLoaderInicio;

    private static Stage stageLogin;

    private static Stage stageCadastroTecnico;

    public static Stage getStageInicio () { return stageInicio; }

    public static void setStageInicio (Stage stageInicio) { MainController.stageInicio = stageInicio; }

    public static FXMLLoader getFXMLLoaderInicio () { return fxmlLoaderInicio; }

    public static void setFXMLLoaderInicio (FXMLLoader fxmlLoaderInicio) { MainController.fxmlLoaderInicio = fxmlLoaderInicio; }

    public static Stage getStageLogin () { return stageLogin; }

    public static void setStageLogin (Stage stageLogin) { MainController.stageLogin = stageLogin; }

    public static Stage getStageCadastroTecnico () { return stageCadastroTecnico; }

    public static void setStageCadastroTecnico (Stage stageCadastroTecnico) { MainController.stageCadastroTecnico = stageCadastroTecnico; }

}