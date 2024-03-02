package peggame;

import java.util.Scanner;

public class ControllerBoard {
    public static void playGame(PegGame game) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println(game);
                System.out.println("Enter your move (format: 'move r1 c1 r2 c2') or type 'quit' to exit:");

                String input = scanner.nextLine().trim();
                if ("quit".equalsIgnoreCase(input)) {
                    System.out.println("Exiting game. Goodbye!");
                    break;
                } else if (input.startsWith("move")) {
                    try {
                        String[] parts = input.split("\\s+");
                        int r1 = Integer.parseInt(parts[1]);
                        int c1 = Integer.parseInt(parts[2]);
                        int r2 = Integer.parseInt(parts[3]);
                        int c2 = Integer.parseInt(parts[4]);

                        Move move = new Move(new Location(r1, c1), new Location(r2, c2));
                        game.makeMove(move);
                        if (game.getGameState() == PegGame.GameState.WON) {
                            System.out.println("Congratulations! You've won!");
                            break;
                        } else if (game.getGameState() == PegGame.GameState.STALEMATE) {
                            System.out.println("It's a stalemate!");
                            break;
                        }
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
