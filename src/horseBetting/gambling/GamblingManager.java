package horseBetting.gambling;

import java.util.ArrayList;

import edu.macalester.graphics.ui.TextField;
import horseBetting.race.Lane;

/**
 * There is a Gambler
 * There is a way to change the values of the Gambler.
 * There is a way to track which horses won and if the gambler gets a payout.
 * generateOdds(List<Horse> horses) generates the odds for the horses given the speeds.
 * There is a way to know how much to pay out
 */
public class GamblingManager {
    private Gambler gambler;

    public GamblingManager() {
        gambler = new Gambler();
    }

    public void updateGambler(TextField bet, TextField horse, ArrayList<Lane> lanes) {
        try {
            gambler.setBet((Double.parseDouble(bet.getText())));
        } catch (NumberFormatException e) {
            
        }
        try {
            gambler.setHorse(lanes.get(Integer.parseInt(horse.getText())).getHorse());
        } catch (NumberFormatException e) {

        }
    }
}
