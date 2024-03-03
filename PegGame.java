package peggame;

import java.util.Collection;

/**
 * The PegGame interface represents a peg game, which is a game played on a board with pegs and holes.
 * It defines methods for accessing possible moves, getting the game state, and making moves.
 */
public interface PegGame {
    
    /**
     * Enum representing the possible states of the peg game.
     */
    enum GameState {
        NOT_STARTED, // The game has not started yet
        IN_PROGRESS, // The game is in progress
        STALEMATE,   // The game has reached a stalemate (no more valid moves)
        WON          // The game has been won
    }

    /**
     * Returns a collection of possible moves that can be made in the current game state.
     * 
     * @return A collection of possible moves.
     */
    Collection<Move> getPossibleMoves();
    
    /**
     * Returns the current state of the game.
     * 
     * @return The game state.
     */
    GameState getGameState();
    
    /**
     * Makes a move in the game.
     * 
     * @param move The move to make.
     * @throws PegGameException if the move is invalid or cannot be made.
     */
    void makeMove(Move move) throws PegGameException;
}

