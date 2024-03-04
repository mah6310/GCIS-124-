package peggame;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class Move_Test {

    @Test
    
    public void testGetFrom() {
        Location from = new Location(7, 0);
        Location to = new Location(0, 1);
        Move move = new Move(from, to);
        assertEquals("From location should be (0,0)", from, move.getFrom());
    }

}
