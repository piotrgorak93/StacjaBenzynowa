/**
 * @author Piotr Górak, Maciej Knichał dnia 2015-03-23.
 */
public class Kontroler {
    private static void utworzPojazd(Listy listy, String nazwa) {
        Pojazd pojazd = new Pojazd(listy, nazwa);
        pojazd.start();
        listy.getListaPojazdow().add(listy.getListaPojazdow().size(), pojazd);
    }

    public static void main(String[] args) {
        Listy listy = new Listy();
        utworzPojazd(listy, "Mercedes");
        new Fabryka(listy, "Tesco").start();
        new Fabryka(listy, "Castorama").start();
        new Mapa(100,100);

    }

}
