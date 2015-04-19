/**
 * @author Piotr Górak, Maciej Knichał dnia 2015-03-26.
 */
class Baza extends Budynek {
    private final int x;
    private final int y;

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
