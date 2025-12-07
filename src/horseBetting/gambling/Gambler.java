package horseBetting.gambling;

import horseBetting.race.Horse;

/**
 * Tracks money, which horse its betting on.
 * There is a way to update the money and horse.
 */
public class Gambler {
    private double money;
    private Horse horse;
    private double bet;

    public Gambler() {
        money = 1000.0;
        horse = null;
        bet = 0;
    }

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
