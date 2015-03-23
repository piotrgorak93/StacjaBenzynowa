/**
 * @author Piotr Górak, Maciej Knichał dnia 2015-03-23.
 */
public class Pojazd extends Thread {
    private int pojemnoscBaku;
    private float iloscPaliwa;
    private String truckName;

    public Pojazd(String truckName) {
        setTruckName(truckName);
        setPojemnoscBaku(new Randomizer().losuj(100, 50));
        setIloscPaliwa(getPojemnoscBaku());
        System.out.println("Jestem " + getTruckName() + " i mam " + getIloscPaliwa() + " l paliwa");

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

    public String getTruckName() {
        return truckName;
    }

    public void setTruckName(String name) {
        this.truckName = name;
    }

    @Override
    public void run() {

    }
}
