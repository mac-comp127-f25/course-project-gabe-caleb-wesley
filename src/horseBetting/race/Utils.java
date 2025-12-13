package horseBetting.race;
import java.util.Random;

public class Utils {
    private static Random rand = new Random();

    public static double halfToOneRandomDouble() {
        return (rand.nextDouble()*0.5) + 0.5;
    }

    public static double SixToEightTenths() {
        return (rand.nextDouble()*0.2) + 0.6;
    }

    public static double TenthToThird() {
        return (rand.nextDouble()*0.23333) + 0.1;
    }

    public static double TwelveToFourteenTenths() {
        return (rand.nextDouble()*0.2) + 1.2;
    }
}
