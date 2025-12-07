package horseBetting.gambling;

import java.util.ArrayList;

import edu.macalester.graphics.ui.TextField;
import horseBetting.race.Horse;
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
    private ArrayList<Double> odds = new ArrayList<>();

    public GamblingManager() {
        gambler = new Gambler();
    }

    public void formatOdds(ArrayList<Lane> lanes) {
        double max = 0;
        double min = 0.02;
        for (Lane lane : lanes) {
            double speed = lane.getHorse().getSpeed();
            max = (speed > max) ? speed : max;
            min = (speed < min) ? speed : min;
        }
        double range = (max-min)*1000/0.02;

        odds.clear();
        for (Lane lane : lanes) {
            double speed = lane.getHorse().getSpeed();
            double betterThanWorst = (speed - min)/(max-min);
            odds.add(range/2 - (range*betterThanWorst));
        }
    }

    public void updateGambler(TextField bet, TextField horse, ArrayList<Lane> lanes) {
        try {
            gambler.setBet((Double.parseDouble(bet.getText())));
        } catch (NumberFormatException e) {
            
        }
        try {
            gambler.setHorse(lanes.get(Integer.parseInt(horse.getText()) - 1).getHorse());
        } catch (NumberFormatException e) {

        }
    }

    public void payout(Horse winner, ArrayList<Lane> lanes) {
        if (gambler.getHorse() == winner) {
            int index = 0;
            for (Lane lane : lanes) {
                if (gambler.getHorse() == lane.getHorse()) {
                    break;
                }
                index++;
            }
            if (odds.get(index) > 0) {
                // If I bet 100 on a +600, I get 600 back
                gambler.addMoney(gambler.getBet() * (odds.get(index)/100));
            } else {
                // If I bet 600 on a -600, I get 100 back
                gambler.addMoney(gambler.getBet()/Math.abs(odds.get(index))*100);
            }
        } else {
            gambler.addMoney(-gambler.getBet());
        }
    }

    public Gambler getGambler() {
        return gambler;
    }

    public ArrayList<Double> getOdds() {
        return odds;
    }
}
