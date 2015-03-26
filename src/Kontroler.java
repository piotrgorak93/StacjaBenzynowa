/**
 * @author Piotr Górak, Maciej Knichał dnia 2015-03-23.
 */
public class Kontroler {
    private static void utworzPojazd(Listy listy, String nazwa, Baza baza) {
        Pojazd pojazd = new Pojazd(listy, nazwa, baza);
        pojazd.start();
        listy.getListaPojazdow().add(listy.getListaPojazdow().size(), pojazd);
    }

    public static void main(String[] args) {
        Listy listy = new Listy();
        Baza baza = new Baza(new Randomizer().losujX(), new Randomizer().losujY());
        utworzPojazd(listy, "Mercedes", baza);
        new Fabryka(listy, "Tesco").start();
        new Fabryka(listy, "Castorama").start();
        new Mapa(100,100);

    }

}
