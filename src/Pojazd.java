import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Piotr Górak, Maciej Knichał dnia 2015-03-23.
 */
public class Pojazd extends Thread {
    private int pojemnoscBaku;
    private double iloscPaliwa;
    private String truckName;
    public boolean jade = false;
    private float spalanie;
    private Vertex mojaPozycja;
    private boolean czyJechacField = true;
    private Ogloszenie mojeZlecenie;
    private Baza baza;
    public Listy listy;
    private int pozycjaX;
    private int pozycjaY;
    public boolean czyWyjechalZBazy = false;
    public Vertex poprzednieMiejsce;
    private final ArrayList<Vertex> backupListyVertex;


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
        backupListyVertex = listy.getListaCustomVertex();
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

    public double getIloscPaliwa() {
        return iloscPaliwa;
    }

    public void setIloscPaliwa(double iloscPaliwa) {
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


    public boolean czyWolny() {
        return !this.jade;

    }

    public void jestemZajety() {

        if (isCzyJechacField())
            listy.wezZlecenie(this);
        else return;
        while (czyJechac()) {
            if (!czyWyjechalZBazy)
                new ObiektJazdy().jedz(this, new VertexFinder().znajdzVertexPoNazwie("Baza firmy", localList), new VertexFinder().znajdzVertexPoNazwie(getMojeZlecenie().getZrodlo().getNazwa(), localList));
            else
                new ObiektJazdy().jedz(this, poprzednieMiejsce, new VertexFinder().znajdzVertexPoNazwie(getMojeZlecenie().getZrodlo().getNazwa(), localList));
            new ObiektJazdy().jedz(this, new VertexFinder().znajdzVertexPoNazwie(getMojeZlecenie().getZrodlo().getNazwa(), localList), new VertexFinder().znajdzVertexPoNazwie(getMojeZlecenie().getCel().getNazwa(), localList));
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


    private Vertex dodajVertex() {
        return getMojaPozycja();

    }

    public boolean sprawdzCzyWyjsc() {
        if (!czyJechac()) {
            System.out.println(getTruckName() + ": znikam!");
            return true;
        }
        return false;
    }

    public void czekajCzasWMilisekundach(int czas) {
        try {
            Thread.sleep(czas);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void funkcjaJazdy(List<Vertex> trasa) {
        System.out.println("Drukuje z pojazdu: " + trasa);
        for (int i = 1; i < trasa.size(); i++) {
            zmieniajPozycje(trasa.get(i));
        }
    }

    public int getPozycjaX() {
        return pozycjaX;
    }

    public void setPozycjaX(int pozycjaX) {
        this.pozycjaX = pozycjaX;
        spalajPaliwo();
        System.out.println(getTruckName() + " ustawiłem pozycje na (" + this.pozycjaX + "," + getPozycjaY() + ")");
    }

    public int getPozycjaY() {
        return pozycjaY;
    }

    public void setPozycjaY(int pozycjaY) {
        this.pozycjaY = pozycjaY;
        spalajPaliwo();
        System.out.println(getTruckName() + " ustawiłem pozycje na (" + getPozycjaX() + "," + this.pozycjaY + ")");
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
        System.out.println(getTruckName() + ": jestem w punkcie " + vertex.name);
        if (getIloscPaliwa() >= 0)
            System.out.println("Mam w baku " + Math.round(getIloscPaliwa() * 100) / 100 + " l paliwa");
    }


    public boolean isCzyJechacField() {
        return czyJechacField;
    }

    public void setCzyJechacField(boolean czyJechacField) {
        this.czyJechacField = czyJechacField;
    }

    public double ileSpale(double dystans) {

        double toReturn = getSpalanie() / 100 * dystans;

        return round(toReturn, 2);
    }

    public double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public boolean czyDojade(double dystans) {
        if (getIloscPaliwa() < ileSpale(dystans) || czyPusty()) {
            System.out.println("Za mało paliwa");
            return false;
        } else return true;
    }

    private void spalajPaliwo() {
        double paliwo = getIloscPaliwa();
        paliwo -= (spalanie / 100);
        setIloscPaliwa((float) paliwo);

    }

    private void jedzNaStacje() {

    }
}

class ObiektJazdy {
    public void jedz(Pojazd pojazd, Vertex skad, Vertex dokad) {
        List<Vertex> localList = this.backupListyVertex;
        double dystans;
        pojazd.jade = true;
        if (skad.name.contains("Baza"))
            pojazd.czyWyjechalZBazy = true;
        Nawigacja gps = new Nawigacja(pojazd.getMojaPozycja(), pojazd.listy);
        List<Vertex> mojaTrasa;


        System.out.println(pojazd.getTruckName() + " wykonuje zlecenie " + pojazd.getMojeZlecenie());
        System.out.println("Jade z " + skad + " do " + dokad);
        mojaTrasa = gps.wyliczDroge(skad, dokad);
        System.out.println("MOJA TRASA1 " + mojaTrasa);
        dystans = gps.minDystans(dokad);
        System.out.println(dystans);
        System.out.println("W trasie do " + dokad + " spale " + pojazd.ileSpale(dystans) + " l paliwa");
        pojazd.setCzyJechacField(pojazd.czyDojade(dystans));
        if (pojazd.sprawdzCzyWyjsc()) return;
        pojazd.czekajCzasWMilisekundach(100);
        System.out.println("Ruszam, jestem w punkcie " + skad);
        pojazd.funkcjaJazdy(mojaTrasa);
        pojazd.setMojaPozycja(dokad);
        pojazd.poprzednieMiejsce = dokad;

    }
}