package peggame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class SquareBoard implements PegGame {
    private char[][] board;
    private GameState gameState;
    
    public char[][] getBoard() {
        return this.board;
        }
    
    public SquareBoard(int size) {
        this.board = new char[size][size+1];
        for (char[] row : board) {
            Arrays.fill(row, 'o');
        }
        this.gameState = GameState.NOT_STARTED;
    }

    @Override
    public Collection<Move> getPossibleMoves() {
        List<Move> moves = new ArrayList<>();
        // Iterate over the board
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                // If the current location has a peg
                if (board[i][j] == 'o') {
                    // Check all four directions (up, down, left, right)
                    for (int[] dir : new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}) {
                        int x = i + dir[0], y = j + dir[1];
                        // If the adjacent location also has a peg
                        if (x >= 0 && x < board.length && y >= 0 && y < board[i].length && board[x][y] == 'o') {
                            // Check if the location after that is within the array bounds and is empty
                            int newX = x + dir[0], newY = y + dir[1];
                            if (newX >= 0 && newX < board.length && newY >= 0 && newY < board[i].length && board[newX][newY] == '.') {
                                // Add this move to the list
                                moves.add(new Move(new Location(i, j), new Location(newX, newY)));
                            }
                        }
                    }
                }
            }
        }
        return moves;
    }
    

@Override
public GameState getGameState() {
    // Check if there are any possible moves left
    Collection<Move> possibleMoves = getPossibleMoves();
    
    // If there are no possible moves left, the game is lost
    if (possibleMoves.isEmpty()) {
        return GameState.STALEMATE;
    }
    // Otherwise, the game is still in progress
    else {
        return GameState.IN_PROGRESS;
    }
}


@Override
public void makeMove(Move move) throws PegGameException {
    Location from = move.getFrom();
    Location to = move.getTo();
    // Calculate the mid-point between the 'from' and 'to' locations
    int midRow = (from.getRow() + to.getRow()) / 2;
    int midCol = (from.getCol() + to.getCol()) / 2;
    // Validate the move
    if (board[from.getRow()][from.getCol()] != 'o' || board[to.getRow()][to.getCol()] != '.' || board[midRow][midCol] != 'o') {
        throw new PegGameException("Invalid move");
    }
    // Make the move
    board[from.getRow()][from.getCol()] = '.';
    board[midRow][midCol] = '.';
    board[to.getRow()][to.getCol()] = 'o';
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
    

}