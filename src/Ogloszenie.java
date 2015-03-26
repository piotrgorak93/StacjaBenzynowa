/**
 * @author Piotr Górak, Maciej Knichał dnia 2015-03-23.
 */
public class Ogloszenie {
    private String zrodlo;
    private String cel;
    private int numerOgloszenia;
    private static volatile int numer = 1;


    public Ogloszenie(String zrodlo, String cel) {
        this.zrodlo = zrodlo;
        this.cel = cel;
        this.numerOgloszenia = numer++;
    }

    @Override
    public String toString() {
        return "z " + this.zrodlo + " do " + this.cel + ", numer zlecenia: " + numerOgloszenia;
    }
}
