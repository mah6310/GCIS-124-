package peggame;

import java.util.Collection;

public interface PegGame {

    public enum GameState {
        NOT_STARTED,
        IN_PROGRESS,
        STALEMATE,
        WON
    }

    Collection<Move> getPossibleMoves();
    
    public GameState getGameState();

    public void makeMove(Move move) throws PegGameException;

}
