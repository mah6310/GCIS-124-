package peggame;

import java.util.*;

public class SquareBoard implements PegGame {
    private char[][] board;

    public SquareBoard(char[][] cs) {
        this.board = cs;
    }

    @Override
    public Collection<Move> getPossibleMoves() {
        List<Move> validMoves = new ArrayList<>();
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == 'o') { // Found a peg
                    // Check all four possible directions
                    int[][] directions = {{-2, 0}, {2, 0}, {0, -2}, {0, 2}};
                    for (int[] dir : directions) {
                        int destRow = row + dir[0];
                        int destCol = col + dir[1];
                        int jumpedRow = row + dir[0] / 2;
                        int jumpedCol = col + dir[1] / 2;
    
                        if (isValidDestination(destRow, destCol) && board[jumpedRow][jumpedCol] == 'o' && board[destRow][destCol] == '.') {
                            validMoves.add(new Move(new Location(row, col), new Location(destRow, destCol)));
                        }
                    }
                }
            }
        }
        return validMoves;
    }
    
    private boolean isValidDestination(int row, int col) {
        return row >= 0 && row < board.length && col >= 0 && col < board[row].length;
    }
    

    public GameState getGameState() {
        boolean movesAvailable = !getPossibleMoves().isEmpty();
        int pegsCount = countPegs();
    
        if (pegsCount == 1) {
            return GameState.WON;
        } else if (movesAvailable) {
            return GameState.IN_PROGRESS;
        } else {
            return GameState.STALEMATE;
        }
    }
    
    private int countPegs() {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'o') {
                    count++;
                }
            }
        }
        return count;
    }
    

    @Override
public void makeMove(Move move) throws PegGameException {
    int r1 = move.getFrom().getRow();
    int c1 = move.getFrom().getCol();
    int r2 = move.getTo().getRow();
    int c2 = move.getTo().getCol();

    // Check if the move is within the bounds of the board
    if (!isWithinBounds(r1, c1) || !isWithinBounds(r2, c2)) {
        throw new PegGameException("Move is out of bounds.");
    }

    // Check if the move is valid according to the game's rules
    if (isValidMove(r1, c1, r2, c2)) {
        // Execute the move
        board[r1][c1] = '.'; // Set starting position to empty
        board[(r1 + r2) / 2][(c1 + c2) / 2] = '.'; // Remove the jumped peg
        board[r2][c2] = 'o'; // Set ending position to contain the peg
    } else {
        // Handle an invalid move attempt
        throw new PegGameException("Invalid move.");
    }
}

private boolean isValidMove(int r1, int c1, int r2, int c2) {
    // Check if the move is within the bounds of the board
    if (!isWithinBounds(r1, c1) || !isWithinBounds(r2, c2)) {
        return false;
    }

    // Ensure the move is from a peg to an empty space
    if (board[r1][c1] != 'o' || board[r2][c2] != '.') {
        return false;
    }

    // Calculate the midpoints between the old and new positions
    int midRow = (r1 + r2) / 2;
    int midCol = (c1 + c2) / 2;

    // Check if the midpoint has a peg that will be "jumped over"
    if (board[midRow][midCol] != 'o') {
        return false;
    }

    // Ensure that the move is in a straight line and two spaces long
    if (Math.abs(r1 - r2) != 2 && Math.abs(c1 - c2) != 2) {
        return false;
    }

    // Ensure that the move is strictly horizontal or vertical
    if (r1 != r2 && c1 != c2) {
        return false;
    }

    return true;
}

private boolean isWithinBounds(int row, int col) {
    return row >= 0 && row < board.length && col >= 0 && col < board[row].length;
}


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (char[] row : board) {
            for (char c : row) {
                sb.append(c);
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public char[][] getBoard() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBoard'");
    }
}
