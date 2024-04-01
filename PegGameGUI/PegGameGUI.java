package PegGameGUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import peggame.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class PegGameGUI extends Application {

    // Define your game state and other variables here
    private enum State { NOT_STARTED, IN_PROGRESS, STALEMATE, WON }
    private State gameState = State.NOT_STARTED;
    private Circle[][] pegs;
    private Label stateLabel = new Label("State: " + gameState);
    private Circle emptyPeg;
    private Circle startPeg = null;

    @Override
    public void start(Stage primaryStage) {
        GridPane root = new GridPane();
        root.setHgap(0);
        root.setVgap(0);
        root.setPadding(new Insets(0, 0, 0, 0));
        // Use a FileChooser to get the file name from the user
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                int size = Integer.parseInt(reader.readLine());
                pegs = new Circle[size][size];
                for (int i = 0; i < size; i++) {
                    String line = reader.readLine();
                    for (int j = 0; j < size; j++) {
                        pegs[i][j] = new Circle(30, line.charAt(j) == 'o' ? Color.RED : Color.BLACK);
                        pegs[i][j].setOnMouseClicked(e -> handlePegClick(e));
                        root.add(pegs[i][j], i, j);
                        if (line.charAt(j) == '.') {
                            emptyPeg = pegs[i][j];
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        root.add(stateLabel, 0, 5);

        TextField saveField = new TextField();
        root.add(saveField, 1, 5);

        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> {
            // Save game state to file
            System.out.println("Saving game to " + saveField.getText());
        });
        root.add(saveButton, 2, 5);

        Scene scene = new Scene(root, 600, 600);

        primaryStage.setTitle("Peg Game");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Add your event handlers and game logic here
        primaryStage.setOnCloseRequest(e -> {
            System.out.println("Closing the game");
            System.exit(0);
        });
    }

    private void handlePegClick(MouseEvent e) {
        Circle clickedPeg = (Circle) e.getSource();
        int clickedX = -1, clickedY = -1;
    
        // Find the position of the clicked peg
        for (int i = 0; i < pegs.length; i++) {
            for (int j = 0; j < pegs[i].length; j++) {
                if (pegs[i][j] == clickedPeg) {
                    clickedX = i;
                    clickedY = j;
                }
            }
        }
    
        if (startPeg == null) {
            // If no start peg has been selected yet, set the clicked peg as the start peg
            if (((Color) clickedPeg.getFill()).equals(Color.RED)) {
                startPeg = clickedPeg;
            }
        } else {
            // If a start peg has already been selected, treat the clicked peg as the end location
            int startX = -1, startY = -1;
            for (int i = 0; i < pegs.length; i++) {
                for (int j = 0; j < pegs[i].length; j++) {
                    if (pegs[i][j] == startPeg) {
                        startX = i;
                        startY = j;
                    }
                }
            }
    
            int dx = startX - clickedX;
            int dy = startY - clickedY;
    
            // Check if the clicked location is two spots away from the start peg
            if (Math.abs(dx) == 2 && dy == 0 || dx == 0 && Math.abs(dy) == 2) {
                // Find the peg that was jumped over
                int overPegX = startX - dx / 2;
                int overPegY = startY - dy / 2;
                Circle overPeg = pegs[overPegX][overPegY];
    
                // Check if the overPeg is a peg and not an empty space
                if (((Color) overPeg.getFill()).equals(Color.RED)) {
                    // Swap the start peg and the clicked peg
                    Color tempColor = (Color) startPeg.getFill();
                    startPeg.setFill(clickedPeg.getFill());
                    clickedPeg.setFill(tempColor);
    
                    // The peg that was jumped over becomes the new empty peg
                    overPeg.setFill(Color.BLACK);
                    emptyPeg = overPeg;
    
                    // Update the game state
                    gameState = State.IN_PROGRESS;
                    stateLabel.setText("State: " + gameState);
                }
            }
    
            // Reset the start peg
            startPeg = null;
        }
    }
    
    

    public static void main(String[] args) {
        launch(args);
    }
}
