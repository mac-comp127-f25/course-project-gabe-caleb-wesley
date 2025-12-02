package horseBetting.race;

import edu.macalester.graphics.Ellipse;
import java.awt.Color;

public class Obstacle extends Interactable {

    public Obstacle() {
        speedChange = Utils.SixToEightTenths();
        graphic = new Ellipse(0, 0, 10, 10);
        graphic.setFillColor(Color.GRAY);
    }
}
