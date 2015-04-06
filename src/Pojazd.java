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

    public Ogloszenie getMojeZlecenie() {
        return mojeZlecenie;
    }

    public void setMojeZlecenie(Ogloszenie mojeZlecenie) {
        this.mojeZlecenie = mojeZlecenie;
    }

    private Ogloszenie mojeZlecenie;
    private Baza baza;
    private Listy listy;
    private int pozycjaX;
    private int pozycjaY;

    public Listy getListy() {
        return this.listy;
    }

    public Pojazd(Listy listy, String truckName, Baza baza) {
        this.baza = baza;
        this.listy = listy;
        setTruckName(truckName);
        setPojemnoscBaku(new Randomizer().losujZZakresu(1500, 1000));
        setSpalanie(new Randomizer().losujZZakresu(45, 25));
        setIloscPaliwa(getPojemnoscBaku());
        this.pozycjaX = this.baza.pozycjaX();
        this.pozycjaY = this.baza.pozycjaY();
        System.out.println(this);

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
    public String toString() {
        return "Jestem " + getTruckName() + " mam w baku " + getIloscPaliwa() + " l paliwa, moje spalanie to " + getSpalanie() +
                " l/100km, jestem w miejscu (" + getPozycjaX() + "," + getPozycjaY() + ")";
    }

    @Override
    public void run() {
        while (true) {
            while (czyWolny()) {
                jestemZajety();

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

        listy.wezZlecenie(this);
        while (this.getMojeZlecenie() != null) {
            jedz();
            jestemWolny();
        }


    }

    public void jestemWolny() {

        System.out.println(this.getTruckName() + " dojechal do "+getMojeZlecenie().getCel().getNazwa() +
                "("+getMojeZlecenie().getxCel()+","+getMojeZlecenie().getyCel()+")");
        setMojeZlecenie(null);
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
        this.jade = true;
        int xZrodlo = getMojeZlecenie().getxZrodlo();
        int yZrodlo = getMojeZlecenie().getyZrodlo();

        int xCel = getMojeZlecenie().getxCel();
        int yCel = getMojeZlecenie().getyCel();

        System.out.println(this.getTruckName() + " jedzie z "+getMojeZlecenie().getZrodlo().getNazwa() +" ("+xZrodlo+","+yZrodlo+") " +
                "do "+getMojeZlecenie().getCel().getNazwa()+" ("+xCel+","+yCel+"), nr zlecenia "+getMojeZlecenie().getNumerOgloszenia());
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.jade = true;
    }

    public int getPozycjaX() {
        return pozycjaX;
    }

    public void setPozycjaX(int pozycjaX) {
        this.pozycjaX = pozycjaX;
    }

    public int getPozycjaY() {
        return pozycjaY;
    }

    public void setPozycjaY(int pozycjaY) {
        this.pozycjaY = pozycjaY;
    }

    public void wyliczDroge(){

    }


}