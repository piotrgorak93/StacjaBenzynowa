/**
 * @author Piotr Górak, Maciej Knichał dnia 2015-03-23.
 */
public class Ogloszenie {
    private String zrodlo;
    private String cel;
    private int xZrodlo;
    private int yZrodlo;
    private int xCel;
    private int yCel;
    private int numerOgloszenia;
    private static volatile int numer = 1;


    public Ogloszenie(String zrodlo, String cel, int xZrodlo, int yZrodlo, int xCel, int yCel) {
        this.zrodlo = zrodlo;
        this.cel = cel;
        this.xZrodlo = xZrodlo;
        this.yZrodlo = yZrodlo;
        this.xCel = xCel;
        this.yCel = yCel;
        this.numerOgloszenia = numer++;
    }

    public String getZrodlo() {
        return this.zrodlo;
    }

    public String getCel() {
        return this.cel;
    }

    @Override
    public String toString() {
        return "z " + this.zrodlo + " do " + this.cel + ", numer zlecenia: " + this.numerOgloszenia;
    }
}
