package peggame;
/**
 * The Location class represents a position on a two-dimensional grid.
 * It stores the row and column indices of the location.
 */
public class Location {
    
    private int row; // The row index of the location
    private int col; // The column index of the location

    /**
     * Constructs a new Location with the specified row and column indices.
     * 
     * @param row The row index of the location.
     * @param col The column index of the location.
     */
    public Location(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Returns the row index of the location.
     * 
     * @return The row index.
     */
    public int getRow() {
        return row;
    }

    /**
     * Returns the column index of the location.
     * 
     * @return The column index.
     */
    public int getCol() {
        return col;
    }
}