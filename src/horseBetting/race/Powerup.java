package horseBetting.race;

import edu.macalester.graphics.Ellipse;
import java.awt.Color;

/**
 * Authors: Caleb Hatlevig, Gabe Guerrero, Wesley Stone
 * 
 * The powerups, which speed horses up on contact.
 */
public class Powerup extends Interactable {

    public Powerup() {
        speedChange = Utils.TwelveToFourteenTenths();
        graphic = new Ellipse(0, 0, 10, 10);
        graphic.setFillColor(Color.RED);
    }
}
