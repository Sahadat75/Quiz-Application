package com.example.quizapplication;

import com.example.quizapplication.model.Question;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class QuizController {
    @FXML
    private Label questionLabel;
    @FXML
    private RadioButton option1, option2, option3, option4;
    @FXML
    private Button nextButton;
    @FXML
    private Label scoreLabel;

    private List<Question> questions = new ArrayList<>();
    private int currentQuestion = 0;
    private int score = 0;

    public void initialize() {
        loadquestion();
        showquestion();
    }

    private void loadquestion() {
        questions.add(new Question("1. What is the Correct Way to declare a Java main method?",
                new String[]{"public static int main(String[] args)","public void main(String[] args)","public static void main(String[] args)","static void main(String[] args)"},2));
        questions.add(new Question("2. Which keyword is used to create an object in Java?",
                new String[]{"new","this","create","object"},0));
        questions.add(new Question("3. What is the output of System.out.println(5 + \"5\");?",
                new String[]{"10","55","15","Compile Error"},1));
        questions.add(new Question("4. Which of these is NOT a valid Java data type?",
                new String[]{"float","double","real","boolean"},2));
        questions.add(new Question("5. What does == compare when used with objects?",
                new String[]{"Compares values","Compares memory references","Checks if objects are of the same class","Always returns false"},1));
        questions.add(new Question("6. What is the default value of an int in Java?",
                new String[]{"null","0","1","Undefined"},1));
        questions.add(new Question("7. Which loop guarantees at least one execution of its body?",
                new String[]{"for","while","do-while","foreach"},2));
        questions.add(new Question("8. What is the correct way to handle exceptions in Java?",
                new String[]{"try-catch","trow","trows","All Above"},3));
        questions.add(new Question("9. Which keyword prevents a method from being overridden?",
                new String[]{"static","final","private","protected"},1));
        questions.add(new Question("10. What is the output of System.out.println(Math.floor(3.7));",
                new String[]{"3.0","4.0","3","4"},0));
    }
    private void showquestion() {
        if (currentQuestion < questions.size()) {
            Question q = questions.get(currentQuestion);
            questionLabel.setText(q.getQuestion());
            option1.setText(q.getOptions()[0]);
            option2.setText(q.getOptions()[1]);
            option3.setText(q.getOptions()[2]);
            option4.setText(q.getOptions()[3]);


            option1.setSelected(false);
            option2.setSelected(false);
            option3.setSelected(false);
            option4.setSelected(false);
        }
        else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/quizapplication/result.fxml"));
                Parent resultRoot = loader.load();

                ResultController resultController = loader.getController();
                resultController.setResult(score, questions.size());

                Stage stage = (Stage) nextButton.getScene().getWindow();
                stage.setScene(new Scene(resultRoot));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void handleNext(ActionEvent actionEvent) {
        int selectedIndex = - 1;
        if (option1.isSelected()) {
            selectedIndex = 0;
        } else if (option2.isSelected()) {
            selectedIndex = 1;
        } else if (option3.isSelected()) {
            selectedIndex = 2;
        } else if (option4.isSelected()) {
            selectedIndex = 3;
        }

        if (selectedIndex != -1){
            if (selectedIndex == questions.get(currentQuestion).getCorrectIndex()) {
                score++;
            }
            currentQuestion++;
            if (currentQuestion < questions.size()) {
                showquestion();
            }
            else {
                showquestion();
            }

        }
        else {
            System.out.println("Please select an option before continuing.");
        }
    }
    public void handleFinish(ActionEvent actionEvent) {
        System.exit(0);
    }
}
