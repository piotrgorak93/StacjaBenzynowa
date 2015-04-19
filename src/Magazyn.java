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

    private final int x;
    private final int y;
    private final String nazwa;
    private final Listy listy;

    public Magazyn(String nazwa, Listy listy) {
        super(nazwa, listy);
        this.nazwa = nazwa;
        this.listy = listy;
        this.x = super.getX();
        this.y = super.getY();
        setNazwa(nazwa);
        listy.getListaMagazynow().add(listy.getListaMagazynow().size(), this);

    }

    public String getNazwa() {
        return this.nazwa;
    }

    @Override
    public void run() {

    }
}
