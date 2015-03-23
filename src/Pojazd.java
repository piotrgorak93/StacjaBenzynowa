/**
 * @author Piotr Górak, Maciej Knichał dnia 2015-03-23.
 */
public class Pojazd {
    private int pojemnoscBaku;
    private float iloscPaliwa = pojemnoscBaku;

    public Pojazd() {
        setPojemnoscBaku(new Randomizer().losuj(100, 50));
        System.out.println("Mam " + getIloscPaliwa() + " l paliwa");

    }

    public int getPojemnoscBaku() {
        return pojemnoscBaku;
    }

    public void setPojemnoscBaku(int pojemnoscBaku) {
        this.pojemnoscBaku = pojemnoscBaku;
    }

    public float getIloscPaliwa() {
        return iloscPaliwa;
    }

    public void setIloscPaliwa(float iloscPaliwa) {
        this.iloscPaliwa = iloscPaliwa;
    }
}
