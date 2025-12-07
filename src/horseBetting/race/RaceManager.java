package horseBetting.race;

import java.util.ArrayList;

import horseBetting.MainGame;

public class RaceManager {
    private ArrayList<Lane> lanes;
    public static int numLanes = 10;
    public static final int RACE_WIDTH = MainGame.CANVAS_WIDTH - 100;
    public static final int RACE_HEIGHT = MainGame.CANVAS_HEIGHT - 100;
    
    public RaceManager() {
        lanes = new ArrayList<>();
        for (int i = 0; i < numLanes; i++) {
            lanes.add(new Lane(i));
        }
    }

    public Interactable runFrame() {
        Interactable found = null;
        for (Lane lane : lanes) {
            if (lane.getHorse().getProgress() < 1) {
                Interactable hit = lane.update();
                if (hit != null) {
                    found = hit;
                }
            }
        }
        return found;
    }

    public ArrayList<Lane> getLanes() {
        return lanes;
    }
}
