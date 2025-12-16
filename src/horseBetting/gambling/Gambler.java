package horseBetting.gambling;

import horseBetting.race.Horse;

/**
 * Authors: Caleb Hatlevig, Gabe Guerrero, Wesley Stone
 * 
 * Tracks money, which horse its betting on.
 * There is a way to update the money and horse.
 */
public class Gambler {
    private double money;
    private Horse horse;
    private double bet;

    /**
     * Creates the Gambler, who starts with $1000, no horse selected and no bet amount.
     */
    public Gambler() {
        money = 1000.0;
        horse = null;
        bet = 0;
    }

    /**
     * Updates the Gambler's balance after a race.
     * @param winnings The net winnings after a race, which is negative if the Gambler lost.
     */
    public void addMoney(double winnings) {
        money += winnings;
    }

    public void setHorse(Horse horse) {
        this.horse = horse;
    }

    public void setBet(double bet) {
        this.bet = bet;
    }

    public Horse getHorse() {
        return horse;
    }

    public double getMoney() {
        return money;
    }

    public double getBet() {
        return bet;
    }
}
