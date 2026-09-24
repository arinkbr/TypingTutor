package com.mycompany.typingtutor;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;



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

        // Create the five rows of the virtual keyboard.
        HBox firstRow = createKeyboardRow(
                "` 1 2 3 4 5 6 7 8 9 0 - = Backspace");
        HBox secondRow = createKeyboardRow(
                "Q W E R T Y U I O P [ ] \\");
        HBox thirdRow = createKeyboardRow(
                "A S D F G H J K L ; '");
        HBox fourthRow = createKeyboardRow(
                "Shift Z X C V B N M , . /");
        HBox fifthRow = createKeyboardRow("Space");

        VBox keyboard = new VBox(8);
        keyboard.getChildren().addAll(
                firstRow,
                secondRow,
                thirdRow,
                fourthRow,
                fifthRow
        );

        // Arrange the controls vertically.
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.getChildren().addAll(
                titleLabel,
                instructionLabel,
                targetField,
                responseLabel,
                responseField,
                keyboard
        );

        Scene scene = new Scene(root, 900, 550);

        stage.setTitle("Typing Tutor");
        stage.setScene(scene);
        stage.show();

        responseField.requestFocus();
    }

    /**
     * Creates a row of keyboard buttons.
     *
     * @param keys the key names separated by spaces
     * @return a row containing the buttons
     */
    private HBox createKeyboardRow(String keys) {
        HBox row = new HBox(6);
        row.setAlignment(Pos.CENTER);

        String[] keyNames = keys.split(" ");

        for (String keyName : keyNames) {
            Button button = new Button(keyName);
            button.setPrefSize(45, 40);
            button.setFocusTraversable(false);

            if (keyName.equals("Backspace")) {
                button.setPrefWidth(110);
            } else if (keyName.equals("Shift")) {
                button.setPrefWidth(90);
            } else if (keyName.equals("Space")) {
                button.setPrefWidth(300);
            }

            row.getChildren().add(button);
        }

        return row;
    }

    public static void main(String[] args) {
        launch(args);
    }
}