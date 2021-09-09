// Dennis Agostinho da Silva
// 25.01.2020

package Aufgabe4.TelNetApplication;

public class TelVerbindung implements Comparable<TelVerbindung> {
    public final TelKnoten u;
    public final TelKnoten v;
    public final int c;

    public TelVerbindung(TelKnoten u, TelKnoten v, int c) {
        this.u = u;
        this.v = v;
        this.c = c;
    }

    /*
    Test-Main.
     */
    public static void main(String[] args) {
        TelKnoten tk1 = new TelKnoten(1, 2);
        TelKnoten tk2 = new TelKnoten(3, 3);

        TelVerbindung telVerbindung = new TelVerbindung(tk1, tk2, 2);

        System.out.println(telVerbindung);

    }

    public String toString() {
        return this.u.toString() + " -> " + this.v.toString() + " mit Gewicht: " + this.c;
    }

    @Override
    public int compareTo(TelVerbindung o) {
        if (o == null) {
            throw new IllegalArgumentException();
        }
        return this.c - o.c;
    }
}
