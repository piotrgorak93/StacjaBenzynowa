/**
 * @author Piotr Górak, Maciej Knichał dnia 2015-03-23.
 */
public class Ogloszenie {
    private final Fabryka zrodlo;
    private final Magazyn cel;
    private final int numerOgloszenia;
    private static volatile int numer = 1;


    public Ogloszenie(Fabryka zrodlo, Magazyn cel) {
        this.zrodlo = zrodlo;
        this.cel = cel;
        this.numerOgloszenia = numer++;
    }

    public Fabryka getZrodlo() {
        return this.zrodlo;
    }

    public Magazyn getCel() {
        return this.cel;
    }

    public int getxZrodlo() {
        return zrodlo.getX();
    }

    public int getyZrodlo() {
        return zrodlo.getY();
    }

    public int getxCel() {
        return cel.getX();
    }

    public int getyCel() {
        return cel.getY();
    }

    public int getNumerOgloszenia() {
        return numerOgloszenia;
    }

    @Override
    public String toString() {
        return "z " + this.zrodlo.getNazwa() + " do " + this.cel.getNazwa() + ", numer zlecenia: " + this.numerOgloszenia;
    }

}
