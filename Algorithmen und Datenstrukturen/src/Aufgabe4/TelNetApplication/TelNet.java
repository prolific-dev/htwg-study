// Dennis Agostinho da Silva
// 25.01.2020

package Aufgabe4.TelNetApplication;

import edu.princeton.cs.introcs.StdDraw;
import edu.princeton.cs.introcs.StdRandom;

import java.util.*;


public class TelNet {
    private final Map<TelKnoten, Integer> telKnotenListe;
    private final List<TelVerbindung> minSpanTree;
    private final int lbg;

    public TelNet(int lbg) {
        this.lbg = lbg;
        this.telKnotenListe = new HashMap<>();
        this.minSpanTree = new LinkedList<>();
    }

    public static void main(String[] args) {
        TelNet telNet = new TelNet(100);

        telNet.generateRandomTelNet(1000, 1000, 1000);
        telNet.computeOptTelNet();
        telNet.drawOptTelNet(1000,1000);
        System.out.println(telNet.getOptTelNetKosten());

/*
        telNet = new TelNet(7);
        telNet.addTelKnoten(1, 1);
        telNet.addTelKnoten(3, 1);
        telNet.addTelKnoten(4, 2);
        telNet.addTelKnoten(3, 4);
        telNet.addTelKnoten(7, 5);
        telNet.addTelKnoten(2, 6);
        telNet.addTelKnoten(4, 7);

        telNet.computeOptTelNet();
        telNet.drawOptTelNet(8, 8);
        System.out.println(telNet.getOptTelNetKosten());
        System.out.println(telNet.toString());


 */
    }

    public boolean addTelKnoten(int x, int y) {
        TelKnoten telKnoten = new TelKnoten(x, y);

        if (!telKnotenListe.containsKey(telKnoten)) {
            minSpanTree.clear();
            telKnotenListe.put(telKnoten, telKnotenListe.size());
            return true;
        }
        return false;
    }

    public boolean computeOptTelNet() {
        UnionFind forest = new UnionFind(telKnotenListe.size());
        Queue<TelVerbindung> edges = new PriorityQueue<>();

        for (TelKnoten tkx : telKnotenListe.keySet()) {
            for (TelKnoten tky : telKnotenListe.keySet()) {
                if (tkx.equals(tky)) {
                    continue;
                }
                // Manhatten-Distanz
                int c = Math.abs(tkx.x - tky.x) + Math.abs(tkx.y - tky.y);

                TelVerbindung telVerbindung = new TelVerbindung(tkx, tky, c);

                if (telVerbindung.c <= this.lbg) {
                    edges.add(telVerbindung);
                }
            }
        }


        while (forest.size() != 1 && !edges.isEmpty()) {
            TelVerbindung telVerbindung = edges.poll();

            int v = telKnotenListe.get(telVerbindung.u);
            int w = telKnotenListe.get(telVerbindung.v);

            int t1 = forest.find(v);
            int t2 = forest.find(w);

            if (t1 != t2) {
                forest.union(t1, t2);
                minSpanTree.add(telVerbindung);
            }
        }

        if (forest.size() != 1) {
            minSpanTree.clear();
            System.out.println("Es existiert kein aufspannender Baum");
            return false;
        }
        return true;
    }

    public List<TelVerbindung> getOptTelNet() {
        return minSpanTree;
    }

    public int getOptTelNetKosten() {

        int cost = 0;
        for (TelVerbindung tv : minSpanTree) {
            cost += tv.c;
        }
        return cost;
    }

    public void drawOptTelNet(int xMax, int yMax) {
        StdDraw.clear();

        StdDraw.setPenRadius(0.010);
        StdDraw.setPenColor(StdDraw.BLUE);


        for (TelKnoten telKnoten : telKnotenListe.keySet()) {
            StdDraw.point(telKnoten.x / (double) xMax, telKnoten.y / (double) yMax);
        }

        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(StdDraw.BLACK);

        for (TelVerbindung telVerbindung : minSpanTree) {
            StdDraw.line(telVerbindung.u.x / (double) xMax, telVerbindung.u.y / (double) yMax,
                    telVerbindung.u.x / (double) xMax, telVerbindung.v.y / (double) yMax);

            StdDraw.line(telVerbindung.u.x / (double) xMax, telVerbindung.v.y / (double) yMax,
                    telVerbindung.v.x / (double) xMax, telVerbindung.v.y / (double) yMax);
        }

        StdDraw.show(0);
    }

    public void generateRandomTelNet(int n, int xMax, int yMax) {
        telKnotenListe.clear();

        if (n <= 0 || xMax <= 0 || yMax <= 0) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < n; i++) {
            int x = StdRandom.uniform(xMax);
            int y = StdRandom.uniform(yMax);

            if (!addTelKnoten(x, y)) {
                i--;
            }
        }
    }

    public int size() {
        return telKnotenListe.size();
    }

    public String toString() {
        return "Telnet mit lbg= " + lbg + "\ntkl=" + telKnotenListe + "\nmst=" + minSpanTree;
    }
}
