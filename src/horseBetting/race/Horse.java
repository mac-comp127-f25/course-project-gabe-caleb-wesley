package horseBetting.race;

import edu.macalester.graphics.Image;
import edu.macalester.graphics.Rectangle;

import java.awt.Color;

public class Horse {
    private double speed;
    private double progress;
    private Image graphic;
    private double height;

    private Rectangle speedBackground;
    private double baseSpeed;
    
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

    public void move(double dt) {
        double displacement = speed * dt;
        progress += displacement;
        double x = progress * (RaceManager.RACE_WIDTH - 40) + 20;
        graphic.setCenter(x, height);

        if (speedBackground != null) {
            speedBackground.setCenter(x, height);
        }
    }

    public void interact(Interactable interactable) {
        speed *= interactable.speedChange;
        updateBackgroundColor();
        interactable.getGraphic().setFillColor(Color.WHITE);
        interactable.getGraphic().setStrokeColor(Color.WHITE);
    }

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

