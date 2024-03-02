import java.io.*;
import java.util.*;

public class TestGame {
    private char[][] board;
    private int currentRow, currentCol;

    public TestGame(String filename) throws FileNotFoundException {
        Scanner fileReader = new Scanner(new File(filename));
        int size = fileReader.nextInt();
        fileReader.nextLine();  // consume newline

        board = new char[size][size];
        for (int i = 0; i < size; i++) {
            String line = fileReader.nextLine();
            for (int j = 0; j < size; j++) {
                board[i][j] = line.charAt(j);
            }
        }
    }

    public void printBoard() {
        for (char[] row : board) {
            for (char c : row) {
                System.out.print(c == 'o' ? 'o' : '-');
            }
            System.out.println();
        }
    }

    public boolean isValidMove(int r1, int c1, int r2, int c2) {
        // Check if the start and end positions are within the board
        if (r1 < 0 || r1 >= board.length || c1 < 0 || c1 >= board[0].length || r2 < 0 || r2 >= board.length || c2 < 0 || c2 >= board[0].length) {
            return false;
        }
    
        // Check if the start position has a peg and the end position is empty
        if (board[r1][c1] != 'o' || board[r2][c2] != '.') {
            return false;
        }
    
        // Check if the move is a valid jump
        if (Math.abs(r1 - r2) == 2 && c1 == c2 && board[(r1 + r2) / 2][c1] == 'o') {
            return true;
        }
        if (Math.abs(c1 - c2) == 2 && r1 == r2 && board[r1][(c1 + c2) / 2] == 'o') {
            return true;
        }
    
        return false;
    }    
    
    public void makeMove(int r1, int c1, int r2, int c2) {
        if (isValidMove(r1, c1, r2, c2)) {
            // Remove the jumped peg
            board[(r1 + r2) / 2][(c1 + c2) / 2] = '.';
            // Move the peg
            board[r2][c2] = 'o';
            board[r1][c1] = '.';
        } else {
            System.out.println("Invalid move");
        }
    }
    

    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printBoard();
            System.out.println("Enter a move (format: move r1 c1 r2 c2) or type 'quit' to exit:");
            String command = scanner.nextLine();
            if (command.equals("quit")) {
                System.out.println("Goodbye!");
                break;
            } else if (command.startsWith("move")) {
                String[] parts = command.split(" ");
                int r1 = Integer.parseInt(parts[1]);
                int c1 = Integer.parseInt(parts[2]);
                int r2 = Integer.parseInt(parts[3]);
                int c2 = Integer.parseInt(parts[4]);
                makeMove(r1, c1, r2, c2);
            }
        }
    }
    

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the path to your file:");
        String filename = scanner.nextLine();
        TestGame game = new TestGame(filename);
        game.playGame();
    }
}
