/**
 * @author Piotr Górak, Maciej Knichał dnia 2015-03-23.
 */
public class Controller {

    public static void main(String[] args) {
        Ogloszenia listaZlecen = new Ogloszenia();
        new Pojazd(listaZlecen);
        new Pojazd(listaZlecen);
        new Pojazd(listaZlecen);
        new Pojazd(listaZlecen);
        new Fabryka(listaZlecen).start();


    }
}
