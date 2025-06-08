package com.example.quizapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class RegisterController {
    @FXML private TextField nameField;
    @FXML private TextField idField;
    @FXML private TextField programField;
    @FXML private TextField passwordField;

    @FXML
    private void handleRegister(ActionEvent event) {
        String name = nameField.getText().trim();
        String id = idField.getText().trim();
        String program = programField.getText().trim();
        String password = passwordField.getText().trim();

        if (name.isEmpty() || id.isEmpty() || program.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR,"Please Fill All Field");
            return;
        }
        if (UserDataHandeler.userExists(id)){
            showAlert(Alert.AlertType.ERROR,"User Already Exists");
            return;
        }
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/java/com/example/quizapplication/Database/users.txt",true))) {
            writer.write(name + "," + id + "," + program + "," + password);
            writer.newLine();
        }
        catch (Exception e) {
            e.printStackTrace();
            return;
        }
        boolean success = UserDataHandeler.saveUser(name, id, program, password);
        if (success) {
            showAlert(Alert.AlertType.INFORMATION,"Successfully Registered");
            clearFields();
        }
        else {
            showAlert(Alert.AlertType.ERROR,"Failed To Register. Please Try Again");
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/quizapplication/login.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Login");
            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setTitle("Registration");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void clearFields() {
        nameField.clear();
        idField.clear();
        programField.clear();
        passwordField.clear();
    }

    public void handleCancel(ActionEvent actionEvent) {
        System.exit(0);
    }
}
