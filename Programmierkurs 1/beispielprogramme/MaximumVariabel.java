// MaximumVariabel.java

package beispielprogramme;


public final class MaximumVariabel {
    /** Es soll keine Instanzen der Klasse geben. */
    private MaximumVariabel() { }

    /**
     * max bildet das Maximum von ein oder mehreren ganzen Zahlen.
     * @param a ist die eine ganze Zahl.
     * @param b enthaelt die uebrigen ganzen Zahlen.
     * @return das Maximum der Argumente.
     */
    private static int max(int a, int... b) {
        int m = a;
        for (int n : b) {
            if (m < n) {
                m = n;
            }
        }
        return m;
    }

    /**
     * main ist der Startpunkt des Programms.
     * @param args wird nicht verwendet.
     */
    public static void main(String[] args) {
        // Aufrufe mit impliziter Felderzeugung:
        System.out.println(max(10));
        System.out.println(max(11, 12));
        System.out.println(max(13, 14, 15));

        // die gleichen Aufrufe mit expliziter Felderzeugung:
        System.out.println(max(10, new int[0]));
        System.out.println(max(11, new int[] {12}));
        System.out.println(max(13, new int[] {14, 15}));
    }
}

