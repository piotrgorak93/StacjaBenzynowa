/**
 * @author Piotr Górak, Maciej Knichał dnia 2015-03-23.
 */
public class Pojazd extends Thread {
    private int pojemnoscBaku;
    private float iloscPaliwa;
    private String truckName;

    public Listy getListy() {
        return listy;
    }

    private Listy listy;

    public Pojazd(Listy listy,String truckName) {
        setListy(listy);
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
        while (true) {
            if (getListy().getListaOgloszen().size() != 0) {
                bioreZlecenie();
            }
        }
    }

    public void setListy(Listy listy) {
        this.listy = listy;
    }

    public void bioreZlecenie() {
        System.out.println(this.getTruckName()+": Przyjalem zlecenie " + getListy().getListaOgloszen().get(0));
        getListy().getListaOgloszen().remove(getListy().getListaOgloszen().get(0));

    }
}
