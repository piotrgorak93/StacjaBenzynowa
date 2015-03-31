import java.util.concurrent.TimeUnit;

/**
 * @author Piotr Górak, Maciej Knichał dnia 2015-03-23.
 */
public class Pojazd extends Thread {
    private int pojemnoscBaku;
    private float iloscPaliwa;
    private String truckName;
    private boolean jade = false;
    private int spalanie;
    private Ogloszenie mojeZlecenie;
    private Baza baza;
    private Listy listy;

    public Listy getListy() {
        return this.listy;
    }


    public Pojazd(Listy listy, String truckName, Baza baza) {
        this.baza = baza;
        this.listy = listy;
        setTruckName(truckName);
        setPojemnoscBaku(new Randomizer().losujZZakresu(100, 50));
        setSpalanie(new Randomizer().losujZZakresu(45, 25));
        setIloscPaliwa(getPojemnoscBaku());
        System.out.println("Jestem " + getTruckName() + " i mam " + getIloscPaliwa() + " l paliwa, moje spalanie to " + getSpalanie());
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
            while (czyWolny()) {
                getListy().akcja(this);

            }
        }
    }

    public void setListy(Listy listy) {
        this.listy = listy;
    }

    public void bioreZlecenie() {
        Vertex zrodlo;
        Vertex cel;

    }

    public boolean czyWolny() {
        return !this.jade;

    }

    public void jestemZajety() {
        if (getListy().czyNiePusta()) {
            System.out.println(truckName + " znalazł ogłoszenie: " + getListy().getPierwszyZListy());
            getListy().usunPierwszyZListy();
            jedz();
            jestemWolny();
        }
    }

    public void jestemWolny() {

        System.out.println(this.getTruckName() + " dojechal");
        this.jade = false;
    }

    public float getSpalanie() {
        return spalanie;
    }

    public void setSpalanie(int spalanie) {
        this.spalanie = spalanie;
    }

    public void wrocDoBazy() {

    }

    public void jedz() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.getTruckName() + " jedzie");
        this.jade = true;
    }
}
