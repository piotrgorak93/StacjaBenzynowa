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
    private boolean czyDzialac = true;
    public Listy listy;
    private int pozycjaX;
    private int pozycjaY;
    final List<Vertex> backupListyVertex;
    private boolean czyWziacZlecenie = true;
    private boolean czyWyjsc = false;
    private boolean czyBylemNaStacji = false;

    public Pojazd(Listy listy, String truckName, Baza baza) {
        this.baza = baza;
        this.listy = listy;
        setTruckName(truckName);
        setPojemnoscBaku(new Randomizer().losujZZakresu(50, 30));
        setSpalanie(new Randomizer().losujZZakresu(50, 25));
        setIloscPaliwa(getPojemnoscBaku());
        this.pozycjaX = this.baza.pozycjaX();
        this.pozycjaY = this.baza.pozycjaY();
        System.out.println(this);
        backupListyVertex = listy.getListaCustomVertex();
        setMojaPozycja(new VertexFinder().znajdzVertexPoNazwie("Baza firmy", backupListyVertex));
    }

    public Ogloszenie getMojeZlecenie() {
        return mojeZlecenie;
    }

    public void setMojeZlecenie(Ogloszenie mojeZlecenie) {
        this.mojeZlecenie = mojeZlecenie;
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
                czekajCzasWMilisekundach(2000);
                jestemZajety();
                if (!czyJechacField)
                    break;
            }
        }
//        System.out.println("Zabijam watek" + currentThread().getName());
//        Thread.currentThread().interrupt();
    }

    private boolean czyDzialac() {
        return czyDzialac;
    }

    public boolean czyWolny() {
        return !this.jade;

    }

    public void jestemZajety() {
        List<Vertex> localList = this.backupListyVertex;

        if (isCzyWziacZlecenie())
            listy.wezZlecenie(this);
        else return;
        Vertex zrodlo = new VertexFinder().znajdzVertexPoNazwie(getMojeZlecenie().getZrodlo().getNazwa(), localList);
        Vertex cel = new VertexFinder().znajdzVertexPoNazwie(getMojeZlecenie().getCel().getNazwa(), localList);

        while (czyJechac()) {
            if (!czyJechacField) {
                return;
            }
            jedz(this, getMojaPozycja(), zrodlo);
            if (!czyJechacField) {
                return;
            }
            jedz(this, getMojaPozycja(), cel);
            jestemWolny();
        }
    }

    private boolean czyJechac() {
        return getMojeZlecenie() != null;
    }

    public void jestemWolny() {
        System.out.println("Zlecenie wykonane");
        setMojeZlecenie(null);
        this.jade = false;
    }

    public float getSpalanie() {
        return spalanie;
    }

    public void setSpalanie(int spalanie) {
        this.spalanie = spalanie;
    }

    public Vertex getMojaPozycja() {
        return mojaPozycja;
    }

    public void setMojaPozycja(Vertex mojaPozycja) {
        this.mojaPozycja = mojaPozycja;
    }

    public void czekajCzasWMilisekundach(int czas) {
        try {
            Thread.sleep(czas);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void funkcjaJazdy(List<Vertex> trasa) {
        System.out.println("Drukuje trasę z pojazdu: " + trasa);
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
        System.out.println(getTruckName() + " ustawiłem pozycję na (" + this.pozycjaX + "," + getPozycjaY() + ")");
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

    private void spalajPaliwo() {
        double paliwo = getIloscPaliwa();
        paliwo -= (spalanie / 100);
        setIloscPaliwa((float) paliwo);

    }

    private void jedzNaStacje(Pojazd pojazd, Vertex skad) {
        List<Vertex> localList = pojazd.backupListyVertex;
        Kontroler.zerujVertexy(listy);
        double dystans;
        jade = true;
        Nawigacja gps = new Nawigacja(pojazd.getMojaPozycja(), localList);
        List<Vertex> mojaTrasa;
        Vertex dokad = gps.znajdzStacje(listy);
        mojaTrasa = gps.wyliczDroge(skad, dokad);
        dystans = gps.minDystans(dokad);
        System.out.println("Dystans do " + dokad + " to " + dystans);
        System.out.println("W trasie do " + dokad + " spale " + pojazd.ileSpale(dystans) + " l paliwa");
        if (pojazd.ileSpale(dystans) > getIloscPaliwa()) {
            System.out.println("Nie dojade do stacji, wychodze!");
            this.czyJechacField = false;
        } else {
            ArrayList<Pojazd> localList2 = listy.pobierzZHashmapy(dokad);
            localList2.add(localList2.size(), this);
            this.czyBylemNaStacji = true;
        }
    }


    public void jedz(Pojazd pojazd, Vertex skad, Vertex dokad) {
        Kontroler.zerujVertexy(listy);
        List<Vertex> localList = pojazd.backupListyVertex;
        double dystans;
        pojazd.jade = true;
        Nawigacja gps = new Nawigacja(pojazd.getMojaPozycja(), localList);
        List<Vertex> mojaTrasa;

        System.out.println(pojazd.getTruckName() + " wykonuje zlecenie " + pojazd.getMojeZlecenie());
        System.out.println("Jade z " + skad + " do " + dokad);
        mojaTrasa = gps.wyliczDroge(skad, dokad);
        dystans = gps.minDystans(dokad);
        System.out.println("Dystans do " + dokad + " to " + dystans);
        System.out.println("W trasie do " + dokad + " spale " + pojazd.ileSpale(dystans) + " l paliwa");
        if (czyJechacNaStacje())
            jedzNaStacje(this, getMojaPozycja());
        if (!czyDojade(dystans)) return;
        pojazd.czekajCzasWMilisekundach(100);
        System.out.println("Ruszam, jestem w punkcie " + skad);
        if (czyBylemNaStacji)
            skad = getMojaPozycja();
        mojaTrasa = gps.wyliczDroge(skad, dokad);
        dystans = gps.minDystans(dokad);
        System.out.println("Dystans do " + dokad + " to " + dystans);
        System.out.println("W trasie do " + dokad + " spale " + pojazd.ileSpale(dystans) + " l paliwa");
        pojazd.funkcjaJazdy(mojaTrasa);
        pojazd.setMojaPozycja(dokad);
    }

    private boolean czyJechacNaStacje() {
        if (getIloscPaliwa() < 10) {
            System.out.println("Rezerwa się świeci, jade na stacje");
            return true;
        } else return false;
    }

    private boolean czyDojade(Double dystans) {
        double iloscPotrzebnegoPaliwa = ileSpale(dystans);
        if (iloscPotrzebnegoPaliwa > getIloscPaliwa()) {
            czyJechacField = false;
            System.out.println("Za mało paliwa w baku, mam " + round(getIloscPaliwa(), 2) + " l, a potrzebuje " + iloscPotrzebnegoPaliwa);
            jedzNaStacje(this, getMojaPozycja());
            return false;
        } else return true;

    }

    public boolean isCzyWziacZlecenie() {
        return czyWziacZlecenie;
    }

    public void tankuj() {
        setIloscPaliwa(pojemnoscBaku);
        System.out.println(getTruckName() + " bak napełniony");
    }
}