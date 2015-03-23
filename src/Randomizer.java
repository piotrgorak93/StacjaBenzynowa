import java.util.Random;

/**
 * @author Piotr Górak, Maciej Knichał dnia 2015-03-23.
 */
public class Randomizer {

    public int losuj(int max, int min) {
        Random r = new Random();
        return r.nextInt(max - min + 1) + min;
    }
}
