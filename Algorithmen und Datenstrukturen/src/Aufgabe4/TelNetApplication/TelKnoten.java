// Dennis Agostinho da Silva
// 25.01.2020

package Aufgabe4.TelNetApplication;

public class TelKnoten {
    public final int x;
    public final int y;

    public TelKnoten(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /*
    Test-Main.
     */
    public static void main(String[] args) {
        TelKnoten a = new TelKnoten(2, 1);
        TelKnoten b = new TelKnoten(3, 4);
        TelKnoten c = new TelKnoten(2, 5);
        TelKnoten d = new TelKnoten(1, 4);
        TelKnoten e = new TelKnoten(1, 4);
        TelKnoten f = new TelKnoten(4, 1);

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        System.out.println(e);

        System.out.println(a.equals(b));
        System.out.println(d.equals(e));
        System.out.println(f.equals(e));

        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
        System.out.println(d.hashCode());
        System.out.println(e.hashCode());
        System.out.println(f.hashCode());

    }

    public boolean equals(Object obj) {
        if (obj instanceof TelKnoten) {
            TelKnoten telKnoten = (TelKnoten) obj;
            return this.x == telKnoten.x && this.y == telKnoten.y;
        }
        return false;
    }

    public int hashCode() {
        return x * 31 + y;
    }

    public String toString() {
        return "(" + this.x + "|" + this.y + ")";
    }

}
