/**
 * @author Piotr Górak, Maciej Knichał dnia 2015-03-23.
 */
public class Fabryka extends Thread {
    Listy listy;

    public Fabryka(Listy listy) {
        this.listy = listy;

    }

    public void wystawOgloszenie() {
        listy.getListaOgloszen().add(listy.getListaOgloszen().size(), new Ogloszenie("Tesco", "Magazyn Adama"));
        System.out.println("Potrzebuje transportu " + listy.getListaOgloszen().get(listy.getListaOgloszen().size() - 1));

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(new Randomizer().losuj(5000, 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            wystawOgloszenie();
        }
    }
}
