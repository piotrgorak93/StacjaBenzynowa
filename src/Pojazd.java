import java.util.List;

/**
 * @author Piotr Górak, Maciej Knichał dnia 2015-03-23.
 */
public class Pojazd extends Thread {
    private int pojemnoscBaku;
    private float iloscPaliwa;
    private String truckName;
    private boolean jade = false;
    private int spalanie;
    private List<Vertex> mojaTrasa;
    private Nawigacja gps;
    private Vertex mojaPozycja = new Vertex("moja pozycja", getPozycjaX(), getPozycjaY());
    private List<Vertex> mojaTrasa2;
    private boolean czyJechacField;

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
        while (czyJechac()) {
            jedz();
            jestemWolny();
        }


    }

    private boolean czyJechac() {
        return isCzyJechacField() && getMojeZlecenie() != null;
    }

    private boolean czyPusty() {
        return getIloscPaliwa() <= 0;
    }

    public void jestemWolny() {

        System.out.println(this.getTruckName() + " dojechal do " + getMojeZlecenie().getCel().getNazwa() +
                "(" + getMojeZlecenie().getxCel() + "," + getMojeZlecenie().getyCel() + ")");
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

    public Vertex getMojaPozycja() {
        return mojaPozycja;
    }

    public void setMojaPozycja(Vertex mojaPozycja) {
        this.mojaPozycja = mojaPozycja;
    }

    public void jedz() {
        this.jade = true;
        Vertex zrodlo;
        Vertex cel;
        int xZrodlo = getMojeZlecenie().getxZrodlo();
        int yZrodlo = getMojeZlecenie().getyZrodlo();
        int xCel = getMojeZlecenie().getxCel();
        int yCel = getMojeZlecenie().getyCel();

        System.out.println("Moje zlecenie: " + mojeZlecenie);
        System.out.println(this.getTruckName() + " jedzie z " + getMojeZlecenie().getZrodlo().getNazwa() + " (" + xZrodlo + "," + yZrodlo + ") " +
                "do " + getMojeZlecenie().getCel().getNazwa() + " (" + xCel + "," + yCel + "), nr zlecenia " + getMojeZlecenie().getNumerOgloszenia());
        gps = new Nawigacja(getMojaPozycja(), listy);
        zrodlo = gps.znajdzVertexPoNazwie(getMojeZlecenie().getZrodlo().getNazwa());
        mojaTrasa = gps.wyliczDroge(getMojaPozycja(), zrodlo);
        if (sprawdzCzyWyjsc()) return;
        czekajCzas(100);
        System.out.println(gps.minDystans(zrodlo));
        funkcjaJazdy(mojaTrasa);
        System.out.println(this.getTruckName() + " jestem w pozycji " + getPozycjaX() + " " + getPozycjaY());

        setMojaPozycja(gps.znajdzVertexPoNazwie(getMojeZlecenie().getZrodlo().getNazwa()));
        Nawigacja gps2 = new Nawigacja(getMojaPozycja(), listy);
        cel = gps2.znajdzVertexPoNazwie(getMojeZlecenie().getCel().getNazwa());
        mojaTrasa2 = gps2.wyliczDroge(gps2.znajdzVertexPoNazwie(getMojeZlecenie().getZrodlo().getNazwa()), cel);
        double dystans = gps2.minDystans(cel);
        setCzyJechacField(czyDojade(dystans));

        if (sprawdzCzyWyjsc()) return;

        czekajCzas(100);
        funkcjaJazdy(mojaTrasa2);


        this.jade = true;
    }

    private boolean sprawdzCzyWyjsc() {
        if (!czyJechac()) {
            System.out.println("Wychodze");
            return true;
        }
        return false;
    }

    private void czekajCzas(int czas) {
        try {
            Thread.sleep(czas);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void funkcjaJazdy(List<Vertex> trasa) {
        System.out.println("Drukuje z pojazdu: " + trasa);
        for (int i = 1; i < trasa.size(); i++) {
            System.out.println("Moja pozycja " + getPozycjaX() + " " + getPozycjaY());
            zmieniajPozycje(trasa.get(i));
        }
    }

    public int getPozycjaX() {
        return pozycjaX;
    }

    public void setPozycjaX(int pozycjaX) {
        this.pozycjaX = pozycjaX;
        System.out.println(this.getTruckName() + " Moj x to " + this.pozycjaX + " moj y to " + getPozycjaY());
    }

    public int getPozycjaY() {
        return pozycjaY;
    }

    public void setPozycjaY(int pozycjaY) {
        this.pozycjaY = pozycjaY;
        System.out.println(this.getTruckName() + " Moj x to " + getPozycjaX() + " Moj y to " + this.pozycjaY);

    }

    private void zmieniajPozycje(Vertex vertex) {
        boolean czySkonczyc = false;
        do {
            int pozycjaSamochoduX = getPozycjaX();
            int pozycjaSamochoduY = getPozycjaY();
            if (vertex.x == pozycjaSamochoduX) {
                czekajCzas(100);
                if (vertex.y == pozycjaSamochoduY)
                    czySkonczyc = true;
                else {
                    if (vertex.y < pozycjaSamochoduY)
                        setPozycjaY(--pozycjaSamochoduY);
                    else
                        setPozycjaY(++pozycjaSamochoduY);
                }
            } else {
                if (vertex.x < pozycjaSamochoduX)
                    setPozycjaX(--pozycjaSamochoduX);
                else
                    setPozycjaX(++pozycjaSamochoduX);
            }
        } while (!czySkonczyc);
        System.out.println(getTruckName() + " dojechałem do punktu " + vertex.name);
    }


    public boolean isCzyJechacField() {
        return czyJechacField;
    }

    public void setCzyJechacField(boolean czyJechacField) {
        this.czyJechacField = czyJechacField;
    }

    private double ileSpale(double dystans) {
        return Math.round((getSpalanie() / 100 * dystans) * 100.00) / 100.00;
    }

    private boolean czyDojade(double dystans) {
        if (getIloscPaliwa() < ileSpale(dystans)) {
            System.out.println("Za mało paliwa");
            return false;
        } else return true;

    }
}