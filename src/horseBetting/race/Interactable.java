package horseBetting.race;

import edu.macalester.graphics.Ellipse;

public abstract class Interactable {
    public double speedChange;
    public double enduranceChange;
    public Ellipse graphic;
    public Ellipse getGraphic() {
        return graphic;
    }
}
