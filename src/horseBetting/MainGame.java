package horseBetting;
import edu.macalester.graphics.CanvasWindow;
import horseBetting.race.*;

public class MainGame {
    public static final int CANVAS_WIDTH = 600;
    public static final int CANVAS_HEIGHT = 400;

    public MainGame() {

        RaceManager race = new RaceManager();
        CanvasWindow canvas = new CanvasWindow("Race", CANVAS_WIDTH, CANVAS_HEIGHT);

        for (Lane lane : race.getLanes()) {
            canvas.add(lane.getHorse().getGraphic());
            for (Interactable interactable : lane.getInteractables().values()) {
                canvas.add(interactable.getGraphic());
            }
        }

        canvas.animate(() -> {
            Interactable found = race.runFrame();
            if (found != null) {
                canvas.remove(found.getGraphic());
            }
        });
    }

    public static void main(String[] args) {
        new MainGame();
    }
}
