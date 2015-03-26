/**
 * @author Piotr Górak, Maciej Knichał dnia 2015-03-26.
 */

import java.util.*;

class Vertex implements Comparable<Vertex> {
    public final String name;
    public final int x;
    public final int y;
    public Edge[] adjacencies;
    public double minDistance = Double.POSITIVE_INFINITY;
    public Vertex previous;

    public Edge[] getAdjacencies() {
        return this.adjacencies;
    }

    public Vertex(String argName, int x, int y) {
        name = argName;
        this.x = x;
        this.y = y;
        getAdjacencies();
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

    public Nawigacja(Vertex from, Vertex to) {
        List<Vertex> listaVertex = Arrays.asList(new Vertex("A", 0, 0),
                new Vertex("B", 0, 21), new Vertex("B", 0, 21), new Vertex("C", 11, 21),
                new Vertex("D", 0, 30), new Vertex("E", 21, 16), new Vertex("F", 21, 30),
                new Vertex("G", 16, 7), new Vertex("H", 11, 7), new Vertex("I", 16, 0),
                new Vertex("J", 21, 7), new Vertex("K", 27, 16), new Vertex("L", 27, 0)
        );

        listaVertex.get(0).adjacencies = new Edge[]{new Edge(listaVertex.get(1), 21),
                new Edge(listaVertex.get(8), 16)};
        listaVertex.get(1).adjacencies = new Edge[]{new Edge(listaVertex.get(0), 21),
                new Edge(listaVertex.get(2), 11),
                new Edge(listaVertex.get(2), 9)};
        listaVertex.get(2).adjacencies = new Edge[]{new Edge(listaVertex.get(1), 11), new Edge(listaVertex.get(7), 14)};
        listaVertex.get(2).adjacencies = new Edge[]{new Edge(listaVertex.get(2), 9),
                new Edge(listaVertex.get(5), 21)};
        listaVertex.get(4).adjacencies = new Edge[]{new Edge(listaVertex.get(10), 6), new Edge(listaVertex.get(9), 9)};
        listaVertex.get(5).adjacencies = new Edge[]{
                new Edge(listaVertex.get(3), 21),
                new Edge(listaVertex.get(4), 14)};
        listaVertex.get(6).adjacencies = new Edge[]{new Edge(listaVertex.get(7), 5),
                new Edge(listaVertex.get(9), 5), new Edge(listaVertex.get(8), 7)};
        listaVertex.get(7).adjacencies = new Edge[]{new Edge(listaVertex.get(2), 14), new Edge(listaVertex.get(6), 5)};
        listaVertex.get(8).adjacencies = new Edge[]{new Edge(listaVertex.get(6), 7), new Edge(listaVertex.get(0), 16), new Edge(listaVertex.get(11), 11)};
        listaVertex.get(9).adjacencies = new Edge[]{new Edge(listaVertex.get(6), 5), new Edge(listaVertex.get(4), 9)};
        listaVertex.get(10).adjacencies = new Edge[]{new Edge(listaVertex.get(4), 6), new Edge(listaVertex.get(11), 16)};
        listaVertex.get(11).adjacencies = new Edge[]{new Edge(listaVertex.get(10), 16), new Edge(listaVertex.get(8), 11)};

        computePaths(from);
        System.out.println("Distance from " + from + " to " + to + ": " + to.minDistance);
        List<Vertex> path = getShortestPathTo(to);
        System.out.println("Path: " + path);
    }

}

