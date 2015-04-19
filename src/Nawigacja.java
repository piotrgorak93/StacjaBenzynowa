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

    private Edge[] getAdjacencies() {
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

class Nawigacja {
    private Vertex mojaPozycja;
    private ArrayList<Vertex> listaVertex;
    Listy listaBudynkow;
    private Collection<Vertex> col;
    private Edge[] temp;
    private ArrayList<Edge> tempArr = new ArrayList<Edge>();
    Vertex to;

    public Nawigacja(Vertex mojaPozycja, List<Vertex> lista) {
        this.mojaPozycja = mojaPozycja;
        //this.listaBudynkow = listy;
        listaVertex = (ArrayList<Vertex>) lista;
//
//        for (int i = 0; i < listy.getListaBudynkow().size(); i++) {
//            dodajDoListyLokalnychVertex(new Vertex(listy.getListaBudynkow().get(i).getNazwa(),
//                    listy.getListaBudynkow().get(i).getX(), listy.getListaBudynkow().get(i).getY()));
//        }
        //   dodajDoListyLokalnychVertex(this.vertexDoDodania);
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
//            if (listaVertex.get(i).x == this.mojaPozycja.x) {
//                tempArr.add(tempArr.size(), new Edge(this.mojaPozycja, Math.abs(listaVertex.get(i).y - this.mojaPozycja.y)));
//            } else if (listaVertex.get(i).y == this.mojaPozycja.y) {
//                tempArr.add(tempArr.size(), new Edge(this.mojaPozycja, Math.abs(listaVertex.get(i).x - this.mojaPozycja.x)));
//            }
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

    private void computePaths(Vertex source) {
        source.minDistance = 0.;
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<>();
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

    private List<Vertex> getShortestPathTo(Vertex target) {
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
        if (!listaVertex.contains(param))
            listaVertex.add(listaVertex.size(), param);

    }


    public Vertex znajdzStacje(Listy list) {
        System.out.println("Stacje paliw na mapie:");
        computePaths(mojaPozycja);
        HashMap<Double, Vertex> lista = new HashMap<>();
        for (Stacja stacja : list.getListaStacji()) {
            System.out.println(stacja);
            Vertex localStacja = new VertexFinder().znajdzVertexPoNazwie(stacja.getNazwa(), listaVertex);
            lista.put(minDystans(localStacja), localStacja);
        }
        Double min = Collections.min(lista.keySet());


        System.out.println("Najblizej jest: " + lista.get(min));
        return lista.get(min);
    }

}


class VertexFinder {
    public Vertex znajdzVertexPoNazwie(String nazwa, List<Vertex> listaVertex) {
        for (Vertex vertex : listaVertex) {
            if (vertex.name.equals(nazwa))
                return vertex;
        }
        return null;
    }
}