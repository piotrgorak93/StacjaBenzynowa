import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * @author Piotr Górak, Maciej Knichał dnia 2015-03-23.
 */
class Pojazd extends Thread {
    private double pojemnoscBaku;
    private double iloscPaliwa;
    private String truckName;
    private boolean jade = false;
    private float spalanie;
    private Vertex mojaPozycja;
    private boolean czyJechacField = true;
    private Ogloszenie mojeZlecenie;
    private final Baza baza;
    private boolean czyDzialac = true;
    private final Listy listy;
    private int pozycjaX;
    private int pozycjaY;
    private final List<Vertex> backupListyVertex;
    private final boolean czyWziacZlecenie = true;
    public boolean blokada = false;
    public boolean czyZatankowany = false;

    public Pojazd(Listy listy, String truckName, Baza baza) {
        this.baza = baza;
        this.listy = listy;
        setTruckName(truckName);
        setPojemnoscBaku(new Randomizer().losujZZakresu(150, 100));
        setSpalanie(new Randomizer().losujZZakresu(50, 25));
        setIloscPaliwa(getPojemnoscBaku());
        this.pozycjaX = this.baza.pozycjaX();
        this.pozycjaY = this.baza.pozycjaY();
        System.out.println(this);
        backupListyVertex = listy.getListaCustomVertex();
        setMojaPozycja(new VertexFinder().znajdzVertexPoNazwie("Baza firmy", backupListyVertex));
    }

    private Ogloszenie getMojeZlecenie() {
        return mojeZlecenie;
    }

    public void setMojeZlecenie(Ogloszenie mojeZlecenie) {
        this.mojeZlecenie = mojeZlecenie;
    }

    private double getPojemnoscBaku() {
        return pojemnoscBaku;
    }

    private void setPojemnoscBaku(int pojemnoscBaku) {
        this.pojemnoscBaku = pojemnoscBaku;
    }

    private double getIloscPaliwa() {
        return iloscPaliwa;
    }

    private void setIloscPaliwa(double iloscPaliwa) {
        this.iloscPaliwa = iloscPaliwa;
    }

    public String getTruckName() {
        return truckName;
    }

    private void setTruckName(String name) {
        this.truckName = name;
    }

    @Override
    public String toString() {
        return "Jestem " + getTruckName() + " mam w baku " + getIloscPaliwa() + " l paliwa, moje spalanie to " + getSpalanie() +
                " l/100km, jestem w miejscu (" + getPozycjaX() + "," + getPozycjaY() + ")";
    }

    @Override
    public void run() {
        while (czyJechacField) {
            while (czyWolny()) {
                czekajCzasWMilisekundach(2000);
                try {
                    jestemZajety();
                } catch (Exception e) {
                    System.out.println(getTruckName() + " się zepsuł");
                }
                if (!czyJechacField)
                    break;
            }
        }
        System.out.println("Zabijam watek samochodu " + getTruckName() + " " + currentThread().getName());
        Thread.currentThread().interrupt();
    }

    private boolean czyWolny() {
        return !this.jade;

    }

    private void jestemZajety() {
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
            if (!czyJechacField) {
                return;
            }
            jestemWolny();
        }
    }

    private boolean czyJechac() {
        return getMojeZlecenie() != null;
    }

    private void jestemWolny() {
        System.out.println("Zlecenie wykonane");
        setMojeZlecenie(null);
        this.jade = false;
    }

    private float getSpalanie() {
        return spalanie;
    }

    private void setSpalanie(int spalanie) {
        this.spalanie = spalanie;
    }

    private Vertex getMojaPozycja() {
        return mojaPozycja;
    }

    private void setMojaPozycja(Vertex mojaPozycja) {
        this.mojaPozycja = mojaPozycja;
    }

    public void czekajCzasWMilisekundach(int czas) {
        try {
            Thread.sleep(czas);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void funkcjaJazdy(List<Vertex> trasa) {
        System.out.println("Drukuje trasę z pojazdu: " + trasa);
        for (int i = 1; i < trasa.size(); i++) {
            zmieniajPozycje(trasa.get(i));
        }
    }

    private int getPozycjaX() {
        return pozycjaX;
    }

    private void setPozycjaX(int pozycjaX) {
        this.pozycjaX = pozycjaX;
        spalajPaliwo();
        System.out.println(getTruckName() + " ustawiłem pozycję na (" + this.pozycjaX + "," + getPozycjaY() + ")");
    }

    private int getPozycjaY() {
        return pozycjaY;
    }

    private void setPozycjaY(int pozycjaY) {
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
            System.out.println(getTruckName() + " mam w baku " + round(getIloscPaliwa(), 2) + " l paliwa");
    }

    private double ileSpale(double dystans) {
        double toReturn = getSpalanie() / 100 * dystans;
        return round(toReturn, 2);
    }

    private double round(double value, int places) {
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

    public void zerujVertexy() {
        for (Vertex vertex : this.listy.getListaCustomVertex()) {
            vertex.previous = null;
            vertex.adjacencies = null;
            vertex.minDistance = Double.POSITIVE_INFINITY;
        }

    }

    private void jedzNaStacje(Pojazd pojazd, Vertex skad) {
        List<Vertex> localList = pojazd.backupListyVertex;
        zerujVertexy();
        double dystans;
        jade = true;
        Nawigacja gps = new Nawigacja(pojazd.getMojaPozycja(), localList);
        List<Vertex> mojaTrasa;
        Vertex dokad = gps.znajdzStacje(listy);
        mojaTrasa = gps.wyliczDroge(skad, dokad);
        dystans = gps.minDystans(dokad);
        System.out.println("Dystans z " + getMojaPozycja() + " do " + dokad + " to " + dystans);
        System.out.println("W trasie do " + dokad + " spale " + pojazd.ileSpale(dystans) + " l paliwa");
        if (pojazd.ileSpale(dystans) <= getIloscPaliwa()) {
            pojazd.czekajCzasWMilisekundach(100);
            System.out.println("Ruszam, jestem w punkcie " + skad);
            dystans = gps.minDystans(dokad);
            System.out.println("Dystans do " + dokad + " to " + dystans);
            System.out.println("W trasie do " + dokad + " spale " + pojazd.ileSpale(dystans) + " l paliwa");
            pojazd.funkcjaJazdy(mojaTrasa);
            pojazd.setMojaPozycja(dokad);

            Stacja mojaStacja = null;

            for (Stacja stacja : listy.getListaStacji()) {
                if (dokad.name.equals(stacja.getNazwa()))
                    mojaStacja = stacja;
            }

            while (!mojaStacja.dodajPojazdDoStacji(this)) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            mojaStacja.tankujPaliwo(this);
            this.czyZatankowany = false;
            //localList2.add(localList2.size(), this);
            this.czyJechacField = true;
            //for (Stacja stacja : localList2) {

            //}


        } else {
            System.out.println("Nie dojade do stacji, znikam!");
            czyJechacField = false;

        }

    }


    private void jedz(Pojazd pojazd, Vertex skad, Vertex dokad) {
        zerujVertexy();
        List<Vertex> localList = pojazd.backupListyVertex;
        double dystans;
        pojazd.jade = true;
        Nawigacja gps = new Nawigacja(pojazd.getMojaPozycja(), localList);
        List<Vertex> mojaTrasa;
        System.out.println(pojazd.getTruckName() + " wykonuje zlecenie " + pojazd.getMojeZlecenie());
        mojaTrasa = gps.wyliczDroge(skad, dokad);
        dystans = gps.minDystans(dokad);
        System.out.println("Dystans z " + skad + " do " + dokad + " to " + dystans);
        System.out.println("W trasie do " + dokad + " spale " + pojazd.ileSpale(dystans) + " l paliwa");
        if (czyJechacNaStacje()) {
            System.out.println("Przeliczam trasę");
            jedzNaStacje(this, getMojaPozycja());
        } else {
            pojazd.czekajCzasWMilisekundach(100);
            System.out.println("Ruszam, jestem w punkcie " + skad);
            System.out.println("Jade z " + skad + " do " + dokad);
            pojazd.funkcjaJazdy(mojaTrasa);
            pojazd.setMojaPozycja(dokad);
            return;
        }
        if (czyJechacField)
            kontynuujJazde(this, getMojaPozycja(), dokad);

    }

    private void kontynuujJazde(Pojazd pojazd, Vertex skad, Vertex dokad) {
        zerujVertexy();
        List<Vertex> localList = pojazd.backupListyVertex;
        double dystans;
        pojazd.jade = true;
        Nawigacja gps = new Nawigacja(pojazd.getMojaPozycja(), localList);
        List<Vertex> mojaTrasa;
        System.out.println(pojazd.getTruckName() + " wykonuje zlecenie " + pojazd.getMojeZlecenie());
        mojaTrasa = gps.wyliczDroge(skad, dokad);
        dystans = gps.minDystans(dokad);
        System.out.println("Dystans " + skad + " do " + dokad + " to " + dystans);
        System.out.println("W trasie do " + dokad + " spale " + pojazd.ileSpale(dystans) + " l paliwa");
        pojazd.czekajCzasWMilisekundach(100);
        System.out.println("Ruszam, jestem w punkcie " + skad);
        System.out.println("Jade z " + skad + " do " + dokad);
        pojazd.funkcjaJazdy(mojaTrasa);
        pojazd.setMojaPozycja(dokad);

    }

    private boolean czyJechacNaStacje() {
        int procent = 20;
        double localPojemnosc = getPojemnoscBaku();
        localPojemnosc = localPojemnosc * procent / 100;
        System.out.println(getTruckName() + ": wskaźnik rezerwy ustawiony na: " + procent + "%: " + localPojemnosc);
        if (getIloscPaliwa() < localPojemnosc) {
            System.out.println("Rezerwa się świeci, jade na stacje");
            return true;
        } else return false;
    }

    private boolean isCzyWziacZlecenie() {
        return czyWziacZlecenie;
    }

    public void tankuj() {
        setIloscPaliwa(getPojemnoscBaku());
        System.out.println(getTruckName() + " bak napełniony: " + getIloscPaliwa() + " l");
    }
}