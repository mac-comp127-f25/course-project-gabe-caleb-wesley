package horseBetting.race;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import edu.macalester.graphics.Line;

/**
 * Authors: Caleb Hatlevig, Gabe Guerrero, Wesley Stone
 * 
 * The racecourse is made up of ten horizontal lanes stacked atop one another.
 * The lanes each have one horse and three interactables, and which interactables have been hit is tracked.
 */
public class Lane {
    private Random rand = new Random();

    private Horse horse;
    private Line divider;
    private HashMap<Double, Interactable> interactables = new HashMap<Double, Interactable>();
    private static final double LANE_HEIGHT = RaceManager.RACE_HEIGHT / RaceManager.NUM_LANES;

    /**
     * Constructs the Lanes, which contain a horse and three interactables, a random combination of powerups and obstacles.
     * Also handles the graphics of the Lane dividers.
     */
    public Lane(int index) {
        double height = LANE_HEIGHT*(index+0.5);
        horse = new Horse(height);
        divider = new Line(0, height + LANE_HEIGHT/2, RaceManager.RACE_WIDTH - 1, height + LANE_HEIGHT/2);
        divider.setStrokeColor(Color.WHITE);

        double interactableProgress = 0.1;
        for (int i = 0; i < 3; i++) {
            interactableProgress += Utils.TenthToThird();
            if (rand.nextDouble() < 0.5) {
                interactables.put(interactableProgress, new Powerup());
            } else {
                interactables.put(interactableProgress, new Obstacle());
            }
            interactables.get(interactableProgress).getGraphic().setCenter(interactableProgress * RaceManager.RACE_WIDTH, height);
        }
    }

    /**
     * Track when the horse hits an interactable, and record it as hit.
     * @return Which interactables in the lane have been hit.
     */
    public ArrayList<Interactable> update() {
        double start = horse.getProgress();
        horse.move(0.1);
        double end = horse.getProgress();
        ArrayList<Interactable> removed = new ArrayList<>();
        double graphicFront = horse.getGraphic().getWidth()/RaceManager.RACE_WIDTH/2;
        for (Double key : interactables.keySet()) {
            if (key >= start + graphicFront && key <= end + graphicFront) {
                horse.interact(interactables.get(key));
                removed.add(interactables.get(key));
            };
        }
        return removed;
    }

    public Horse getHorse() {
        return horse;
    }

    public Line getDivider() {
        return divider;
    }

    public HashMap<Double, Interactable> getInteractables() {
        return interactables;
    }
}
