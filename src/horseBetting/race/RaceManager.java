package horseBetting.race;

import java.util.ArrayList;
import java.util.List;

public class RaceManager {
    private ArrayList<Lane> lanes;
    public static int numLanes = 10;
    
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
