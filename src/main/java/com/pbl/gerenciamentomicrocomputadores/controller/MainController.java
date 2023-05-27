package com.pbl.gerenciamentomicrocomputadores.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class MainController {

    private static Stage stage;

    private static FXMLLoader fxmlLoader;

    public static Stage getStage () { return stage; }

    public static void setStage (Stage stage) { MainController.stage = stage; }

    public static FXMLLoader getFXMLLoader () { return fxmlLoader; }

    public static void setFXMLLoader (FXMLLoader fxmlLoader) { MainController.fxmlLoader = fxmlLoader; }

}