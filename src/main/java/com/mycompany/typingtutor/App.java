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
import java.util.HashMap;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * A program to practice typing.
 *
 * @author Arin Kabir
 */
public class App extends Application {

    private HashMap<KeyCode, Button> keyButtons = new HashMap<>();

    private String[] practiceTexts = {
        "Try typing this text. Do it as quickly and accurately as you can.",
        "Next type another line of input data.",
        "The quick brown fox jumps over the lazy dog.",
        "Five big quacking zephyrs jolt my wax bed.",
        "Sympathizing would fix Quaker objectives.",
        "A large fawn jumped quickly over white zinc boxes."
    };

    private int currentTextIndex = 0;

    @Override
    public void start(Stage stage) {
        Label titleLabel = new Label("Typing Tutor");

        Label instructionLabel = new Label(
                "Type the following sentence:");

        Label progressLabel = new Label(
                (currentTextIndex + 1) + " of " + practiceTexts.length);

        TextField targetField = new TextField(
                practiceTexts[currentTextIndex]);
        targetField.setEditable(false);

        Label responseLabel = new Label("Your response:");

        TextField responseField = new TextField();
        responseField.setPromptText("Start typing here");

        Label keyLabel = new Label("Key pressed: None");

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

        Button nextButton = new Button("Next");

        // Move to the next sentence and clear the previous response.
        nextButton.setOnAction(event -> {
            if (currentTextIndex < practiceTexts.length - 1) {
                currentTextIndex++;

                targetField.setText(practiceTexts[currentTextIndex]);
                responseField.clear();

                progressLabel.setText(
                        (currentTextIndex + 1) + " of " + practiceTexts.length);
            }

            if (currentTextIndex == practiceTexts.length - 1) {
                nextButton.setDisable(true);
            }

            responseField.requestFocus();
        });

        Button resetButton = new Button("Reset");

        // Return to the first sentence and reset the current state.
        resetButton.setOnAction(event -> {
            currentTextIndex = 0;

            targetField.setText(practiceTexts[currentTextIndex]);
            responseField.clear();

            progressLabel.setText(
                    (currentTextIndex + 1) + " of " + practiceTexts.length);

            keyLabel.setText("Key pressed: None");
            keyLabel.setStyle("-fx-text-fill: black;");

            for (Button button : keyButtons.values()) {
                button.setStyle("");
            }

            nextButton.setDisable(false);
            responseField.requestFocus();
        });

        HBox navigation = new HBox(10);
        navigation.getChildren().addAll(nextButton, resetButton);

        // Arrange the controls vertically.
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.getChildren().addAll(
                titleLabel,
                instructionLabel,
                progressLabel,
                targetField,
                responseLabel,
                responseField,
                keyLabel,
                keyboard,
                navigation
        );

        Scene scene = new Scene(root, 900, 550);

        // Highlight the matching button when a key is pressed.
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            Button button = keyButtons.get(event.getCode());

            if (button != null) {
                button.setStyle("-fx-background-color: lightblue;");
                keyLabel.setText("Key pressed: " + button.getText());
                keyLabel.setStyle("-fx-text-fill: black;");
            } else {
                keyLabel.setText("Not handled");
                keyLabel.setStyle("-fx-text-fill: red;");
            }
        });

        // Restore the button's appearance when the key is released.
        scene.addEventFilter(KeyEvent.KEY_RELEASED, event -> {
            Button button = keyButtons.get(event.getCode());

            if (button != null) {
                button.setStyle("");
            }
        });

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

            KeyCode code = getKeyCode(keyName);
            keyButtons.put(code, button);

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

    /**
     * Finds the physical key code for a virtual key.
     *
     * @param keyName the text on the button
     * @return the matching key code
     */
    private KeyCode getKeyCode(String keyName) {
        switch (keyName) {
            case "`":
                return KeyCode.BACK_QUOTE;
            case "-":
                return KeyCode.MINUS;
            case "=":
                return KeyCode.EQUALS;
            case "[":
                return KeyCode.OPEN_BRACKET;
            case "]":
                return KeyCode.CLOSE_BRACKET;
            case "\\":
                return KeyCode.BACK_SLASH;
            case ";":
                return KeyCode.SEMICOLON;
            case "'":
                return KeyCode.QUOTE;
            case ",":
                return KeyCode.COMMA;
            case ".":
                return KeyCode.PERIOD;
            case "/":
                return KeyCode.SLASH;
            case "Backspace":
                return KeyCode.BACK_SPACE;
            default:
                return KeyCode.getKeyCode(keyName);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}