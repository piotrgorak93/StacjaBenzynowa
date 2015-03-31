/**
 * @author Piotr Górak, Maciej Knichał dnia 2015-03-27.
 */
public class Droga {
    private final int xStart;
    private final int yStart;
    private final int xEnd;
    private final int yEnd;
    private String nazwa;

    public Droga(String nazwa, int xStart, int yStart, int xEnd, int yEnd) {
        this.nazwa = nazwa;
        this.xStart = xStart;
        this.yStart = yStart;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
    }

    @Override
    public String toString() {
        return this.getNazwa();
    }

    public int getxEnd() {
        return xEnd;
    }

    public int getxStart() {
        return xStart;
    }

    public int getyStart() {
        return yStart;
    }

    public int getyEnd() {
        return yEnd;
    }

    public String getNazwa() {
        return nazwa;
    }
}
