package peggame;

import java.util.Scanner;
/**
 * The Project1Main class serves as the entry point for the peg game application.
 * It prompts the user to enter the path to a file containing the initial state of the game.
 * The file is then read to initialize the game state, and the user is allowed to play the game through the command line interface.
 */
public class Project1Main {
    
    /**
     * The main method of the application.
     */
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter the path to your file:");
            String filename = scanner.nextLine();
            PegGame game = Reader.readGameFromFile(filename); // Reads the game state from the file
            ControllerBoard.playGame(game); // Allows the user to play the game through the command line
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
