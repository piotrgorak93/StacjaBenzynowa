import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author Piotr Górak, Maciej Knichał dnia 2015-03-23.
 */
public class Listy {
    private ArrayList<Pojazd> listaPojazdow = new ArrayList<Pojazd>();
    private ArrayList<Stacja> listaStacji = new ArrayList<Stacja>();
    private ArrayList<Fabryka> listaFabryk = new ArrayList<Fabryka>();
    private ArrayList<Magazyn> listaMagazynow = new ArrayList<Magazyn>();
    private ArrayList<Ogloszenie> listaOgloszen = new ArrayList<Ogloszenie>();
    private ArrayList<String> listaZleceniobiorcow = new ArrayList<String>();

    public ArrayList<Magazyn> getListaMagazynow() {
        return this.listaMagazynow;
    }


    public ArrayList<Pojazd> getListaPojazdow() {
        return this.listaPojazdow;
    }


    public ArrayList<Stacja> getListaStacji() {
        return listaStacji;
    }


    public ArrayList<Fabryka> getListaFabryk() {
        return this.listaFabryk;
    }


    public ArrayList<Ogloszenie> getListaOgloszen() {
        return this.listaOgloszen;
    }


    public ArrayList<String> getListaZleceniobiorcow() {
        return listaZleceniobiorcow;
    }

    public void usunPierwszyZListy() {
        listaOgloszen.remove(0);
    }

    public Ogloszenie getPierwszyZListy() {
        return getListaOgloszen().get(0);
    }

    public void dodajDoListy(Ogloszenie ogloszenie) {

        getListaOgloszen().add(getListaOgloszen().size(), ogloszenie);
    }

    public synchronized void wezZlecenie(String truckName) {
        usunPierwszyZListy();
        System.out.println(truckName + ": Przyjalem zlecenie " + getPierwszyZListy());
    }

    public boolean czyNiePusta() {
        return !getListaOgloszen().isEmpty();
    }

    public synchronized void akcja(Pojazd pojazd) {
        try {
            pojazd.jestemZajety();
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
