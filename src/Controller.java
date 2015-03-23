/**
 * @author Piotr Górak, Maciej Knichał dnia 2015-03-23.
 */
public class Controller {
    public static void main(String[] args) {
        Listy listy = new Listy();
        Pojazd mercedes = new Pojazd("Mercedes");
        mercedes.start();
        listy.getListaPojazdow().add(listy.getListaPojazdow().size(),mercedes);
        new Fabryka(listy).start();


    }
}
