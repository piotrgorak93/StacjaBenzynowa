import java.util.ArrayList;

/**
 * @author Piotr Górak, Maciej Knichał dnia 2015-03-23.
 */
public class Fabryka extends Thread {
    Listy listy;
    private String nazwa;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private int x;
    private int y;

    public Fabryka(Listy listy, String nazwa) {
        this.listy = listy;
        setNazwa(nazwa);
        this.x = new Randomizer().losujZZakresu(27, 0);
        this.y = new Randomizer().losujZZakresu(30, 0);
        System.out.println("Jestem "+this.nazwa+" Moja pozycja to ("+getX()+","+getY()+")");

    }

    public void wystawOgloszenie() {
        ArrayList<Ogloszenie> lista = listy.getListaOgloszen();
        lista.add(lista.size(), new Ogloszenie(this.getNazwa(), "Magazyn Adama", getX(), getY(), 20, 50));
        System.out.println("Potrzebuje transportu " + lista.get(lista.size() - 1));

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
