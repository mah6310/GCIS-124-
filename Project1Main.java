package peggame;

import java.util.Scanner;

public class Project1Main {
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
