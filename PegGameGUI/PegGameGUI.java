package PegGameGUI;
/* PEG GAME GUI PROJECT
    BY:
    ABDULRAHMAN ALQAIWANI
    YOUSUF ABDULLAH
    MOHAMMAD HARIB
 

    In this code, we essentially transferred the main logic and concepts from the previous
    PegGame that was operated in command prompt and 
 */
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PegGameGUI extends Application {

    // Define your game state and other variables here
    private enum State { NOT_STARTED, IN_PROGRESS, STALEMATE, WON }
    private State gameState = State.NOT_STARTED;
    private Circle[][] pegs;
    private Label stateLabel = new Label("State: " + gameState);
    private Label errorLabel;

    private Circle emptyPeg;
    private Circle startPeg = null;
    private Color startPegOriginalColor;
    
    @Override
    public void start(Stage primaryStage) {
        GridPane root = new GridPane();

        root.setAlignment(Pos.CENTER);

        root.setHgap(0);
        root.setVgap(0);

        root.setPadding(new Insets(0, 0, 0, 0));
        root.setHgap(0); // Sets the  horizontal gap to 0
        root.setVgap(0); // Set vertical gap to 0

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the file path:");
        String filePath = scanner.nextLine();

File file = new File(filePath);
if (file.exists() && !file.isDirectory()) {
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        int size = Integer.parseInt(reader.readLine());
        pegs = new Circle[size][size];
        for (int i = 0; i < size; i++) {
            String line = reader.readLine();
            for (int j = 0; j < size; j++) {
                pegs[i][j] = new Circle(30, line.charAt(j) == 'o' ? Color.RED : Color.BLACK);
                pegs[i][j].setOnMouseClicked(e -> handlePegClick(e));
                root.add(pegs[i][j], i, j);
                GridPane.setFillWidth(pegs[i][j], true); 
                GridPane.setFillHeight(pegs[i][j], true); 
                GridPane.setHgrow(pegs[i][j], Priority.ALWAYS); 
                GridPane.setVgrow(pegs[i][j], Priority.ALWAYS);
                if (line.charAt(j) == '.') {
                    emptyPeg = pegs[i][j];
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    } else {
        System.out.println("File does not exist or it is a directory.");
    }

    root.add(stateLabel, 0, 5);

    TextField saveField = new TextField("Enter save file name here (.txt), then press Save :");
    root.add(saveField, 1, 5);

    errorLabel = new Label();
    errorLabel.setAlignment(Pos.TOP_CENTER);

    VBox vbox = new VBox();

    vbox.getChildren().addAll(errorLabel, root);

    Button saveButton = new Button("Save");
    saveButton.setOnAction(e -> {
    // Save game state to file
    String filename = saveField.getText();
    if (!filename.endsWith(".txt")) {
        filename += ".txt";
    }
    try (FileWriter writer = new FileWriter(filename)) {
        // Write the size of the board
        writer.write(pegs.length + "\n");
        // Write the state of each peg
        for (int i = 0; i < pegs.length; i++) {
            for (int j = 0; j < pegs[i].length; j++) {
                if (((Color) pegs[i][j].getFill()).equals(Color.RED)) {
                    writer.write('o');
                } else {
                    writer.write('.');
                }
            }
            writer.write("\n");
        }
        System.out.println("Saving game to " + filename);
    } catch (IOException ex) { //catches an error if input is incorrect or interuptted, to avoid program crash
        System.out.println("An error occurred while trying to save the game.");
        ex.printStackTrace();
    }
    });
    root.add(saveButton, 2, 5);



        Button closeButton = new Button("Press to Close");
        closeButton.setOnAction(e -> {
    // Closes the current window once the button is clicked
        primaryStage.close();
    });
        root.add(closeButton, 3, 5);

        
        
        Scene scene = new Scene(vbox, 800, 600);

        primaryStage.setTitle("Peg Game");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Add your event handlers and game logic here
        primaryStage.setOnCloseRequest(e -> {
            System.out.println("Closing the game");
            System.exit(0);
        });
    }

    private int countPegs() { //simple nested for loop that counts the num of pegs, if the red peg is detected, it adds to counter
        int count = 0;
        for (int i = 0; i < pegs.length; i++) {
            for (int j = 0; j < pegs[i].length; j++) {
                if (((Color) pegs[i][j].getFill()).equals(Color.RED)) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean hasValidMoves() {
        for (int i = 0; i < pegs.length; i++) {
            for (int j = 0; j < pegs[i].length; j++) {
                if (((Color) pegs[i][j].getFill()).equals(Color.RED)) {
                    // Check all four directions around the peg
                    if (i > 1 && ((Color) pegs[i-1][j].getFill()).equals(Color.RED) && ((Color) pegs[i-2][j].getFill()).equals(Color.BLACK)) {
                        return true;
                    }
                    if (i < pegs.length - 2 && ((Color) pegs[i+1][j].getFill()).equals(Color.RED) && ((Color) pegs[i+2][j].getFill()).equals(Color.BLACK)) {
                        return true;
                    }
                    if (j > 1 && ((Color) pegs[i][j-1].getFill()).equals(Color.RED) && ((Color) pegs[i][j-2].getFill()).equals(Color.BLACK)) {
                        return true;
                    }
                    if (j < pegs[i].length - 2 && ((Color) pegs[i][j+1].getFill()).equals(Color.RED) && ((Color) pegs[i][j+2].getFill()).equals(Color.BLACK)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    

    private void handlePegClick(MouseEvent e) {
        
        Circle clickedPeg = (Circle) e.getSource();
        int clickedX = -1, clickedY = -1;
    
        // Find the position of the clicked peg using the mouse event.
        for (int i = 0; i < pegs.length; i++) {
            for (int j = 0; j < pegs[i].length; j++) {
                if (pegs[i][j] == clickedPeg) {
                    clickedX = i;
                    clickedY = j;
                }
            }
        }
    
        // If no start peg has been selected yet, set the clicked peg as the start peg
        if (startPeg == null && ((Color) clickedPeg.getFill()).equals(Color.RED)) {
            startPeg = clickedPeg;
            startPegOriginalColor = (Color) startPeg.getFill();
            startPeg.setFill(Color.BLUE); // Change the color of the selected peg
    
            // Update the game state to IN_PROGRESS as soon as peg is clicked
            gameState = State.IN_PROGRESS;
            stateLabel.setText("State: " + gameState);
        } else if (startPeg != null) {
            // If the start peg is already been selected, treat the clicked peg asend location
            int startX = -1, startY = -1;
            for (int i = 0; i < pegs.length; i++) {
                for (int j = 0; j < pegs[i].length; j++) {
                    if (pegs[i][j] == startPeg) {
                        startX = i;
                        startY = j;
                    }
                }
            }
    
            int dx = startX - clickedX; //Calculates the difference between the starting position and ending position.
            int dy = startY - clickedY;
    
            // Check if the clicked location is two spots away from the start peg
            if (Math.abs(dx) == 2 && dy == 0 || dx == 0 && Math.abs(dy) == 2) {
                // Find the peg that was jumped over
                int overPegX = startX - dx / 2;
                int overPegY = startY - dy / 2;
                Circle overPeg = pegs[overPegX][overPegY];
    
                // Check if the overPeg is a peg and not an empty space
                if (((Color) overPeg.getFill()).equals(Color.RED) && ((Color) clickedPeg.getFill()).equals(Color.BLACK)) {
                    // The peg that was jumped over becomes the new empty peg
                    overPeg.setFill(Color.BLACK);
    
                    // The start peg becomes an empty space
                    startPeg.setFill(Color.BLACK);
    
                    // The clicked peg becomes a red peg
                    clickedPeg.setFill(Color.RED);
    
                    // Update the game state
                    if (countPegs() == 1) {
                        gameState = State.WON;
                    } else if (hasValidMoves()) {
                        gameState = State.IN_PROGRESS;
                    } else {
                        gameState = State.STALEMATE;
                    }
                    stateLabel.setText("State: " + gameState);
                    
                } else {
                    // If the move is not valid, display a message
                    errorLabel.setText("Invalid move. You must jump over a peg into an empty space.");
                }
    
                // Reset the start peg
                startPeg = null;
            } else {
                // If the move is not valid, reset the color of the start peg and clear the start peg
                startPeg.setFill(startPegOriginalColor);
                startPeg = null;
                // Display a message
                errorLabel.setText("Invalid move. You must move to a spot two spaces away.");
            }
        }
    }
    
    
    public static void main(String[] args) {
        launch(args);
    }
}