import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author Piotr Górak, Maciej Knichał dnia 2015-03-23.
 */
public class Listy {
    private final ArrayList<Pojazd> listaPojazdow = new ArrayList<Pojazd>();
    private final ArrayList<Stacja> listaStacji = new ArrayList<Stacja>();
    private final ArrayList<Fabryka> listaFabryk = new ArrayList<Fabryka>();
    private final ArrayList<Magazyn> listaMagazynow = new ArrayList<Magazyn>();
    private final ArrayList<Ogloszenie> listaOgloszen = new ArrayList<Ogloszenie>();
    private final ArrayList<Budynek> listaBudynkow = new ArrayList<Budynek>();
    private ArrayList<Vertex> listaCustomVertex = new ArrayList<>();
    private final HashMap<Stacja, ArrayList<Pojazd>> hashMap = new HashMap<>();

    public void dodajDoHashmapy() {
        for (Stacja stacja : listaStacji) {
            hashMap.put(stacja, stacja.listaPojazdowNaStacji);
        }
    }

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

    private synchronized void usunPierwszyZListy() {
        listaOgloszen.remove(0);
    }

    private Ogloszenie getPierwszyZListy() {
        return getListaOgloszen().get(0);
    }

    public void dodajDoListy(Ogloszenie ogloszenie) {

        getListaOgloszen().add(getListaOgloszen().size(), ogloszenie);
    }

    public synchronized void wezZlecenie(Pojazd pojazd) {
        if (czyNiePusta()) {
            Ogloszenie zlecenie = getPierwszyZListy();
            System.out.println(pojazd.getTruckName() + " znalazł ogłoszenie: " + zlecenie);
            pojazd.setMojeZlecenie(zlecenie);
            usunPierwszyZListy();
            System.out.println(pojazd.getTruckName() + ": Przyjalem zlecenie " + zlecenie);
        }
    }

    private boolean czyNiePusta() {
        return !getListaOgloszen().isEmpty();
    }


    public Magazyn losujMagazyn() {
        int liczbaMagazynow = getListaMagazynow().size();
        if (liczbaMagazynow == 1)
            return getListaMagazynow().get(0);
        else
            return getListaMagazynow().get(new Randomizer().losujZZakresu(liczbaMagazynow - 1, 0));

    }

    public ArrayList<Budynek> getListaBudynkow() {
        return listaBudynkow;
    }

    public ArrayList<Vertex> getListaCustomVertex() {
        return listaCustomVertex;
    }

    public void setListaCustomVertex(ArrayList<Vertex> listaCustomVertex) {
        this.listaCustomVertex = listaCustomVertex;
    }
}
