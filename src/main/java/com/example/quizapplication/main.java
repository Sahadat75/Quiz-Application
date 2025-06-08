package com.example.quizapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class main extends Application {


    @Override
    public void start(Stage primarystage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/quizapplication/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primarystage.setTitle("Login - Quiz App");
        primarystage.setScene(scene);
        primarystage.show();
    }
    public static void main (String[] args) {
        launch(args);
    }
}
