/**
 * @author Piotr Górak, Maciej Knichał dnia 2015-03-30.
 */
class Budynek {
    int x;
    int y;
    private String nazwa;
    private Listy listy;

    public String getNazwa() {
        return nazwa;
    }

    void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    Listy getListy() {
        return listy;
    }

    private void setListy(Listy listy) {
        this.listy = listy;
    }

    Budynek(String nazwa, Listy listy) {
        setListy(listy);
        setNazwa(nazwa);
        Object[] tablicaXY = new Randomizer().losujPrzyDrodze(getListy());
        setX((Integer) tablicaXY[0]);
        setY((Integer) tablicaXY[1]);
        System.out.println("Przy drodze " + tablicaXY[2] + " utworzono budynek " + nazwa + " w pozycji (" + tablicaXY[0] + "," + tablicaXY[1] + ")");
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

    private void setX(int x) {
        this.x = x;
    }

    private void setY(int y) {
        this.y = y;
    }


}
