package com.pbl.gerenciamentomicrocomputadores;

import com.pbl.gerenciamentomicrocomputadores.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.IOException;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/pbl/gerenciamentomicrocomputadores/Icones/Icone.png")));

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("InicioView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 650);

        stage.setResizable(false);

        stage.setScene(scene);
        MainController.setStage(stage);
        stage.show();
    }

    public static void main(String[] args) {

        launch();
    }
}