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

/**
 * Authors: Caleb Hatlevig, Gabe Guerrero, Wesley Stone
 * 
 * A game of horse racing.
 */
public class MainGame {
    public static final int CANVAS_WIDTH = 800;
    public static final int CANVAS_HEIGHT = 470;
    private CanvasWindow canvas = new CanvasWindow("Race", CANVAS_WIDTH, CANVAS_HEIGHT);
    private GamblingManager manager = new GamblingManager();
    private RaceManager race = raceSetup(canvas, manager);
    private ArrayList<Lane> lanes = race.getLanes();
    private TextField selectHorse = addSelectHorse(canvas);
    private TextField bet = addBet(canvas);
    private boolean raceInProgress = false;
    public static boolean hasMoney = true;
    private int x = 60;

    /**
     * The main method where the game is actually run.
     * Generates all the graphics for the game, 
     * and creates logic for the buttons and text fields the user can interact with: 
     * selecting a horse, placing a bet, starting the race, and resetting after it's over.
     * Animates the canvas, moving the horses and removing interactables as needed.
     * The user loses if they run out of money.
     */
    public MainGame() {
        Button go = new Button("Go!");
        go.setCenter(670, 450);
        canvas.add(go);

        Button reset = new Button("Reset");
        reset.setCenter(750, 450);
        canvas.add(reset);

        go.onClick(() -> {
            raceInProgress = true;
        });

        reset.onClick(() -> {
            if (!raceInProgress) {
                manager.getGambler().setBet(0);
                manager.getGambler().setHorse(null);
                canvas.removeAll();
                race = raceSetup(canvas, manager);
                lanes = race.getLanes();
                selectHorse = addSelectHorse(canvas);
                bet = addBet(canvas);
                canvas.add(go);
                canvas.add(reset);
            }
        });

        canvas.animate(() -> {
            if (hasMoney) {
                if (!raceInProgress) {
                    manager.updateGambler(bet, selectHorse, lanes);
                } else {
                    ArrayList<Interactable> found = race.runFrame();
                    for (Interactable hit : found) {
                        canvas.remove(hit.getGraphic());
                    }
                    Horse winner = race.getWinner();
                    if (winner != null) {
                        manager.payout(winner, lanes);
                        raceInProgress = false;
                    }
                }
            } else {
                GraphicsText brokeMessage = new GraphicsText("You are out of money! Please restart the program to play again.");
                brokeMessage.setPosition(150, 200);
                canvas.removeAll();
                canvas.add(brokeMessage);
            }
        });
    }

    /**
     * Sets up the race. First adds the background graphics, then the game objects (horses and interactables).
     * Then sets the odds and displays them, as well as the Gambler's current balance.
     */
    public RaceManager raceSetup(CanvasWindow canvas, GamblingManager manager) {
        RaceManager newRace = new RaceManager();
        ArrayList<Lane> lanes = newRace.getLanes();
        x = 65;

        createRaceGraphics(canvas, lanes);

        for (Lane lane : lanes) {
            canvas.add(lane.getHorse().getGraphic());
            canvas.add(lane.getHorse().getSpeedBackground());
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
            x += payout.getWidth() + 20;
            canvas.add(payout);
        }

        GraphicsText moneyDisplay = new GraphicsText("Current Balance: $" + manager.getGambler().getMoney());
        moneyDisplay.setPosition(10, 455);
        canvas.add(moneyDisplay);

        return newRace;
    }

    /**
     * Generates the basic graphics for the race: the brown racetrack, white dividers, 
     * black finish line, green betting background and numerical labels for the lanes.
     */
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

        int i = 1;
        for (Lane lane : lanes) {
            canvas.add(lane.getDivider());
            GraphicsText number = new GraphicsText(String.valueOf(i));
            number.setCenter(52,40 * i - 20);
            number.setFillColor(Color.WHITE);
            canvas.add(number);
            i++;
        }
    }

    /**
     * Creates the TextField for selecting a horse to bet on.
     */
    public TextField addSelectHorse(CanvasWindow canvas) {
        GraphicsText selectHorseLabel = new GraphicsText("Select Horse (1-10):");
        selectHorseLabel.setCenter(305, 450);
        canvas.add(selectHorseLabel);

        TextField selectHorse = new TextField();
        selectHorse.setCenter(425, 450);
        canvas.add(selectHorse);

        return selectHorse;
    }

    /**
     * Creates the TextField for selecting how much money to bet.
     */
    public TextField addBet(CanvasWindow canvas) {
        GraphicsText betLabel = new GraphicsText("Bet:");
        betLabel.setCenter(500, 450);
        canvas.add(betLabel);

        TextField bet = new TextField();
        bet.setCenter(565, 450);
        canvas.add(bet);

        return bet;
    }

    public static void broke() {
        hasMoney = false;
    }


    public static void main(String[] args) {
        new MainGame();
    }
}
