package horseBetting.race;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

public class LaneTest {
    Lane lane = new Lane(0);

    @Test
    void laneHasDefaultValues() {
        assertTrue(lane.getHorse() != null);
        HashMap<Double, Interactable> interactables = lane.getInteractables();
        for (double position : interactables.keySet()) {
            assertTrue(interactables.get(position).getClass() == (new Powerup()).getClass() || interactables.get(position).getClass() == (new Obstacle()).getClass());
        }
    }

    @Test
    void updateMovesHorse() {
        double initialProgress = lane.getHorse().getProgress();
        lane.update();
        assertTrue(lane.getHorse().getProgress() > initialProgress);
    }

    @Test
    void updateReturnsInteractables() {
        Double min = 1.0;
        for (Double key : lane.getInteractables().keySet()) {
            min = (key < min) ? key : min;
        }
        while (lane.getHorse().getProgress() <= (min - lane.getHorse().getSpeed() * 0.1 - lane.getHorse().getGraphic().getWidth()/RaceManager.RACE_WIDTH/2)) {
            ArrayList<Interactable> hits = lane.update();
            assertTrue(hits.size() == 0);
        }
        ArrayList<Interactable> hits = lane.update();
        assertTrue(hits.size() > 0);
    }
}
