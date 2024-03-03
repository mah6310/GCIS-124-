package peggame;
/**
 * The Move class represents a move in a peg game.
 * It stores the starting location (from) and the destination location (to) of the move.
 */
public class Move {
    
    private Location from; // The starting location of the move
    private Location to;   // The destination location of the move

    /**
     * Constructs a new Move with the specified starting and destination locations.
     * 
     * @param from The starting location of the move.
     * @param to The destination location of the move.
     */
    public Move(Location from, Location to) {
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the starting location of the move.
     * 
     * @return The starting location.
     */
    public Location getFrom() {
        return from;
    }

    /**
     * Returns the destination location of the move.
     * 
     * @return The destination location.
     */
    public Location getTo() {
        return to;
    }
}