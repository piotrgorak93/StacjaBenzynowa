/**
 * @author Piotr Górak, Maciej Knichał dnia 2015-03-26.
 */
public class Baza {
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private final int x;
    private final int y;

    public Baza(int x, int y) {
        this.x = x;
        this.y = y;
        System.out.println("Baza wygenerowana w (" + this.x + "," + this.y + ")");
    }

}
