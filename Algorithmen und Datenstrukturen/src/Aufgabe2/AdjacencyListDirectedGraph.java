// Dennis Agostinho da Silva
// 23.01.2020

package Aufgabe2;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.Set;

/**
 * Implementierung von DirectedGraph mit einer doppelten TreeMap
 * für die Nachfolgerknoten und einer einer doppelten TreeMap
 * für die Vorgängerknoten.
 * <p>
 * Beachte: V muss vom Typ Comparable&lt;V&gt; sein.
 * <p>
 * Entspicht einer Adjazenzlisten-Implementierung
 * mit schnellem Zugriff auf die Knoten.
 *
 * @param <V> Knotentyp.
 */
public class AdjacencyListDirectedGraph<V> implements DirectedGraph<V> {
    // doppelte Map für die Nachfolgerknoten:
    private final Map<V, Map<V, Double>> succ = new TreeMap<>();

    // doppelte Map für die Vorgängerknoten:
    private final Map<V, Map<V, Double>> pred = new TreeMap<>();

    private int numberEdge = 0;
    private int numberVertex = 0;

    public static void main(String[] args) {
        DirectedGraph<Integer> g = new AdjacencyListDirectedGraph<>();

/*
 * Eigene Tests
 System.out.println(g.addVertex(2)); // true
 System.out.println(g.addVertex(4)); // true
 System.out.println(g.addVertex(1)); // true
 System.out.println(g.addVertex(5)); // true
 System.out.println(g.addVertex(2)); // false
 System.out.println(g.addVertex(5)); // false
 System.out.println(g.addVertex(4)); // false
 g.addEdge(2, 5, 2.5);
 System.out.println(g);
 System.out.println(g.addEdge(2,5)); // false
 System.out.println("");
 System.out.println(g.containsVertex(2)); // true
 System.out.println(g.containsVertex(7)); // false
 System.out.println(g.containsEdge(2, 5)); // true
 System.out.println(g.containsEdge(5, 2)); // false
 System.out.println(g.containsEdge(6, 7)); // false
 System.out.println("------------Test Ende-----------");
 */
        g.addEdge(1, 2);
        g.addEdge(2, 5);
        g.addEdge(5, 1);
        g.addEdge(2, 6);
        g.addEdge(3, 7);
        g.addEdge(4, 3);
        g.addEdge(4, 6);
        g.addEdge(7, 4);


        System.out.println(g.getNumberOfVertexes());    // 7
        System.out.println(g.getNumberOfEdges());        // 8
        System.out.println(g.getVertexSet());    // 1, 2, ..., 7


        System.out.println(g);
        // 1 --> 2 weight = 1.0
        // 2 --> 5 weight = 1.0
        // 2 --> 6 weight = 1.0
        // 3 --> 7 weight = 1.0
        // ...

        System.out.println("");
        System.out.println(g.getOutDegree(2));                // 2


        System.out.println(g.getSuccessorVertexSet(2));    // 5, 6
        System.out.println(g.getInDegree(6));                // 2
        System.out.println(g.getPredecessorVertexSet(6));    // 2, 4

        System.out.println("");
        System.out.println(g.containsEdge(1, 2));    // true
        System.out.println(g.containsEdge(2, 1));    // false
        System.out.println(g.getWeight(1, 2));    // 1.0
        g.addEdge(1, 2, 5.0);
        System.out.println(g.getWeight(1, 2));    // 5.0

        System.out.println("");
        System.out.println(g.invert());
        // 1 --> 5 weight = 1.0
        // 2 --> 1 weight = 5.0
        // 3 --> 4 weight = 1.0
        // 4 --> 7 weight = 1.0
        // ...


        Set<Integer> s = g.getSuccessorVertexSet(2);
        System.out.println(s);
        //s.remove(5); Laufzeitfehler! Warum?
        System.out.println(s);
    }

    /*
    Knoten hinzufügen.
     */
    @Override
    public boolean addVertex(V v) {
        // Wenn leer oder nicht vorhanden...
        if (succ.isEmpty() || !succ.containsKey(v)) {
            succ.put(v, new TreeMap<V, Double>());
            pred.put(v, new TreeMap<V, Double>());
            numberVertex++;
            return true;
        }
        return false;
    }

    /*
    Kanten hinzufügen.
     */
    @Override
    public boolean addEdge(V v, V w, double weight) {
        addVertex(v);
        addVertex(w);

        if (containsEdge(v, w)) {
            succ.get(w).replace(v, weight);
            pred.get(v).replace(w, weight);
        } else {
            succ.get(w).put(v, weight);
            pred.get(v).put(w, weight);
            numberEdge++;
            return true;
        }
        return false;
    }

    /*
    Kanten hinzufügen mit default Gewicht 1.
     */
    @Override
    public boolean addEdge(V v, V w) {
        if (containsEdge(v, w)) {
            addEdge(v, w, 1.0);
            return false;
        } else {
            addEdge(v, w, 1.0);
            return true;
        }
    }

    /*
    Knoten vorhanden?
     */
    @Override
    public boolean containsVertex(V v) {
        if (succ.containsKey(v))
            return true;
        return false;
    }

    /*
    Kante vorhanden?
     */
    @Override
    public boolean containsEdge(V v, V w) {
        if (succ.get(w) != null) {
            if (succ.get(w).containsKey(v))
                return true;
        }
        return false;
    }

    /*
    return Gewicht.
     */
    @Override
    public double getWeight(V v, V w) {
        return pred.get(v).get(w);
    }

    /*
    eingehende Kanten.
     */
    @Override
    public int getInDegree(V v) {
        return succ.get(v).size();
    }

    /*
    ausgehende Kanten.
     */
    @Override
    public int getOutDegree(V v) {
        return pred.get(v).size();
    }

    @Override
    public Set<V> getVertexSet() {
        return Collections.unmodifiableSet(succ.keySet()); // nicht modifizierbare Sicht
    }

    /*
    Vorgänger-Set
     */
    @Override
    public Set<V> getPredecessorVertexSet(V v) {
        return Collections.unmodifiableSet(succ.get(v).keySet());
    }

    /*
    Nachgänger-Set
     */
    @Override
    public Set<V> getSuccessorVertexSet(V v) {
        return Collections.unmodifiableSet(pred.get(v).keySet());
    }

    @Override
    public int getNumberOfVertexes() {
        return numberVertex;
    }

    @Override
    public int getNumberOfEdges() {
        return numberEdge;
    }

    /*
    Invertierung.
     */
    @Override
    public DirectedGraph<V> invert() {
        DirectedGraph<V> dg = new AdjacencyListDirectedGraph<>();
        for (Map.Entry<V, Map<V, Double>> v : pred.entrySet()) {
            for (Map.Entry<V, Double> inner : v.getValue().entrySet()) {
                dg.addEdge(inner.getKey(), v.getKey(), inner.getValue());
            }
        }
        return dg;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<V, Map<V, Double>> e : pred.entrySet()) {
            assert e.getValue() != null;
            for (Map.Entry<V, Double> inner : e.getValue().entrySet()) {
                assert inner.getValue() != null;
                sb.append(e.getKey());
                sb.append(" --> ");
                sb.append(inner.getKey());
                sb.append(" ");
                sb.append("weight = ");
                sb.append(inner.getValue());
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
