// Zufall.java

package beispielprogramme;

public final class Zufall {
    /** Es soll keine Instanzen der Klasse geben. */
    private Zufall() { }

    /**
     * zufallszahlen wird mit statischem Initialisierungsblock initialisiert.
     */
    private static final int[] ZUFALLSZAHLEN;

    static {
        java.util.Random r = new java.util.Random();
        ZUFALLSZAHLEN = new int[r.nextInt(10) + 1];  // maximal 10 Zahlen
        for (int i = 0; i < ZUFALLSZAHLEN.length; ++i) {
            ZUFALLSZAHLEN[i] = r.nextInt();
        }
    }

    /**
     * main ist der Startpunkt des Programms.
     * @param args wird nicht verwendet.
     */
    public static void main(String[] args) {
        for (int n : ZUFALLSZAHLEN) {
            System.out.println(n);
        }
    }
}

