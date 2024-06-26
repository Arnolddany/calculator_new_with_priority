package com.example.mainone;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 515, 352); // создание сцены
        stage.setTitle("Калькулятор"); // заголовок
        stage.setScene(scene); // установка сцены
        stage.show(); // отображает окно на экране
    }

    public static void main(String[] args) {
        launch();
    }
}