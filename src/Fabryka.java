/**
 * @author Piotr Górak, Maciej Knichał dnia 2015-03-23.
 */
public class Fabryka extends Budynek implements Runnable {
    Listy listy;
    private String nazwa;

    public Fabryka(Listy listy, String nazwa) {
        this.listy = listy;
        setNazwa(nazwa);

       // this.x = new Randomizer().losujPrzyDrodze();
       // this.y = new Randomizer().losujPrzyDrodze();
        System.out.println("Jestem " + this.nazwa + " Moja pozycja to (" + getX() + "," + getY() + ")");
    }

    public synchronized void wystawOgloszenie() {
        listy.dodajDoListy(new Ogloszenie(this.getNazwa(), "Magazyn Adama", getX(), getY(), 20, 50));
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
