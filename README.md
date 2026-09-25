# Typing Tutor

Assignment 01  
Arin Kabir

A JavaFX application to practice typing with six sample sentences.

## How to run

Open the TypingTutor project in NetBeans. Wait for Maven to
download the dependencies, then right-click the project and
select Run.

The project uses JavaFX 21.0.9 and Maven.

## How to use

Click the response field and type the sentence shown above it.

The virtual keys turn blue when you press the matching keys on
your keyboard. They return to normal when you release them.
The label shows the key you pressed, or "Not handled" in red
if the key is not on the virtual keyboard.

Use Shift for uppercase letters and Backspace to fix mistakes.
The virtual buttons only show key presses; clicking them
does not type anything.

Next shows the next sentence and clears your response and scores.
It becomes disabled when you reach sentence 6.

Reset takes you back to sentence 1 and clears the response,
scores, and key display.

## How the scores work

Each character you type is checked against the target sentence
at the position where you are typing.

If it matches, Correct goes up by one. Otherwise, Incorrect
goes up by one. Uppercase and lowercase letters are different.
Spaces and punctuation count too.

Backspace removes text, but the previous attempt stays in the
score. Retyping a character counts as another attempt.

Shift and other control keys are not scored. Pasting text
does not add to the scores.

## Project files

- App.java contains the interface and program logic.
- module-info.java declares the module and its dependencies.
- pom.xml contains the Maven configuration.
- nbactions.xml contains the NetBeans run settings.
- .gitignore keeps generated files out of the repository.