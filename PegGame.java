package peggame;

import java.util.Collection;

public interface PegGame {
    enum GameState {
        NOT_STARTED, 
        IN_PROGRESS, 
        STALEMATE, 
        WON
    }

    Collection<Move> getPossibleMoves();
    GameState getGameState();
    void makeMove(Move move) throws PegGameException;
}
