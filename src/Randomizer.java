import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author Piotr Górak, Maciej Knichał dnia 2015-03-23.
 */
public class Randomizer {
    private ArrayList<Droga> pasujaceX = new ArrayList<Droga>();
    private ArrayList<Droga> pasujaceY = new ArrayList<Droga>();
    private boolean bool = true;
    Droga tablicaDrog[] = {new Droga("A-B", 0, 0, 0, 21), new Droga("B-D", 0, 21, 0, 30), new Droga("D-F", 0, 30, 21, 30), new Droga("B-C", 0, 21, 11, 21),
            new Droga("H-C", 11, 7, 11, 21), new Droga("H-G", 11, 7, 16, 7), new Droga("G-J", 16, 7, 21, 7), new Droga("I-G", 16, 0, 16, 7),
            new Droga("I-L", 16, 0, 27, 0), new Droga("L-K", 27, 0, 27, 16), new Droga("E-F", 21, 16, 21, 30), new Droga("A-I", 0, 0, 16, 0),
            new Droga("E-K", 21, 16, 27, 16), new Droga("J-E", 21, 7, 21, 16)
    };

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

    public int[] losujPrzyDrodze() {

        int x;
        int y;
        int tableToReturn[] = null;

        while (this.bool) {
            x = new Randomizer().losujX();
            y = new Randomizer().losujY();
            System.out.println("Wygenerowano x " + x);
            System.out.println("Wygenerowano y " + y);
            try {
                TimeUnit.SECONDS.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
                        System.out.println(y + " jest wieksze rowne " + aTablicaDrog.getyStart() + " i mniejsze rowne " + aTablicaDrog.getyEnd() + " OK");
                    } else
                        System.out.println(y + " wieksze rowne od " + aTablicaDrog.getyStart() + " i wieksze od " + aTablicaDrog.getyEnd());
                } else
                    System.out.println(y + " mniejsze od " + aTablicaDrog.getyStart() + " i nie wiadomo jakie od " + aTablicaDrog.getyEnd());


            }
            if (porownajArrayListy(this.pasujaceX, this.pasujaceY, x, y))
                System.out.println(x + " " + y);
            this.pasujaceX.clear();
            this.pasujaceY.clear();
        }


        return tableToReturn;
    }

    public boolean czyPrzyDrodze() {
        return this.bool;
    }

    public synchronized boolean porownajArrayListy(ArrayList<Droga> x, ArrayList<Droga> y, int a, int b) {

        System.out.println("Pasujace x");
        for (Droga aX : x) {
            System.out.println(aX.getNazwa());
        }
        System.out.println("Pasujace y");
        for (Droga aY : y) {
            System.out.println(aY.getNazwa());
        }
        List<Droga> c = new ArrayList<Droga>(x.size() > y.size() ? x.size() : y.size());
        c.addAll(x);
        c.retainAll(y);
        if (!c.isEmpty()) {
            //    out.println("Wygenerowano : " + x.get(0).getNazwa()+" Koordynaty: "+a+" "+b);
            //    out.close();
            System.out.println("Koordynaty: " + a + " " + b);
            System.out.println("Wygenerowano : " + c.get(0).getNazwa());
            System.out.println("(" + c.get(0).getxStart() + "," + c.get(0).getyStart() + ");(" + c.get(0).getxEnd() + "," + c.get(0).getyEnd() + ")");

            this.bool = false;

        } else System.out.println("Dalej");

        return false;
    }
}
