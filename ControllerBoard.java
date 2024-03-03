package peggame;

import java.util.Scanner;

// Controller Board class is responsible for the interactions from the user and the peggame.

public class ControllerBoard {
    public static void playGame(PegGame game) {
        try (Scanner scanner = new Scanner(System.in)) { 
            while (true) {
//The user can input moves in the format 'move r1 c1 r2 c2', where r1 and c1 are the row and column.
                System.out.println(game);
                System.out.println("Input your next move! \n use the following format: 'move r1 c1 r2 c2' \n type 'quit' to exit:"); 
//It provides a method to play the game, allowing the user to input moves via the command line.
//The game continues until the user types quit as the input or the game reaches a win or stalemate.
                String input = scanner.nextLine().trim();
                if ("quit".equalsIgnoreCase(input)) {
                    System.out.println("Exiting game session... Goodbye!");
                    break;
                } else if (input.startsWith("move")) {
                    try { // r1 and c1 represents the coorinates of the peg you want to move, while r2 and c2 represent its target hole.
                        String[] parts = input.split("\\s+");
                        int r1 = Integer.parseInt(parts[1]);
                        int c1 = Integer.parseInt(parts[2]);
                        int r2 = Integer.parseInt(parts[3]);
                        int c2 = Integer.parseInt(parts[4]);
                        Move move = new Move(new Location(r1, c1), new Location(r2, c2));
                        game.makeMove(move);
//Approaching the end of the game, a message is displayed to notify the user regarding the outcome.(WIN, LOSS OR STALEMATE)
                        if (game.getGameState() == PegGame.GameState.WON) {
                            System.out.println("Congratulations! You've won!");
                            break;
                        } else if (game.getGameState() == PegGame.GameState.STALEMATE) {
                            System.out.println("It's a stalemate!");
                            break;
                        }
//The class ensures proper handling of user input, displaying error messages for invalid commands or moves.

                    } catch (Exception e) {
                        System.out.println("Invalid command or move. Please try again.");
                    }
                } else {
                    System.out.println("Invalid command. Please use the correct format: 'move r1 c1 r2 c2'");
                }
            }
        }
    }
}