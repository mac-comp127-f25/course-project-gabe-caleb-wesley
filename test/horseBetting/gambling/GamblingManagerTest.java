package horseBetting.gambling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.macalester.graphics.ui.TextField;
import horseBetting.race.Horse;
import horseBetting.race.RaceManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;

public class GamblingManagerTest {
    GamblingManager gamblingManager;
    RaceManager race = new RaceManager();
    TextField bet = new TextField();
    TextField horse = new TextField();

    @BeforeEach
    void setUp() {
        gamblingManager = new GamblingManager();
    }
    
    @Test
    void updatesGamblerWithTextFields() {
        bet.setText("100");
        horse.setText("5");
        gamblingManager.updateGambler(bet, horse, race.getLanes());

        assertEquals(100.0, gamblingManager.getGambler().getBet());
        assertEquals(race.getLanes().get(4).getHorse(), gamblingManager.getGambler().getHorse());
    }

    @Test
    void oddsAreEquallySpacedAroundZero() {
        gamblingManager.formatOdds(race.getLanes());
        Double max = Collections.max(gamblingManager.getOdds());
        Double min = Collections.min(gamblingManager.getOdds());
        assertEquals(0.0, max + min, 0.1);
    }

    @Test
    void oddsAreOnTheOrderOfHundreds() {
        gamblingManager.formatOdds(race.getLanes());
        Double max = Collections.max(gamblingManager.getOdds());
        Double min = Collections.min(gamblingManager.getOdds());
        for (Double odds : gamblingManager.getOdds()) {
            assertTrue(Math.abs(odds/1000) < 1);
        }
        assertTrue(max/100 > 1);
        assertTrue(min/100 < -1);
    }

    @Test
    void favoritePayoutIsOneHundredGivenOdds() {
        gamblingManager.formatOdds(race.getLanes());
        Double min = Collections.min(gamblingManager.getOdds());
        int indexOfFavorite = gamblingManager.getOdds().indexOf(min);
        Horse favorite = race.getLanes().get(indexOfFavorite).getHorse();

        bet.setText(String.valueOf(Math.abs(min)));
        horse.setText(String.valueOf(indexOfFavorite + 1));
        gamblingManager.updateGambler(bet, horse, race.getLanes());

        gamblingManager.payout(favorite, race.getLanes());

        assertEquals(1100, gamblingManager.getGambler().getMoney(), 0.1);
    }

    @Test
    void underdogPayoutIsOneHundredGivenOdds() {
        gamblingManager.formatOdds(race.getLanes());
        Double max = Collections.max(gamblingManager.getOdds());
        int indexOfUnderdog = gamblingManager.getOdds().indexOf(max);
        Horse underdog = race.getLanes().get(indexOfUnderdog).getHorse();

        bet.setText(String.valueOf(100/max*100));
        horse.setText(String.valueOf(indexOfUnderdog + 1));
        gamblingManager.updateGambler(bet, horse, race.getLanes());

        gamblingManager.payout(underdog, race.getLanes());

        assertEquals(1100, gamblingManager.getGambler().getMoney(), 0.1);
    }

    @Test
    void lossSubtractsBet() {
        gamblingManager.formatOdds(race.getLanes());
        bet.setText("100");
        horse.setText("5");
        gamblingManager.updateGambler(bet, horse, race.getLanes());

        gamblingManager.payout(race.getLanes().get(6).getHorse(), race.getLanes());

        assertEquals(900, gamblingManager.getGambler().getMoney());
    }
}
