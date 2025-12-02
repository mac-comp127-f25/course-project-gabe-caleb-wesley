package horseBetting.race;

import edu.macalester.graphics.Ellipse;
import java.awt.Color;

public class Powerup extends Interactable {

    public Powerup() {
        speedChange = Utils.TwelveToFourteenTenths();
        graphic = new Ellipse(0, 0, 10, 10);
        graphic.setFillColor(Color.RED);
    }
}
