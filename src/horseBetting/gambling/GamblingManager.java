package horseBetting.gambling;

import java.util.ArrayList;

import edu.macalester.graphics.ui.TextField;
import horseBetting.MainGame;
import horseBetting.race.Horse;
import horseBetting.race.Lane;

/**
 * Authors: Caleb Hatlevig, Gabe Guerrero, Wesley Stone
 * 
 * The bookie of our code: generates the odds, takes the Gambler's bet and pays out the Gambler.
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

    /**
     * Generates the odds for each horse as a function of their speeds. 
     * The fastest is mapped to 1.2:1, the slowest to 8.0:1, and everyone else is scaled appropriately.
     * @param lanes The set of lanes for the race, which contains the horses' speeds.
     */
    public void formatOdds(ArrayList<Lane> lanes) {
        double max = 0;
        double min = 0.02;
        for (Lane lane : lanes) {
            double speed = lane.getHorse().getSpeed();
            max = (speed > max) ? speed : max;
            min = (speed < min) ? speed : min;
        }
        double range = max-min;

        odds.clear();
        for (Lane lane : lanes) {
            double speed = lane.getHorse().getSpeed();
            odds.add(Math.round((8.0 - (6.8/range)*(speed-min)) * 10)/10.0);
        }
    }

    /**
     * Records the Gambler's bet on the selected horse. 
     * For the bet, negative bets are recorded as $0,
     * and bets the Gambler can't afford are set to all their money.
     * For the horses, a negative selection is recorded as a bet on 1, 
     * and a double-digit selection is recorded as a bet on 10.
     */
    public void updateGambler(TextField bet, TextField horse, ArrayList<Lane> lanes) {
        try {
            Double betAmount = Double.parseDouble(bet.getText());
            if (betAmount > gambler.getMoney()) {
                bet.setText(gambler.getMoney() + "");
                gambler.setBet(gambler.getMoney());
            } else if (betAmount < 0) {
                bet.setText("0");
                gambler.setBet(0);
            } else {
                gambler.setBet(betAmount);
            }
        } catch (NumberFormatException e) {
            
        }
        try {
            Integer horseNumber = Integer.parseInt(horse.getText()) - 1;
            if (horseNumber < 0) {
                horse.setText("1");
                horseNumber = 0;
            } else if (horseNumber >= lanes.size()) {
                horse.setText(lanes.size() + "");
                horseNumber = lanes.size() - 1;
            }
            gambler.setHorse(lanes.get(horseNumber).getHorse());
        } catch (NumberFormatException e) {

        } catch (IndexOutOfBoundsException e) {

        }
    }

/**
 * Pays out the Gambler after the race is over. The initial bet is subtracted, 
 * and if the Gambler won, they are paid that bet times the odds on the winner. 
 */
    public void payout(Horse winner, ArrayList<Lane> lanes) {
        gambler.addMoney(-gambler.getBet());
        if (gambler.getHorse() == winner) {
            int index = 0;
            for (Lane lane : lanes) {
                if (gambler.getHorse() == lane.getHorse()) {
                    break;
                }
                index++;
            }
            gambler.addMoney(odds.get(index) * gambler.getBet());
        }
        if (gambler.getMoney() <= 0) {
            MainGame.broke();
        }
    }

    public Gambler getGambler() {
        return gambler;
    }

    public ArrayList<Double> getOdds() {
        return odds;
    }
}
