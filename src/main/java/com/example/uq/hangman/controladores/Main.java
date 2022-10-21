package com.example.uq.hangman.controladores;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/uq/hangman/img/logo2.png")));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("game2.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 640);
        stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.setTitle("HangMan");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}