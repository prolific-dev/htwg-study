// Programm.java
package beispielprogramme;

public final class Programm {
    /** Es soll keine Instanzen der Klasse geben. */
    private Programm() { }

    /**
     * main ist der Startpunkt des Programms.
     * @param args Feld mit beliebig vielen Zeichenketten.
     */
    public static void main(String[] args) {
        for (int i = 0; i < args.length; ++i) {
            System.out.printf("%d: %s%n", i, args[i]);
        }
    }
}

