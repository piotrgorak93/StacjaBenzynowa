/**
 * @author Piotr Górak, Maciej Knichał dnia 2015-03-23.
 */
public class Fabryka extends Thread {
    Listy listy;

    public Fabryka(Listy listy) {
        this.listy = listy;

    }

    public void wezwijPojazd() {
        System.out.println("Potrzebuje transportu");
        listy.getListaOgloszen().add(listy.getListaOgloszen().size(),"");
    }

    @Override
    public void run() {
        try {
            Thread.sleep(new Randomizer().losuj(5000, 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wezwijPojazd();
    }
}
