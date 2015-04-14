import java.util.List;

/**
 * @author Piotr Górak, Maciej Knichał dnia 2015-03-23.
 */
public class Pojazd extends Thread {
    private int pojemnoscBaku;
    private float iloscPaliwa;
    private String truckName;
    private boolean jade = false;
    private float spalanie;
    private Vertex mojaPozycja = new Vertex("moja pozycja", getPozycjaX(), getPozycjaY());
    private boolean czyJechacField = true;
    private Ogloszenie mojeZlecenie;
    private Baza baza;
    private Listy listy;
    private int pozycjaX;
    private int pozycjaY;


    public Pojazd(Listy listy, String truckName, Baza baza) {
        this.baza = baza;
        this.listy = listy;
        setTruckName(truckName);
        setPojemnoscBaku(new Randomizer().losujZZakresu(50, 30));
        setSpalanie(new Randomizer().losujZZakresu(45, 25));
        setIloscPaliwa(getPojemnoscBaku());
        this.pozycjaX = this.baza.pozycjaX();
        this.pozycjaY = this.baza.pozycjaY();
        System.out.println(this);

    }

    public Ogloszenie getMojeZlecenie() {
        return mojeZlecenie;
    }

    public void setMojeZlecenie(Ogloszenie mojeZlecenie) {
        this.mojeZlecenie = mojeZlecenie;
    }


    public Listy getListy() {
        return this.listy;
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
        double dystans;
        this.jade = true;
        Vertex zrodlo;
        Vertex cel;
        Nawigacja gps;
        List<Vertex> mojaTrasa;

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
        dystans = gps.minDystans(zrodlo);
        System.out.println("W trasie spale " + ileSpale(dystans));
        setCzyJechacField(czyDojade(dystans));

        if (sprawdzCzyWyjsc()) return;
        czekajCzas(100);
        funkcjaJazdy(mojaTrasa);


        setMojaPozycja(gps.znajdzVertexPoNazwie(getMojeZlecenie().getZrodlo().getNazwa()));
        gps = new Nawigacja(getMojaPozycja(), listy);
        cel = gps.znajdzVertexPoNazwie(getMojeZlecenie().getCel().getNazwa());
        mojaTrasa = gps.wyliczDroge(gps.znajdzVertexPoNazwie(getMojeZlecenie().getZrodlo().getNazwa()), cel);
        dystans = gps.minDystans(cel);
        System.out.println("W trasie spale " + ileSpale(dystans));
        setCzyJechacField(czyDojade(dystans));

        if (sprawdzCzyWyjsc()) return;

        czekajCzas(100);
        funkcjaJazdy(mojaTrasa);

        this.jade = true;
        System.out.println("Zlecenie wykonane");
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
        spalajPaliwo();
        System.out.println(this.getTruckName() + " ustawiłem pozycje na (" + this.pozycjaX + "," + getPozycjaY() + ")");
    }

    public int getPozycjaY() {
        return pozycjaY;
    }

    public void setPozycjaY(int pozycjaY) {
        this.pozycjaY = pozycjaY;
        spalajPaliwo();
        System.out.println(this.getTruckName() + " ustawiłem pozycje na (" + getPozycjaX() + "," + this.pozycjaY + ")");
    }

    private void zmieniajPozycje(Vertex vertex) {
        boolean czySkonczyc = false;
        do {
            int pozycjaSamochoduX = getPozycjaX();
            int pozycjaSamochoduY = getPozycjaY();
            if (vertex.x == pozycjaSamochoduX) {
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
        System.out.println("Mam w baku " + Math.round(getIloscPaliwa()*100)/100);
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
        if (getIloscPaliwa() < ileSpale(dystans) || czyPusty()) {
            System.out.println("Za mało paliwa");
            return false;
        } else return true;
    }

    private void spalajPaliwo() {
        double paliwo = getIloscPaliwa();
        System.out.println(spalanie / 100);
        paliwo -= (spalanie / 100);
        setIloscPaliwa((float) paliwo);

    }
}