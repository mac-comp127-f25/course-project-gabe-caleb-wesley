package horseBetting.gambling;

import org.junit.jupiter.api.Test;

import horseBetting.race.Horse;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Authors: Caleb Hatlevig, Gabe Guerrero, Wesley Stone
 * 
 * Tests of the Gambler's functionality.
 */
public class GamblerTest {
    private Gambler gambler = new Gambler();

    @Test
    void gamblerHasDefaultValues() {
        assertEquals(1000, gambler.getMoney());
        assertEquals(null, gambler.getHorse());
        assertEquals(0, gambler.getBet());
    }

    @Test
    void gamblerIsEditable() {
        gambler.addMoney(1000);
        gambler.setBet(100);
        Horse horse = new Horse(100);
        gambler.setHorse(horse);

        assertEquals(2000.0, gambler.getMoney());
        assertEquals(horse, gambler.getHorse());
        assertEquals(100, gambler.getBet());
    }
}
