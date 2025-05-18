package com.example.quizapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;

public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Label errorlabel;


    public void handleLogin(ActionEvent actionEvent) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (username.equals("sahadat") && password.equals("pass123")) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/quizapplication/quiz.fxml"));
                Stage stage = (Stage) usernameField.getScene().getWindow();
                stage.setScene(new Scene(fxmlLoader.load()));
                stage.setTitle("Quiz Application");
            }
            catch (Exception e){
                e.printStackTrace();
            }

        } else if (username.isEmpty() && password.isEmpty()) {
            showError("username and password are required");
            return;
        } else if (!username.equals("sahadat") || !password.equals("pass123")) {
            showError("Invalid username and password");
            return;
        }
        else {
            errorlabel.setVisible(true);
            errorlabel.setText("logged in successfully");
        }

    }
    private void showError(String error) {
        errorlabel.setText(error);
        errorlabel.setVisible(true);
        System.out.println("showing Error: " + error);
    }
}