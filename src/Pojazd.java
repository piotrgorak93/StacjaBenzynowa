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

    public Listy getListy() {
        return listy;
    }

    private Listy listy;

    public Pojazd(Listy listy, String truckName) {
        setListy(listy);
        setTruckName(truckName);
        setPojemnoscBaku(new Randomizer().losuj(100, 50));
        setSpalanie(new Randomizer().losuj(45, 25));
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
                if (getListy().getListaOgloszen().size() != 0) {
                    bioreZlecenie();
                    jestemZajety();
                }
            }
            try {
                Thread.sleep(3000);
                System.out.println("Dojechałem "+this.mojeZlecenie );
                jestemWolny();


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setListy(Listy listy) {
        this.listy = listy;
    }

    public void bioreZlecenie() {
        this.mojeZlecenie=getListy().getListaOgloszen().get(0);
        System.out.println(this.getTruckName() + ": Przyjalem zlecenie " + this.mojeZlecenie );


        getListy().getListaOgloszen().remove(getListy().getListaOgloszen().get(0));

    }

    public boolean czyWolny() {
        return !this.jade;

    }

    public void jestemZajety() {
        this.jade = true;
    }

    public void jestemWolny() {
        this.jade = false;
    }

    public float getSpalanie() {
        return spalanie;
    }

    public void setSpalanie(int spalanie) {
        this.spalanie = spalanie;
    }
}
