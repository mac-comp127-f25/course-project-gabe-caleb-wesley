package horseBetting.race;

import edu.macalester.graphics.Ellipse;
import horseBetting.MainGame;

import java.awt.Color;

public class Horse {
    private double speed;
    private double progress;
    private Ellipse graphic;
    private double height;
    
    public Horse(double height) {
        speed = Utils.halfToOneRandomDouble() * 0.02;
        this.height = height;
        graphic = new Ellipse (0, height, 20, 20);
        graphic.setFillColor(Color.BLUE);
    }

    public double getProgress() {
        return progress;
    }

    public Ellipse getGraphic() {
        return graphic;
    }

    public void move(double dt) {
        double displacement = speed * dt;
        progress += displacement;
        graphic.setCenter(progress * MainGame.CANVAS_WIDTH, height);
    }

    public void interact(Interactable interactable) {
        speed *= interactable.speedChange;
        interactable.getGraphic().setFillColor(Color.WHITE);
        interactable.getGraphic().setStrokeColor(Color.WHITE);
    }
}
