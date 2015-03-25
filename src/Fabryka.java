import java.util.ArrayList;

/**
 * @author Piotr Górak, Maciej Knichał dnia 2015-03-23.
 */
public class Fabryka extends Thread {
    Listy listy;
    private String nazwa;

    public Fabryka(Listy listy, String nazwa) {
        this.listy = listy;
        setNazwa(nazwa);
    }

    public void wystawOgloszenie() {
        ArrayList<Ogloszenie> lista = listy.getListaOgloszen();
       lista.add(lista.size(), new Ogloszenie(this.getNazwa(), "Magazyn Adama"));
        System.out.println("Potrzebuje transportu " + lista.get(lista.size() - 1));

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

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
}
