import java.util.ArrayList;

/**
 * @author Piotr Górak, Maciej Knichał dnia 2015-03-23.
 */
public class Stacja extends Budynek implements Runnable {
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

    @Override
    public void run() {
        while (true) {
            if (!listaPojazdowNaStacji.isEmpty()) {
                System.out.println("Klient przyjechał");

                for (int i = 0; i <= listaPojazdowNaStacji.size(); i++) {
                    if (listaPojazdowNaStacji.size() <= iloscDystrybutorow) {
                        listaPojazdowNaStacji.get(i).czekajCzasWMilisekundach(2000);
                        listaPojazdowNaStacji.get(i).tankuj();
                        listaPojazdowNaStacji.remove(i);
                    }
                }
            }
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
}
