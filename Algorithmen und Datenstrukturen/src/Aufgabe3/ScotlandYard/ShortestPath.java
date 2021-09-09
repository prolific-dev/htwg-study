// Dennis Agostinho da Silva
// 23.01.2020

package Aufgabe3.ScotlandYard;

import Aufgabe2.DirectedGraph;
import Aufgabe3.SYSimulation.sim.SYSimulation;

import java.awt.*;
import java.util.*;
import java.util.List;
// ...

/**
 * Kürzeste Wege in Graphen mit A*- und Dijkstra-Verfahren.
 *
 * @param <V> Knotentyp.
 */
public class ShortestPath<V> {

    SYSimulation sim = null;

    Map<V, Double> dist = new HashMap<>(); // Distanz für jeden Knoten // TODO: Abchecken ob richtige Map
    Map<V, V> pred = new HashMap<>(); // Vorgänger für jeden Knoten
    // ...
    DirectedGraph<V> myGraph;
    Heuristic<V> heuristic;
    V v;

    /**
     * Konstruiert ein Objekt, das im Graph g k&uuml;rzeste Wege
     * nach dem A*-Verfahren berechnen kann.
     * Die Heuristik h schätzt die Kosten zwischen zwei Knoten ab.
     * Wird h = null gewählt, dann ist das Verfahren identisch
     * mit dem Dijkstra-Verfahren.
     *
     * @param g Gerichteter Graph
     * @param h Heuristik. Falls h == null, werden kürzeste Wege nach
     *          dem Dijkstra-Verfahren gesucht.
     */
    public ShortestPath(DirectedGraph<V> g, Heuristic<V> h) {
        // ...
        this.myGraph = g;
        this.heuristic = h;
    }

    /**
     * Diese Methode sollte nur verwendet werden,
     * wenn kürzeste Wege in Scotland-Yard-Plan gesucht werden.
     * Es ist dann ein Objekt für die Scotland-Yard-Simulation zu übergeben.
     * <p>
     * Ein typische Aufruf für ein SYSimulation-Objekt sim sieht wie folgt aus:
     * <p><blockquote><pre>
     *    if (sim != null)
     *       sim.visitStation((Integer) v, Color.blue);
     * </pre></blockquote>
     *
     * @param sim SYSimulation-Objekt.
     */
    public void setSimulator(SYSimulation sim) {
        this.sim = sim;
    }

    /**
     * Sucht den kürzesten Weg von Starknoten s zum Zielknoten g.
     * <p>
     * Falls die Simulation mit setSimulator(sim) aktiviert wurde, wird der Knoten,
     * der als nächstes aus der Kandidatenliste besucht wird, animiert.
     *
     * @param s Startknoten
     * @param g Zielknoten
     */
    public boolean searchShortestPath(V s, V g) {
        // ...
        if (this.heuristic != null) {
            return goASternAlgorithm(s, g);
        } else {
            goDijkstraAlgorithm(s);
            v = g;
            if (getShortestPath().get(0) != s) {
                return false;
            } else {
                return true;
            }
        }
    }

    private void goDijkstraAlgorithm(V s) {
        // leere Kandidatenliste
        List<V> kl = new LinkedList<>();

        addVertexDistance(s);

        kl.add(s);

        while (!kl.isEmpty()) {
            double z = Double.MAX_VALUE;
            // lösche Knoten v aus kl mit d[v] minimal
            for (V x : kl) {
                if (dist.get(x) < z) {
                    v = x;
                    z = dist.get(x);
                }
            }
            kl.remove(v);

            setVisitStationAndPrint();

            kl = improveDistance(kl);
        }
    }

    private boolean goASternAlgorithm(V s, V g) {
        // leere Kandidatenliste
        List<V> kl = new LinkedList<>();

        addVertexDistance(s);

        kl.add(s);

        while (!kl.isEmpty()) {
            // lösche Knoten v aus kl mit d[v] + h(v,z) minimal
            double u = Double.MAX_VALUE;
            for (V x : kl) {
                double dh = dist.get(x) + heuristic.estimatedCost(x, g);
                if (dh < u) {
                    v = x;
                    u = dh;
                }
            }
            kl.remove(v);

            setVisitStationAndPrint();

            // Zielknoten erreicht
            if (v.equals(g)) {
                return true;
            }

            kl = improveDistance(kl);
        }
        return false;
    }

    private void addVertexDistance(V s) {
        v = s;
        // für jeden Knoten v
        for (V x : myGraph.getVertexSet()) {
            // infinity
            dist.put(x, Double.POSITIVE_INFINITY);
            // undefined
            pred.put(x, null);
        }
        // Startknoten
        dist.put(s, 0.0);
    }

    private void setVisitStationAndPrint() {
        if (sim != null) {
            sim.visitStation((Integer) v, Color.blue);
        }
        System.out.println("Besuche Knoten " + v + " mit d = " + dist.get(v));
    }

    private List<V> improveDistance(List<V> kl) {
        List<V> klMethod = new LinkedList<>();
        klMethod = kl;

        // jeden adjazenten Knoten w von v
        for (V w : myGraph.getSuccessorVertexSet(v)) {
            // w noch nicht besucht und nicht in Kandidatenliste
            if (dist.get(w) == Double.POSITIVE_INFINITY) {
                klMethod.add(w);
            }

            // d[v] + c(v,w) < d[w]
            if (dist.get(v) + myGraph.getWeight(v, w) < dist.get(w)) {
                // p[w] = v
                pred.put(w, v);
                // d[w] = d[v] + c(v,w)
                dist.put(w, dist.get(v) + myGraph.getWeight(v, w));
            }
        }

        return klMethod;
    }


    /**
     * Liefert einen kürzesten Weg von Startknoten s nach Zielknoten g.
     * Setzt eine erfolgreiche Suche von searchShortestPath(s,g) voraus.
     *
     * @return kürzester Weg als Liste von Knoten.
     * @throws IllegalArgumentException falls kein kürzester Weg berechnet wurde.
     */
    public List<V> getShortestPath() {
        // ...
        List<V> help = new LinkedList<>();
        V u = v;
        help.add(v);
        while (pred.get(u) != null) {
            u = pred.get(u);
            help.add(0, u);
        }
        return help;
    }

    /**
     * Liefert die Länge eines kürzesten Weges von Startknoten s nach Zielknoten g zurück.
     * Setzt eine erfolgreiche Suche von searchShortestPath(s,g) voraus.
     *
     * @return Länge eines kürzesten Weges.
     * @throws IllegalArgumentException falls kein kürzester Weg berechnet wurde.
     */
    public double getDistance() {
        // ...
        double help = 0;
        V last = null;
        List<V> helpList = getShortestPath();
        for (V x : helpList) {
            if (last != null) {
                help += myGraph.getWeight(last, x);
            }
            last = x;
        }
        return help;
    }

}
