package peggame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The Reader class provides methods for reading game data from files.
 */
public class Reader {
    
    /**
     * Reads the game state from a file and returns a PegGame object representing the game.
     * 
     * @param filename The path to the file containing the game state.
     * @return A PegGame object representing the game state.
     * @throws FileNotFoundException if the specified file cannot be found.
     */
    public static PegGame readGameFromFile(String filename) throws FileNotFoundException {
        File file = new File(filename);
        try (Scanner scanner = new Scanner(file)) {
            int size = scanner.nextInt();  // Reads the size of the board (first line)
            scanner.nextLine();  // Consume the remainder of the first line

            char[][] board = new char[size][size];
            for (int i = 0; i < size; i++) {
                String line = scanner.nextLine();
                if (line.length() != size) {
                    throw new IllegalArgumentException("Each row must contain " + size + " characters.");
                }
                for (int j = 0; j < size; j++) {
                    board[i][j] = line.charAt(j);
                }
            }
            return new SquareBoard(board);
        }
    }
}
