package com.pbl.gerenciamentomicrocomputadores;

import com.pbl.gerenciamentomicrocomputadores.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("MenuView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setScene(scene);
        MainController.setStage(stage);
        stage.show();
    }

    public static void main(String[] args) {

        launch();
    }
}