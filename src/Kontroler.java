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
        Baza baza = new Baza();
        new Baza();
        new Baza();
        new Baza();
        new Baza();

       // new Budynek();

        //  utworzPojazd(listy, "Mercedes", baza);
        //  utworzPojazd(listy, "MAN", baza);
        //new Thread(new Fabryka(listy, "Tesco")).start();
        //   new Thread(new Fabryka(listy, "Castorama")).start();
        //  new ListyTest().porownaj();
        // new Mapa(100,100);
        //  new Nawigacja();
    }

}
