/**
 * @author Piotr Górak, Maciej Knichał dnia 2015-03-26.
 */
public class Baza extends Budynek {
    private int x;
    private int y;

    public Baza(String nazwa, Listy listy) {
        super(nazwa, listy);
        this.x = super.getX();
        this.y = super.getY();
        System.out.println("Baza pozycja to (" + pozycjaX() + "," + pozycjaY() + ")");
    }

    public int pozycjaX() {
        return this.x;
    }

    public int pozycjaY() {
        return this.y;
    }

}
