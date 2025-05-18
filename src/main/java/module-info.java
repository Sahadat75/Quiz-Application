module com.example.quizapplication {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens com.example.quizapplication to javafx.fxml;
    exports com.example.quizapplication;
    exports com.example.quizapplication.model;
    opens com.example.quizapplication.model to javafx.fxml;
}