import java.util.ArrayList;

/**
 * @author Piotr Górak, Maciej Knichał dnia 2015-03-23.
 */
public class Listy {

    private ArrayList<Pojazd> listaPojazdow = new ArrayList<Pojazd>();
    private ArrayList<Stacja> listaStacji = new ArrayList<Stacja>();
    private ArrayList<Fabryka> listaFabryk = new ArrayList<Fabryka>();
    private ArrayList<Magazyn> listaMagazynow = new ArrayList<Magazyn>();

    public void setListaOgloszen(ArrayList<String> listaOgloszen) {
        this.listaOgloszen = listaOgloszen;
    }

    private ArrayList<String> listaOgloszen = new ArrayList<String>();
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


    public ArrayList<String> getListaOgloszen() {
        return listaOgloszen;
    }


    public ArrayList<String> getListaZleceniobiorcow() {
        return listaZleceniobiorcow;
    }


}
