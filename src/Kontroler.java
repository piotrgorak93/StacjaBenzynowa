/**
 * @author Piotr Górak, Maciej Knichał dnia 2015-03-23.
 */
public class Kontroler {
    private static void utworzPojazd(Listy listy, String nazwa, Baza baza) {
        Pojazd pojazd = new Pojazd(listy, nazwa, baza);
        pojazd.start();
        listy.getListaPojazdow().add(listy.getListaPojazdow().size(), pojazd);
    }

    private static void utworzBudynek(Budynek budynek, Listy listy) {
        if (!budynek.getClass().getCanonicalName().equalsIgnoreCase("Magazyn"))
            new Thread((Runnable) budynek).start();


    }


    public static void main(String[] args) {
        Listy listy = new Listy();
        Baza baza = new Baza("Baza firmy", listy);
        utworzPojazd(listy, "Mercedes", baza);
//        utworzPojazd(listy, "MAN", baza);
        utworzBudynek(new Magazyn("ASD", listy), listy);
        utworzBudynek(new Fabryka("Tesco", listy), listy);
//        new Thread(new Fabryka(listy, "Tesco")).start();
//        new Thread(new Fabryka(listy, "Castorama")).start();
        //  new Thread(new Magazyn("Magazyn Andrzeja",listy)).start();
//        new Thread(new Stacja(listy, "Orlen")).start();

    }
}
