package horseBetting.race;

import edu.macalester.graphics.Ellipse;
import java.awt.Color;

/**
 * Authors: Caleb Hatlevig, Gabe Guerrero, Wesley Stone
 * 
 * The obstacles, which slow horses on contact.
 */
public class Obstacle extends Interactable {

    public Obstacle() {
        speedChange = Utils.SixToEightTenths();
        graphic = new Ellipse(0, 0, 10, 10);
        graphic.setFillColor(Color.GRAY);
    }
}
