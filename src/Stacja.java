/**
 * @author Piotr Górak, Maciej Knichał dnia 2015-03-23.
 */
public class Stacja extends Budynek implements Runnable {


    private int x;
    private int y;
    private String nazwa;
    private Listy listy;

    public Stacja(Listy listy, String nazwa) {
        this.nazwa = nazwa;
        this.listy = listy;
        this.x = super.x;
        this.y = super.y;
        System.out.println("Stacja paliw "+this.nazwa+" pozycja to (" + getX() + "," + getY() + ")");

    }

    @Override
    public void run() {

    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
}
