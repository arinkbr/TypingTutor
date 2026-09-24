package com.mycompany.typingtutor;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * A program for practice typing.
 *
 * @author Arin Kabir
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        Label titleLabel = new Label("Typing Tutor");

        Label instructionLabel = new Label(
                "Type the following sentence:");

        TextField targetField = new TextField(
                "Try typing this text. Do it as quickly and accurately as you can.");
        targetField.setEditable(false);

        Label responseLabel = new Label("Your response:");

        TextField responseField = new TextField();
        responseField.setPromptText("Start typing here");

        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.getChildren().addAll(
                titleLabel,
                instructionLabel,
                targetField,
                responseLabel,
                responseField
        );

        Scene scene = new Scene(root, 800, 300);

        stage.setTitle("Typing Tutor");
        stage.setScene(scene);
        stage.show();

        responseField.requestFocus();
    }

    public static void main(String[] args) {
        launch(args);
    }
}