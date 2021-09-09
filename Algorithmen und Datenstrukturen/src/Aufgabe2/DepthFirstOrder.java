// Dennis Agostinho da Silva
// 23.01.2020

package Aufgabe2;

import java.util.*;

/**
 * Klasse für Tiefensuche.
 *
 * @param <V> Knotentyp.
 */
public class DepthFirstOrder<V> {

    private final List<V> preOrder = new LinkedList<>();
    private final List<V> postOrder = new LinkedList<>();
    private final DirectedGraph<V> myGraph;
    private int numberOfDFTrees = 0;
    // ...
    private boolean[] visited;

    /**
     * Führt eine Tiefensuche für g durch.
     *
     * @param g gerichteter Graph.
     */
    public DepthFirstOrder(DirectedGraph<V> g) {
        myGraph = g;
        // ...
    }

    public static void main(String[] args) {
        DirectedGraph<Integer> g = new AdjacencyListDirectedGraph<>();
        g.addEdge(1, 2);
        g.addEdge(2, 5);
        g.addEdge(5, 1);
        g.addEdge(2, 6);
        g.addEdge(3, 7);
        g.addEdge(4, 3);
        g.addEdge(4, 6);
        //g.addEdge(7,3);
        g.addEdge(7, 4);

        DepthFirstOrder<Integer> dfs = new DepthFirstOrder<>(g);
        dfs.visitDFAllNodes();
        System.out.println(dfs.numberOfDFTrees());    // 2
        System.out.println(dfs.preOrder());        // [1, 2, 5, 6, 3, 7, 4]
        System.out.println(dfs.postOrder());        // [5, 6, 2, 1, 4, 7, 3]


    }

    /*
    Tiefensuche
     */
    public void visitDF(V v, DirectedGraph<V> g) {
        Set<V> besucht = new TreeSet<>();
        visitDFR(v, myGraph, besucht);
    }

    /*
    Tiefensuche rekursiv
     */
    public void visitDFR(V v, DirectedGraph<V> g, Set<V> besucht) {
        besucht.add(v);
        preOrder.add(v);
        //System.out.println(v);

        for (V w : myGraph.getSuccessorVertexSet(v)) {
            if (!besucht.contains(w)) {
                visitDFR(w, myGraph, besucht);
            }
        }
        postOrder.add(v);
    }

    /*
    Tiefensuche gesamt
     */
    public void visitDFAllNodes() {
        Set<V> besucht = new TreeSet<>();

        for (V v : myGraph.getVertexSet()) {
            if (!besucht.contains(v)) {
                numberOfDFTrees++;
                visitDFR(v, myGraph, besucht);
            }
        }
    }

    /*
    Tiefensuche für StrongComponents, liefert einzelne Sets zurück.
     */
    public Set<V> visitDFforStrongComponents(V v, DirectedGraph<V> g, Set<V> besucht) {
        besucht.add(v);
        preOrder.add(v);

        for (V entry1 : g.getSuccessorVertexSet(v)) {
            if (!besucht.contains(entry1)) {
                visitDFforStrongComponents(entry1, g, besucht);
            }
        }
        postOrder.add(v);
        return besucht;
    }

    /**
     * Liefert eine nicht modifizierbare Liste (unmodifiable view) mit einer
     * Pre-Order-Reihenfolge zurück.
     *
     * @return Pre-Order-Reihenfolge der Tiefensuche.
     */
    public List<V> preOrder() {
        return Collections.unmodifiableList(preOrder);
    }

    /**
     * Liefert eine nicht modifizierbare Liste (unmodifiable view) mit einer
     * Post-Order-Reihenfolge zurück.
     *
     * @return Post-Order-Reihenfolge der Tiefensuche.
     */
    public List<V> postOrder() {
        return Collections.unmodifiableList(postOrder);
    }

    /**
     * @return Anzahl der Bäume des Tiefensuchwalds.
     */
    public int numberOfDFTrees() {
        return numberOfDFTrees;
    }
}
