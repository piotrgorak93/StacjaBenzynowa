/**
 * @author Piotr Górak, Maciej Knichał dnia 2015-03-23.
 */
public class Fabryka extends Budynek implements Runnable {
    private final int x;
    private final int y;
    private final Listy listy;
    private String nazwa;

    public Fabryka(String nazwa, Listy listy) {
        super(nazwa, listy);
        this.nazwa = super.getNazwa();
        this.listy = super.getListy();
        this.x = super.x;
        this.y = super.y;
        setNazwa(nazwa);
        listy.getListaFabryk().add(listy.getListaFabryk().size(), this);
    }

    private synchronized void wystawOgloszenie() {
        listy.dodajDoListy(new Ogloszenie(this, listy.losujMagazyn()));
        System.out.println("Potrzebuje transportu " + listy.getListaOgloszen().get(listy.getListaOgloszen().size() - 1));

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(new Randomizer().losujZZakresu(2000, 1000));
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
