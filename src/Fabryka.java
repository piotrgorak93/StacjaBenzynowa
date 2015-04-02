/**
 * @author Piotr Górak, Maciej Knichał dnia 2015-03-23.
 */
public class Fabryka extends Budynek implements Runnable {
    private int x;
    private int y;
    private Listy listy;
    private String nazwa;

    public Fabryka(Listy listy, String nazwa) {
        this.listy = listy;
        this.x = super.x;
        this.y = super.y;
        setNazwa(nazwa);
        System.out.println("Fabryka " + this.nazwa + " pozycja to (" + getX() + "," + getY() + ")");
    }

    public synchronized void wystawOgloszenie() {
        listy.dodajDoListy(new Ogloszenie(this, listy.losujMagazyn()));
        System.out.println("Potrzebuje transportu " + listy.getListaOgloszen().get(listy.getListaOgloszen().size() - 1));

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(new Randomizer().losujZZakresu(5000, 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            wystawOgloszenie();
        }
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
}
