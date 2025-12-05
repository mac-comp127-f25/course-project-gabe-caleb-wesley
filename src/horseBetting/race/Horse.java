package horseBetting.race;

import edu.macalester.graphics.Image;
import horseBetting.MainGame;

import java.awt.Color;

public class Horse {
    private double speed;
    private double progress;
    private Image graphic;
    private double height;
    
    public Horse(double height) {
        speed = Utils.halfToOneRandomDouble() * 0.02;
        this.height = height;
        graphic = new Image("horse.png");
        graphic.setPosition(0, height);
        graphic.setMaxWidth(40);
    }

    public double getProgress() {
        return progress;
    }

    public Image getGraphic() {
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
