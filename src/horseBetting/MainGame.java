package horseBetting;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Rectangle;
import java.awt.Color;
import java.util.ArrayList;

import edu.macalester.graphics.ui.Button;
import edu.macalester.graphics.ui.TextField;
import horseBetting.gambling.GamblingManager;
import horseBetting.race.*;

public class MainGame {
    public static final int CANVAS_WIDTH = 700;
    public static final int CANVAS_HEIGHT = 470;
    private boolean flag = true;
    private int x = 60;

    public MainGame() {

        RaceManager race = new RaceManager();
        CanvasWindow canvas = new CanvasWindow("Race", CANVAS_WIDTH, CANVAS_HEIGHT);
        ArrayList<Lane> lanes = race.getLanes();
        GamblingManager manager = new GamblingManager();

        createRaceGraphics(canvas, lanes);

        manager.formatOdds(lanes);

        ArrayList<Double> odds = manager.getOdds();

        for (int i = 0; i < 10; i++) {
            GraphicsText payout = new GraphicsText(String.valueOf(i + 1) + "| " + odds.get(i) + ":1");
            payout.setPosition(x, 425);
            x += payout.getWidth() + 12;
            canvas.add(payout);
        }

        GraphicsText moneyDisplay = new GraphicsText("Current Balance: $" + manager.getGambler().getMoney()); // add actual money
        moneyDisplay.setPosition(10, 455);
        canvas.add(moneyDisplay);

        GraphicsText selectHorseLabel = new GraphicsText("Select horse (1-10):");
        selectHorseLabel.setCenter(270, 450);
        canvas.add(selectHorseLabel);

        TextField selectHorse = new TextField();
        selectHorse.setCenter(390, 450);
        canvas.add(selectHorse);

        GraphicsText betLabel = new GraphicsText("Bet:");
        betLabel.setCenter(470, 450);
        canvas.add(betLabel);

        GraphicsText oddsDisplay = new GraphicsText("Odds: ");
        oddsDisplay.setPosition(10, 425);
        canvas.add(oddsDisplay);

        TextField bet = new TextField();
        bet.setCenter(535, 450);
        canvas.add(bet);

        Button go = new Button("Go!");
        go.setCenter(650, 450);
        canvas.add(go);

        go.onClick(() -> {
            flag = false;
        });
        
        canvas.animate(() -> {
            if (flag) {
                manager.updateGambler(bet, selectHorse, lanes);
            } else {
                Interactable found = race.runFrame();
                if (found != null) {
                    canvas.remove(found.getGraphic());
                }
            }
        });
    }

    public void createRaceGraphics(CanvasWindow canvas, ArrayList<Lane> lanes) {
        Rectangle trackBackground = new Rectangle(0, 0, CANVAS_WIDTH, 400);
        trackBackground.setFillColor(Color.getHSBColor((float) 0.10, (float) 0.70, (float) 0.72));
        canvas.add(trackBackground);

        Rectangle betBackground = new Rectangle(0, 400, CANVAS_WIDTH, CANVAS_HEIGHT);
        betBackground.setFillColor(Color.getHSBColor((float) 0.4, (float) 1, (float) 0.84));
        canvas.add(betBackground);

        Rectangle finishLine = new Rectangle(680, 0, 700, 400);
        finishLine.setFillColor(Color.BLACK);
        canvas.add(finishLine);

        for (Lane lane : lanes) {
            canvas.add(lane.getHorse().getGraphic());
            canvas.add(lane.getDivider());
            for (Interactable interactable : lane.getInteractables().values()) {
                canvas.add(interactable.getGraphic());
            }
        }
    }


    public static void main(String[] args) {
        new MainGame();
    }
}
