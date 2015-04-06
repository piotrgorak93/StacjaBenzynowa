import java.util.ArrayList;
import java.util.Arrays;
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
    private ArrayList<Budynek> listaBudynkow = new ArrayList<Budynek>();

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

    public synchronized void usunPierwszyZListy() {
        listaOgloszen.remove(0);
    }

    public Ogloszenie getPierwszyZListy() {
        return getListaOgloszen().get(0);
    }

    public void dodajDoListy(Ogloszenie ogloszenie) {

        getListaOgloszen().add(getListaOgloszen().size(), ogloszenie);
    }

    public synchronized void wezZlecenie(Pojazd pojazd) {
        while (czyNiePusta()) {
            Ogloszenie zlecenie = getPierwszyZListy();
            System.out.println(pojazd.getTruckName() + " znalazł ogłoszenie: " + zlecenie);
            pojazd.setMojeZlecenie(zlecenie);
            usunPierwszyZListy();
            System.out.println(pojazd.getTruckName() + ": Przyjalem zlecenie " + zlecenie);

        }
    }

    public boolean czyNiePusta() {
        return !getListaOgloszen().isEmpty();
    }

    public synchronized void akcja(Pojazd pojazd) {
        pojazd.jestemZajety();
        Ogloszenie zlecenie = pojazd.getMojeZlecenie();
        // System.out.println(pojazd.getMojeZlecenie());
        try {

            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Magazyn losujMagazyn() {
        int liczbaMagazynow = getListaMagazynow().size();
        if (liczbaMagazynow == 1)
            return getListaMagazynow().get(0);
        else
            return getListaMagazynow().get(new Randomizer().losujZZakresu(liczbaMagazynow, 0));

    }

    public ArrayList<Budynek> getListaBudynkow() {
        return listaBudynkow;
    }

    public void setListaBudynkow(ArrayList<Budynek> listaBudynkow) {
        this.listaBudynkow = listaBudynkow;
    }
}
