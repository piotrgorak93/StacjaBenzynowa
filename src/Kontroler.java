import java.util.ArrayList;
import java.util.Arrays;

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
            if (!budynek.getClass().getCanonicalName().equalsIgnoreCase("Stacja"))
                new Thread((Runnable) budynek).start();

        listy.getListaCustomVertex().add(listy.getListaCustomVertex().size(), new Vertex(budynek.getNazwa(), budynek.getX(), budynek.getY()));
    }

    public static void zerujVertexy(Listy listy) {
        for (Vertex vertex : listy.getListaCustomVertex()) {
            vertex.previous = null;
            vertex.adjacencies = null;
            vertex.minDistance = Double.POSITIVE_INFINITY;
        }

    }

    public static void main(String[] args) {
        Listy listy = new Listy();
        listy.setListaCustomVertex(new ArrayList<>(Arrays.asList(new Vertex("A", 0, 0),
                new Vertex("B", 0, 21), new Vertex("C", 11, 21),
                new Vertex("D", 0, 30), new Vertex("E", 21, 16), new Vertex("F", 21, 30),
                new Vertex("G", 16, 7), new Vertex("H", 11, 7), new Vertex("I", 16, 0),
                new Vertex("J", 21, 7), new Vertex("K", 27, 16), new Vertex("L", 27, 0)

        )));
        Baza baza = new Baza("Baza firmy", listy);
        listy.getListaCustomVertex().add(listy.getListaCustomVertex().size(), new Vertex(baza.getNazwa(), baza.getX(), baza.getY()));

//        utworzPojazd(listy, "MAN", baza);
        utworzBudynek(new Magazyn("ASD", listy), listy);
        utworzBudynek(new Fabryka("Tesco", listy), listy);
        utworzBudynek(new Stacja("Orlen", listy), listy);
        utworzBudynek(new Stacja("Shell", listy), listy);
        utworzBudynek(new Stacja("Lotos", listy), listy);
        listy.dodajDoHashmapy();
        utworzPojazd(listy, "Mercedes", baza);
//        new Thread(new Fabryka(listy, "Tesco")).start();
//        new Thread(new Fabryka(listy, "Castorama")).start();
        //  new Thread(new Magazyn("Magazyn Andrzeja",listy)).start();
//        new Thread(new Stacja(listy, "Orlen")).start();

    }
}
