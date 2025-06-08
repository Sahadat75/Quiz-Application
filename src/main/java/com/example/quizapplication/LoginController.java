package com.example.quizapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

        if (username.isEmpty() || password.isEmpty()) {
            showError("Username and Password are required");
            return;
        }
        boolean matched = false;
        try (BufferedReader reader = new BufferedReader(new FileReader("/com/example/quizapplication/Database/users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String name = parts[0].trim();
                    String id = parts[1].trim();
                    String program = parts[2].trim();
                    String pass = parts[3].trim();

                    if (name.equals(username) && password.equals(pass)) {
                        matched = true;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            showError("Something went wrong");
            e.printStackTrace();
            return;
        }
        if (matched) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/quizapplication/quiz.fxml"));
                Stage stage = (Stage) usernameField.getScene().getWindow();
                stage.setScene(new Scene(fxmlLoader.load()));
                stage.setTitle("Quiz Application");
            } catch (Exception e) {
                showError("Something went wrong");
                e.printStackTrace();
            }
        } else {
            showError("Invalid Username or Password");
        }
    }
    private void showError(String error) {
        errorlabel.setText(error);
        errorlabel.setVisible(true);
        System.out.println("showing Error: " + error);
    }

    public void cancel(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void register(ActionEvent actionEvent) throws IOException {
        Parent register = FXMLLoader.load(getClass().getResource("/com/example/quizapplication/register.fxml"));
        Stage stage = (Stage) usernameField.getScene().getWindow();
        Scene registerScene = new Scene(register);
        stage.setScene(registerScene);
        stage.setTitle("Register");
        stage.show();
    }
}