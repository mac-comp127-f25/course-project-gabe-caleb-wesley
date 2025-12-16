package horseBetting.race;

import edu.macalester.graphics.Image;
import edu.macalester.graphics.Rectangle;

import java.awt.Color;

/**
 * Authors: Caleb Hatlevig, Gabe Guerrero, Wesley Stone
 * 
 * Represents a racing horse, each one has a speed, position, and visual representation. The horse can move
 * across the race track and interact with various interactable objects that modify
 * its speed. A visual speed indicator (background rectangle) changes color based on
 * the horse's current speed relative to its base speed.
 */
public class Horse {
    private double speed;
    private double progress;
    private Image graphic;
    private double height;

    private Rectangle speedBackground;
    private double baseSpeed;
    
    /**
     * Constructs a new horse, handling its graphics: height on the track, size, image, 
     * and background color, which scales according to speed.
     */
    public Horse(double height) {
        speed = Utils.halfToOneRandomDouble() * 0.02;
        this.height = height;
        graphic = new Image("horse.png");
        graphic.setPosition(0, height-20);
        graphic.setMaxWidth(40);
        baseSpeed = speed;
        speedBackground = new Rectangle(
            0, 0,
            graphic.getWidth() ,
            graphic.getHeight()
        );
        speedBackground.setCenter(graphic.getCenter().getX(), graphic.getCenter().getY());
        speedBackground.setStrokeColor(new Color(0, 0, 0, 0));
        speedBackground.setFillColor(new Color(255, 0, 0, 120));
        updateBackgroundColor();
    }

    public double getProgress() {
        return progress;
    }

    public Image getGraphic() {
        return graphic;
    }

    public Rectangle getSpeedBackground() {
        return speedBackground;
    }

    public double getSpeed() {
        return speed;
    }

    /**
     * Move the horse forward one frame at a time, multiplying its speed by time elapsed.
     * It only moves horizontally.
     */
    public void move(double dt) {
        double displacement = speed * dt;
        progress += displacement;
        double x = progress * (RaceManager.RACE_WIDTH - 40) + 20;
        graphic.setCenter(x, height);

        if (speedBackground != null) {
            speedBackground.setCenter(x, height);
        }
    }

    /**
     * Handle interactions between horse and powerups/obstacles.
     * Change the speed based on which type of object it is, and remove the object from the canvas.
     */
    public void interact(Interactable interactable) {
        speed *= interactable.speedChange;
        updateBackgroundColor();
        interactable.getGraphic().setFillColor(Color.WHITE);
        interactable.getGraphic().setStrokeColor(Color.WHITE);
    }

    /**
     * Update the background color to reflect the horse's color in real time, 
     * as it hits powerups and obstacles.
     */
    private void updateBackgroundColor() {
        if (speedBackground == null || baseSpeed <= 0) {
            return;
        }
        double ratio = speed / baseSpeed;
        ratio = Math.max(0.5, Math.min(ratio, 2.0));
        float t = (float) ((ratio - 0.5) / (2.0 - 0.5));
        float hue = 0.0f;
        float saturation = 1.0f;
        float brightness =1.0f - 0.6f * t;
        

        Color hsbRed = Color.getHSBColor(hue, saturation, brightness);
        int alpha = 100;
        Color tinted = new Color(hsbRed.getRed(), hsbRed.getGreen(), hsbRed.getBlue(), alpha);
        speedBackground.setFillColor(tinted);         
        }
}

