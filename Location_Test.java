package peggame;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class Location_Test {

    @Test
    public void testGetRow() {
        Location loc = new Location(5, 3);
        assertEquals("Row value should be 5", 5, loc.getRow());
    }


}
