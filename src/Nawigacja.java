/**
 * @author Piotr Górak, Maciej Knichał dnia 2015-03-26.
 */

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import java.util.*;

class Vertex implements Comparable<Vertex> {
    public final String name;
    public final int x;
    public final int y;
    public Edge[] adjacencies;
    public double minDistance = Double.POSITIVE_INFINITY;
    public Vertex previous;

    public Vertex(String argName, int x, int y) {
        name = argName;
        this.x = x;
        this.y = y;
        getAdjacencies();
    }

    public Edge[] getAdjacencies() {
        return this.adjacencies;
    }

    public String toString() {
        return name + " (" + x + "," + y + ")";
    }

    public int compareTo(Vertex other) {
        return Double.compare(minDistance, other.minDistance);
    }

}

class Edge {
    public final Vertex target;
    public final double weight;

    public Edge(Vertex argTarget, double argWeight) {
        target = argTarget;
        weight = argWeight;
    }
}

public class Nawigacja {
    private Vertex mojaPozycja;
    private Vertex vertexDoDodania;
    ArrayList<Vertex> listaVertex;
    Listy listaBudynkow;
    Collection<Vertex> col;
    Edge[] temp;
    ArrayList<Edge> tempArr = new ArrayList<Edge>();
    Vertex to;

    public Nawigacja(Vertex mojaPozycja, Listy listy, Vertex vertexDoDodania) {
        this.vertexDoDodania = vertexDoDodania;
        this.mojaPozycja = mojaPozycja;
        this.listaBudynkow = listy;

        listaVertex = new ArrayList<>(Arrays.asList(new Vertex("A", 0, 0),
                new Vertex("B", 0, 21), new Vertex("C", 11, 21),
                new Vertex("D", 0, 30), new Vertex("E", 21, 16), new Vertex("F", 21, 30),
                new Vertex("G", 16, 7), new Vertex("H", 11, 7), new Vertex("I", 16, 0),
                new Vertex("J", 21, 7), new Vertex("K", 27, 16), new Vertex("L", 27, 0)

        ));
        for (int i = 0; i < listy.getListaBudynkow().size(); i++) {
            dodajDoListyLokalnychVertex(new Vertex(listy.getListaBudynkow().get(i).getNazwa(),
                    listy.getListaBudynkow().get(i).getX(), listy.getListaBudynkow().get(i).getY()));
        }
        dodajDoListyLokalnychVertex(vertexDoDodania);
        Multimap<Vertex, Vertex> mapa = HashMultimap.create();
        pionowo(listaVertex, mapa);
        poziomo(listaVertex, mapa);


        for (int i = 0; i < listaVertex.size(); i++) {
            col = mapa.get(listaVertex.get(i));
            for (Vertex next : col) {
                if (listaVertex.get(i).x == next.x) {
                    tempArr.add(tempArr.size(), new Edge(next, Math.abs(listaVertex.get(i).y - next.y)));
                    //    tempArr.add(tempArr.size(), new Edge(this.mojaPozycja, Math.abs(listaVertex.get(i).y - this.mojaPozycja.y)));
                } else {
                    tempArr.add(tempArr.size(), new Edge(next, Math.abs(listaVertex.get(i).x - next.x)));
                    //  tempArr.add(tempArr.size(), new Edge(this.mojaPozycja, Math.abs(listaVertex.get(i).x - this.mojaPozycja.x)));
                }
            }
            if (listaVertex.get(i).x == this.mojaPozycja.x) {
                tempArr.add(tempArr.size(), new Edge(this.mojaPozycja, Math.abs(listaVertex.get(i).y - this.mojaPozycja.y)));
            } else if (listaVertex.get(i).y == this.mojaPozycja.y) {
                tempArr.add(tempArr.size(), new Edge(this.mojaPozycja, Math.abs(listaVertex.get(i).x - this.mojaPozycja.x)));
            }
            temp = tempArr.toArray(new Edge[tempArr.size()]);
            listaVertex.get(i).adjacencies = temp;
            tempArr.clear();

        }
    }

    public List<Vertex> wyliczDroge(Vertex skad, Vertex dokad) {
        computePaths(skad);
        minDystans(dokad);
        return getShortestPathTo(dokad);
    }

    public double minDystans(Vertex dokad) {
        return dokad.minDistance;
    }

    public static void computePaths(Vertex source) {
        source.minDistance = 0.;
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
        vertexQueue.add(source);

        while (!vertexQueue.isEmpty()) {
            Vertex u = vertexQueue.poll();

            // Visit each edge exiting u
            for (Edge e : u.adjacencies) {
                Vertex v = e.target;
                double weight = e.weight;
                double distanceThroughU = u.minDistance + weight;
                if (distanceThroughU < v.minDistance) {
                    vertexQueue.remove(v);

                    v.minDistance = distanceThroughU;
                    v.previous = u;
                    vertexQueue.add(v);
                }
            }
        }
    }

    public static List<Vertex> getShortestPathTo(Vertex target) {
        List<Vertex> path = new ArrayList<Vertex>();
        for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
            path.add(vertex);

        Collections.reverse(path);
        return path;
    }

    private void pionowo(List<Vertex> listaVertex, Multimap<Vertex, Vertex> map) {
        int found = 0;
        for (int i = 0; i < listaVertex.size(); i++) {
            for (int j = 0; j < listaVertex.size(); j++) {
                if (listaVertex.get(i).x == listaVertex.get(j).x) {
                    if (listaVertex.get(i).y < listaVertex.get(j).y)
                        found++;
                    if (found != 2) {
                        if (!listaVertex.get(i).equals(listaVertex.get(j))) {
                            map.put(listaVertex.get(i), listaVertex.get(j));
                        }
                    }
                }
            }
            found = 0;
        }
    }

    private void poziomo(List<Vertex> listaVertex, Multimap<Vertex, Vertex> map) {
        int f = 0;
        for (int i = 0; i < listaVertex.size(); i++) {
            for (int j = 0; j < listaVertex.size(); j++) {
                if (listaVertex.get(i).y == listaVertex.get(j).y) {
                    if (listaVertex.get(i).x < listaVertex.get(j).x)
                        f++;
                    if (f != 2) {
                        if (!listaVertex.get(i).equals(listaVertex.get(j))) {
                            map.put(listaVertex.get(i), listaVertex.get(j));
                        }

                    }
                }
            }
            f = 0;
        }
    }

    public void dodajDoListyLokalnychVertex(Vertex param) {
        if (param != null)
            this.listaVertex.add(this.listaVertex.size(), param);
    }

    public Vertex znajdzVertexPoNazwie(String nazwa) {
        for (Vertex vertex : listaVertex) {
            if (vertex.name.equals(nazwa))
                return vertex;
        }
        return null;
    }

    public List<Vertex> znajdzStacje() {
        System.out.println("Stacje paliw na mapie:");
        for (Stacja stacja : listaBudynkow.getListaStacji()) {
            System.out.println(stacja);
        }


        return null;
    }
}





