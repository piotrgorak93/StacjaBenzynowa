/**
 * @author Piotr Górak, Maciej Knichał dnia 2015-03-30.
 */
public class Budynek {
    protected int x;
    protected int y;

    public Budynek() {
        Object[] tablicaXY = new Randomizer().losujPrzyDrodze();
        setX((Integer) tablicaXY[0]);
        setY((Integer) tablicaXY[1]);
        System.out.print("Przy drodze "+tablicaXY[2]+" utworzono budynek ");
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
