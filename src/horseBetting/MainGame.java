package horseBetting;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Rectangle;
import java.awt.Color;

import edu.macalester.graphics.ui.Button;
import edu.macalester.graphics.ui.TextField;
import horseBetting.race.*;

public class MainGame {
    public static final int CANVAS_WIDTH = 700;
    public static final int CANVAS_HEIGHT = 500;

    public MainGame() {

        RaceManager race = new RaceManager();
        CanvasWindow canvas = new CanvasWindow("Race", CANVAS_WIDTH, CANVAS_HEIGHT);

        Rectangle background = new Rectangle(0, 0, 700, 400);
        background.setFillColor(Color.getHSBColor((float) 0.10, (float) 0.70, (float) 0.72));
        canvas.add(background);

        for (Lane lane : race.getLanes()) {
            canvas.add(lane.getHorse().getGraphic());
            canvas.add(lane.getDivider());
            for (Interactable interactable : lane.getInteractables().values()) {
                canvas.add(interactable.getGraphic());
            }
        }

        TextField bet = new TextField();
        bet.setCenter(500, 460);
        canvas.add(bet);

        // money = Gambler.getMoney()
        for (int i = 1; i < 11; i++) {
            Button selectHorse = new Button(String.valueOf(i));
            selectHorse.setCenter(70*i - 30, 430);
            canvas.add(selectHorse);
        }

        Button bet1 = new Button("Bet $1");
        bet1.setCenter(50, 460);
        canvas.add(bet1); 
        
        Button bet5 = new Button("Bet $5");
        bet5.setCenter(150, 460);
        canvas.add(bet5);

        Button bet10 = new Button("Bet $10");
        bet10.setCenter(250, 460);
        canvas.add(bet10); 
        
        Button bet50 = new Button("Bet $50");
        bet50.setCenter(350, 460);
        canvas.add(bet50);
        
        Button bet100 = new Button("Bet $100");
        bet100.setCenter(450, 460);
        canvas.add(bet100);
        
        canvas.animate(() -> {
            Interactable found = race.runFrame();
            if (found != null) {
                canvas.remove(found.getGraphic());
            }
        });
    }

    public static void main(String[] args) {
        new MainGame();
    }
}
