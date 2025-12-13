package horseBetting.race;

import java.util.ArrayList;

import horseBetting.MainGame;

public class RaceManager {
    private ArrayList<Lane> lanes;
    private Horse winner = null;
    public static final int NUM_LANES = 10;
    public static final int RACE_WIDTH = MainGame.CANVAS_WIDTH - 20;
    public static final int RACE_HEIGHT = MainGame.CANVAS_HEIGHT - 70;
    
    public RaceManager() {
        lanes = new ArrayList<>();
        for (int i = 0; i < NUM_LANES; i++) {
            lanes.add(new Lane(i));
        }
    }

    public ArrayList<Interactable> runFrame() {
        ArrayList<Interactable> found = new ArrayList<>();
        for (Lane lane : lanes) {
            if (lane.getHorse().getProgress() < 1) {
                ArrayList<Interactable> hits = lane.update();
                found.addAll(hits);
            } else {
                if (winner == null) {
                    winner = lane.getHorse();
                }
            }
        }
        return found;
    }

    public ArrayList<Lane> getLanes() {
        return lanes;
    }

    public Horse getWinner() {
        return winner;
    }
}
