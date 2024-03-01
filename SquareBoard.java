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
        this.board = new char[size][size];
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
                    // If the adjacent location also has a peg and the location after that is empty
                    if (x >= 0 && x < board.length && y >= 0 && y < board[i].length && board[x][y] == 'o' && board[x + dir[0]][y + dir[1]] == '.') {
                        // Add this move to the list
                        moves.add(new Move(new Location(i, j), new Location(x + dir[0], y + dir[1])));
                    }
                }
            }
        }
    }
    return moves;
}

    @Override
    public GameState getGameState() {
        return this.gameState;
    }

    @Override
    public void makeMove(Move move) throws PegGameException {
        Location from = move.getFrom();
    Location to = move.getTo();
    // Validate the move
    if (board[from.getRow()][from.getCol()] != 'o' || board[to.getRow()][to.getCol()] != '.' || Math.abs(from.getRow() - to.getRow()) + Math.abs(from.getCol() - to.getCol()) != 2) {
        throw new PegGameException("Invalid move");
    }
    // Make the move
    board[from.getRow()][from.getCol()] = '.';
    board[(from.getRow() + to.getRow()) / 2][(from.getCol() + to.getCol()) / 2] = '.';
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