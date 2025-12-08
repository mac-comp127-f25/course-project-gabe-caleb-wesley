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
    public static final int CANVAS_WIDTH = 750;
    public static final int CANVAS_HEIGHT = 470;
    private CanvasWindow canvas = new CanvasWindow("Race", CANVAS_WIDTH, CANVAS_HEIGHT);
    private GamblingManager manager = new GamblingManager();
    private RaceManager race = raceSetup(canvas, manager);
    private TextField selectHorse = addSelectHorse(canvas);
    private TextField bet = addBet(canvas);
    private boolean raceInProgress = false;
    private int x = 60;

    public MainGame() {
        ArrayList<Lane> lanes = race.getLanes();

        Button go = new Button("Go!");
        go.setCenter(640, 450);
        canvas.add(go);

        Button reset = new Button("Reset");
        reset.setCenter(710, 450);
        canvas.add(reset);

        go.onClick(() -> {
            raceInProgress = true;
        });

        reset.onClick(() -> {
            if (!raceInProgress) {
                manager.getGambler().setBet(0);
                manager.getGambler().setHorse(null);
                race = raceSetup(canvas, manager);
                selectHorse = addSelectHorse(canvas);
                bet = addBet(canvas);
            }
        });

        canvas.animate(() -> {
            if (!raceInProgress) {
                manager.updateGambler(bet, selectHorse, lanes);
            } else {
                Interactable found = race.runFrame();
                if (found != null) {
                    canvas.remove(found.getGraphic());
                }
                Horse winner = race.getWinner();
                if (winner != null) {
                    System.out.println(manager.getGambler().getBet());
                    System.out.println(manager.getGambler().getHorse());
                    manager.payout(winner, lanes);
                    raceInProgress = false;
                }
            }
        });
    }

    public RaceManager raceSetup(CanvasWindow canvas, GamblingManager manager) {
        RaceManager newRace = new RaceManager();
        ArrayList<Lane> lanes = newRace.getLanes();
        x = 60;

        createRaceGraphics(canvas, lanes);

        for (Lane lane : lanes) {
            canvas.add(lane.getHorse().getGraphic());
            for (Interactable interactable : lane.getInteractables().values()) {
                canvas.add(interactable.getGraphic());
            }
        }

        manager.formatOdds(lanes);

        ArrayList<Double> odds = manager.getOdds();

        GraphicsText oddsDisplay = new GraphicsText("Odds: ");
        oddsDisplay.setPosition(10, 425);
        canvas.add(oddsDisplay);

        for (int i = 0; i < 10; i++) {
            GraphicsText payout = new GraphicsText(String.valueOf(i + 1) + "| " + odds.get(i) + ":1");
            payout.setPosition(x, 425);
            x += payout.getWidth() + 12;
            canvas.add(payout);
        }

        GraphicsText moneyDisplay = new GraphicsText("Current Balance: $" + manager.getGambler().getMoney());
        moneyDisplay.setPosition(10, 455);
        canvas.add(moneyDisplay);

        return newRace;
    }

    public void createRaceGraphics(CanvasWindow canvas, ArrayList<Lane> lanes) {
        Rectangle trackBackground = new Rectangle(0, 0, CANVAS_WIDTH, 400);
        trackBackground.setFillColor(Color.getHSBColor((float) 0.10, (float) 0.70, (float) 0.72));
        canvas.add(trackBackground);

        Rectangle betBackground = new Rectangle(0, 400, CANVAS_WIDTH, CANVAS_HEIGHT);
        betBackground.setFillColor(Color.getHSBColor((float) 0.4, (float) 1, (float) 0.84));
        canvas.add(betBackground);

        Rectangle finishLine = new Rectangle(RaceManager.RACE_WIDTH, 0, 20, 400);
        finishLine.setFillColor(Color.BLACK);
        canvas.add(finishLine);

        for (Lane lane : lanes) {
            canvas.add(lane.getDivider());
        }
    }

    public TextField addSelectHorse(CanvasWindow canvas) {
        GraphicsText selectHorseLabel = new GraphicsText("Select Horse (1-10):");
        selectHorseLabel.setCenter(270, 450);
        canvas.add(selectHorseLabel);

        TextField selectHorse = new TextField();
        selectHorse.setCenter(390, 450);
        canvas.add(selectHorse);

        return selectHorse;
    }

    public TextField addBet(CanvasWindow canvas) {
        GraphicsText betLabel = new GraphicsText("Bet:");
        betLabel.setCenter(470, 450);
        canvas.add(betLabel);

        TextField bet = new TextField();
        bet.setCenter(535, 450);
        canvas.add(bet);

        return bet;
    }


    public static void main(String[] args) {
        new MainGame();
    }
}
