package horseBetting.race;

import java.awt.Color;
import java.util.HashMap;
import java.util.Random;

import edu.macalester.graphics.Line;

import horseBetting.MainGame;

public class Lane {
    private Random rand = new Random();

    private Horse horse;
    private Line divider;
    private HashMap<Double, Interactable> interactables = new HashMap<Double, Interactable>();

    public Lane(int index) {
        double height = (RaceManager.RACE_HEIGHT/RaceManager.numLanes)*(index+0.5);
        horse = new Horse(height);
        divider = new Line(0, height + 20, 700, height + 20);
        divider.setStrokeColor(Color.WHITE);

        double interactableProgress = 0;
        for (int i = 0; i < 3; i++) {
            interactableProgress += (1 - interactableProgress) * rand.nextDouble();
            if (rand.nextDouble() < 0.5) {
                interactables.put(interactableProgress, new Powerup());
            } else {
                interactables.put(interactableProgress, new Obstacle());
            }
            interactables.get(interactableProgress).getGraphic().setCenter(interactableProgress * MainGame.CANVAS_WIDTH, height);
        }
    }

    public Interactable update() {
        double start = horse.getProgress();
        horse.move(0.1);
        double end = horse.getProgress();
        if (end >= 1) {
            return null;
        }
        for (Double key : interactables.keySet()) {
            if (key >= start && key <= end) {
                horse.interact(interactables.get(key));
                return interactables.get(key);
            };
        }
        return null;
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
