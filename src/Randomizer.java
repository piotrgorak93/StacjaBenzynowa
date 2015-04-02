import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Piotr Górak, Maciej Knichał dnia 2015-03-23.
 */
public class Randomizer {
    private ArrayList<Droga> pasujaceX = new ArrayList<Droga>();
    private ArrayList<Droga> pasujaceY = new ArrayList<Droga>();
    private ArrayList<Object> wylosowane = new ArrayList<Object>();
    private Object tab = new Object();

    Object tableToReturn[] = new Object[3];
    private boolean przyDrodze = false;
    final Droga tablicaDrog[] = {new Droga("A-B", 0, 0, 0, 21), new Droga("B-D", 0, 21, 0, 30), new Droga("D-F", 0, 30, 21, 30), new Droga("B-C", 0, 21, 11, 21),
            new Droga("H-C", 11, 7, 11, 21), new Droga("H-G", 11, 7, 16, 7), new Droga("G-J", 16, 7, 21, 7), new Droga("I-G", 16, 0, 16, 7),
            new Droga("I-L", 16, 0, 27, 0), new Droga("L-K", 27, 0, 27, 16), new Droga("E-F", 21, 16, 21, 30), new Droga("A-I", 0, 0, 16, 0),
            new Droga("E-K", 21, 16, 27, 16), new Droga("J-E", 21, 7, 21, 16)
    };

    public void setZajete(boolean zajete) {
        this.zajete = zajete;
    }

    private boolean zajete = false;

    public int losujZZakresu(int max, int min) {
        Random r = new Random();
        return r.nextInt(max - min + 1) + min;
    }

    public int losujX() {
        Random r = new Random();
        return r.nextInt(27);
    }

    public int losujY() {
        Random r = new Random();
        return r.nextInt(30);
    }

    public Object[] losujPrzyDrodze() {
        int x;
        int y;

        while (!czyPrzyDrodze()) {


            x = new Randomizer().losujX();
            y = new Randomizer().losujY();

            for (Droga aTablicaDrog1 : this.tablicaDrog) {
                if (x >= aTablicaDrog1.getxStart())
                    if (x <= aTablicaDrog1.getxEnd()) {
                        this.pasujaceX.add(this.pasujaceX.size(), aTablicaDrog1);
                    }
            }
            for (Droga aTablicaDrog : this.tablicaDrog) {
                if (y >= aTablicaDrog.getyStart()) {
                    if (y <= aTablicaDrog.getyEnd()) {
                        this.pasujaceY.add(this.pasujaceY.size(), aTablicaDrog);
                    }
                }

            }
            if (porownajArrayListy(this.pasujaceX, this.pasujaceY, x, y)) {
                if (!czyZajete()) {
                    this.wylosowane.add(wylosowane.size(), this.tab);
                    tableToReturn[0] = x;
                    tableToReturn[1] = y;
                }
            } else {
                this.pasujaceX.clear();
                this.pasujaceY.clear();
            }
        }
        return tableToReturn;
    }

    private boolean czyZajete() {
        return !wylosowane.contains(this.tab);
    }

    private boolean czyPrzyDrodze() {
        return this.przyDrodze;
    }

    private boolean porownajArrayListy(ArrayList<Droga> x, ArrayList<Droga> y, int a, int b) {
        List<Droga> c = new ArrayList<Droga>(x.size() > y.size() ? x.size() : y.size());
        c.addAll(x);
        c.retainAll(y);
        if (!c.isEmpty()) {
            this.tableToReturn[0] = a;
            this.tableToReturn[1] = b;
            this.tableToReturn[2] = c.get(0).getNazwa();
            this.przyDrodze = true;
            return true;
        } else return false;
    }
}
