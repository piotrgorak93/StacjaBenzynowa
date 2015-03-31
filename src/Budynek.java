/**
 * @author Piotr Górak, Maciej Knichał dnia 2015-03-30.
 */
public class Budynek {
    protected int x;
    protected int y;

    public Budynek() {
        int tablicaXY[] = new Randomizer().losujPrzyDrodze();
//        setX(tablicaXY[0]);
      //  setY(tablicaXY[1]);


    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


}
