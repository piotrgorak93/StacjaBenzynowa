/**
 * @author Piotr Górak, Maciej Knichał dnia 2015-03-23.
 */
public class Pojazd {
    private int pojemnoscBaku;

    public Pojazd(Ogloszenia listaZlecen) {
        setPojemnoscBaku(new Randomizer().losuj(100,50));
        System.out.println("Mam "+getPojemnoscBaku()+ " l paliwa");

    }

    public int getPojemnoscBaku() {
        return pojemnoscBaku;
    }

    public void setPojemnoscBaku(int pojemnoscBaku) {
        this.pojemnoscBaku = pojemnoscBaku;
    }
}
