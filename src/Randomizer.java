import java.util.Random;

/**
 * @author Piotr Górak, Maciej Knichał dnia 2015-03-23.
 */
public class Randomizer {

    public int losujZZakresu(int max, int min) {
        Random r = new Random();
        return r.nextInt(max - min + 1) + min;
    }
    public int losujX(){
        Random r = new Random();
        return r.nextInt(26);
    }
    public int losujY(){
        Random r = new Random();
        return r.nextInt(29);
    }
}
