import java.util.ArrayList;

/**
 * @author Piotr Górak, Maciej Knichał dnia 2015-03-23.
 */
public class Stacja extends Budynek {
    private int x;
    private int y;
    private String nazwa;
    private Listy listy;
    private final int iloscDystrybutorow = 4;
    private ArrayList<Pojazd> localListPojazd;
    public ArrayList<Pojazd> listaPojazdowNaStacji = new ArrayList<>();


    public Stacja(String nazwa, Listy listy) {
        super(nazwa, listy);
        this.nazwa = nazwa;
        this.listy = listy;
        this.x = super.x;
        this.y = super.y;
        listy.getListaStacji().add(listy.getListaStacji().size(), this);
    }


    public void run() {

        if (!listaPojazdowNaStacji.isEmpty()) {
            listaPojazdowNaStacji.get(0).blokada = true;
            System.out.println("Klient przyjechał");
            for (int i = 0; i <= listaPojazdowNaStacji.size(); i++) {
                System.out.println("Pętla sprawdza");
                if (listaPojazdowNaStacji.size() <= iloscDystrybutorow) {
                    listaPojazdowNaStacji.get(i).czekajCzasWMilisekundach(2000);
                    listaPojazdowNaStacji.get(i).tankuj();
                    listaPojazdowNaStacji.remove(i);
                }
            }
            System.out.println("Zapraszamy ponownie!");


        }
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public synchronized boolean dodajPojazdDoStacji(Pojazd pojazd) {
        if (this.listaPojazdowNaStacji.size() < this.iloscDystrybutorow) {
            this.listaPojazdowNaStacji.add(this.listaPojazdowNaStacji.size(), pojazd);
            return true;
        } else {
            System.out.println("Czekaj na wolne miejsce");
            return false;
        }
    }

    public void tankujPaliwo(Pojazd pojazd) {
        pojazd.tankuj();
        pojazd.czyZatankowany = true;
        int indeksPojazdu = listaPojazdowNaStacji.indexOf(pojazd);

        this.listaPojazdowNaStacji.remove(indeksPojazdu);
    }
}
