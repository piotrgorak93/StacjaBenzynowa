/**
 * @author Piotr Górak, Maciej Knichał dnia 2015-03-30.
 */
public class Budynek {
    protected int x;
    protected int y;
    protected String nazwa;
    protected Listy listy;

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Listy getListy() {
        return listy;
    }

    public void setListy(Listy listy) {
        this.listy = listy;
    }

    public Budynek(String nazwa, Listy listy) {
        setListy(listy);
        setNazwa(nazwa);
        Object[] tablicaXY = new Randomizer().losujPrzyDrodze();
        setX((Integer) tablicaXY[0]);
        setY((Integer) tablicaXY[1]);
        System.out.println("Przy drodze " + tablicaXY[2] + " utworzono budynek " + nazwa);
        listy.getListaBudynkow().add(listy.getListaBudynkow().size(), this);
    }

    @Override
    public String toString() {
        return this.getNazwa() + " (" + getX() + "," + getY() + ")";
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
