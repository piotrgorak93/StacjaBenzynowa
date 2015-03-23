import java.util.ArrayList;

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
        return listaMagazynow;
    }


    public ArrayList<Pojazd> getListaPojazdow() {
        return listaPojazdow;
    }


    public ArrayList<Stacja> getListaStacji() {
        return listaStacji;
    }


    public ArrayList<Fabryka> getListaFabryk() {
        return listaFabryk;
    }


    public synchronized ArrayList<Ogloszenie> getListaOgloszen() {
        return listaOgloszen;
    }


    public ArrayList<String> getListaZleceniobiorcow() {
        return listaZleceniobiorcow;
    }



}
