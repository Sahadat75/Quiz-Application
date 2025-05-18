package com.example.quizapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ResultController {
    @FXML
    private Label resultlabel;

    public void setResult(int score, int total) {
        resultlabel.setText("Your Score is: " + score + " / " + total);
    }
    public void handleExit(ActionEvent event) {
        System.exit(0);
    }
}
