package peggame;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PegGame_Test {

    @Test
    public void testGetGameState() {
        PegGame game = (PegGame) new YourConcretePegGameClass();
        assertEquals(PegGame.GameState.NOT_STARTED, game.getGameState());
    }
}
