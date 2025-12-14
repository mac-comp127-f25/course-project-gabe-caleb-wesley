package horseBetting.race;

import edu.macalester.graphics.Ellipse;

/**
 * Authors: Caleb Hatlevig, Gabe Guerrero, Wesley Stone
 * 
 * The abstract class for the interactables (powerups and obstacles).
 * Each one has graphics and a speed modifier applied to the horse that hits it.
 */
public abstract class Interactable {
    public double speedChange;
    public Ellipse graphic;
    public Ellipse getGraphic() {
        return graphic;
    }
}
