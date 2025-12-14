package horseBetting.race;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;

public class InteractableTest {
    Horse horse;
    double initialSpeed;

    Interactable powerup = new Powerup();
    Interactable obstacle = new Obstacle();

    @BeforeEach
    void setUp() {
        horse = new Horse(100);
        initialSpeed = horse.getSpeed();
    }
    
    @Test
    void powerupsSpeedUp() {
        horse.interact(powerup);
        assertEquals(initialSpeed * powerup.speedChange, horse.getSpeed());
        assertTrue(horse.getSpeed() > initialSpeed);
    }

    @Test
    void obstaclesSlowDown() {
        horse.interact(obstacle);
        assertEquals(initialSpeed * obstacle.speedChange, horse.getSpeed());
        assertTrue(horse.getSpeed() < initialSpeed);
    }
}
