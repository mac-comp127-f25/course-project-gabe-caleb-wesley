package horseBetting.race;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HorseTest {
    Horse horse = new Horse(100);

    @Test
    void horseHasDefaultValues() {
        assertTrue(horse.getSpeed() >= 0.01 && horse.getSpeed() <= 0.02);
        assertEquals(horse.getProgress(), 0);
    }

    @Test
    void horseMovesCorrectly() {
        double startProgress = horse.getProgress();
        horse.move(0.1);
        assertEquals(horse.getProgress(), startProgress + horse.getSpeed() * 0.1);
    }
}
