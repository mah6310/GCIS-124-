package peggame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import peggame.PegGame.GameState;

public class ControllerBoard {
    public static void playGame(PegGame game) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));//Change
        while (game.getGameState() == GameState.IN_PROGRESS) {
            System.out.println(game);
            System.out.println("Enter your move in the format 'move r1 c1 r2 c2', or 'quit' to quit:");
            String line = reader.readLine();
            if (line.toLowerCase().equals("quit")) {
                System.out.println("Goodbye, thanks for playing!");
                return;
            }
            String[] parts = line.split(" ");
            if (parts.length != 5 || !parts[0].equals("move")) {
                System.out.println("Invalid command. Please enter a command in the format 'move r1 c1 r2 c2'.");
                continue;
            }
            try {
                int r1 = Integer.parseInt(parts[1]);
                int c1 = Integer.parseInt(parts[2]);
                int r2 = Integer.parseInt(parts[3]);
                int c2 = Integer.parseInt(parts[4]);
                game.makeMove(new Move(new Location(r1, c1), new Location(r2, c2)));
            } catch (NumberFormatException a) {
                System.out.println("Wrong input. Row and column numbers must be integers.");
            } catch (PegGameException a) {
                System.out.println("Invalid move: " + a.getMessage());
            }
        }
        System.out.println(game);
        if (game.getGameState() == GameState.WON) {
            System.out.println("Congratulations! You are a professional! You WON!");
        } else {
            System.out.println("Uh oh, you lost! You are bad at this game. Better luck next time!");
        }
    }
}