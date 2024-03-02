package peggame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Reader {
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
