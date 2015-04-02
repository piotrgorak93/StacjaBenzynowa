/**
 * @author Piotr Górak, Maciej Knichał dnia 2015-03-23.
 */
public class Magazyn extends Budynek implements Runnable {
    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    private int x;
    private int y;
    private String nazwa;
    private Listy listy;

    public Magazyn(Listy listy, String nazwa) {
        this.nazwa = nazwa;
        this.listy = listy;
        this.x = super.getX();
        this.y = super.getY();
        System.out.println(this.nazwa + " pozycja to (" + getX() + "," + getY() + ")");
        listy.getListaMagazynow().add(listy.getListaMagazynow().size(), this);

    }

    public String getNazwa() {
        return this.nazwa;
    }

    @Override
    public void run() {

    }
}
