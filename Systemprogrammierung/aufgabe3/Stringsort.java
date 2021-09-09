// Stringsort.java

import java.util.Random;

/**
 * Stringsort erzeugt zuf&auml;llige Ziffernstrings
 * und sortiert sie alphabetisch
 * @author H.Drachenfels
 * @version 19.12.2017
 */
public final class Stringsort {
    private Stringsort() { }

    /**
     * main ist der Startpunkt des Programms.
     * @param args wird nicht verwendet.
     */
    public static void main(final String[] args) {
        //---------------------------------------------- Feldgroesse bestimmen
        if (args.length != 1) {
            System.err.println("Aufruf: java Stringsort Anzahl");
            return;
        }

        final int n = Integer.parseInt(args[0]);
        if (n < 1) {
            System.err.println("Anzahl muss midestens 1 sein");
            return;
        }

        //--------------------------------------------------- Strings wuerfeln
        final String[] a = new String[n];
        final Random random = new Random();

        System.out.println("Unsortiertes Feld:");
        for (int i = 0; i < a.length; ++i) {
            int r = random.nextInt(n);
            a[i] = String.valueOf(r);
            System.out.print(a[i] + ' ');
        }

        System.out.println();

        //-------------------------------------------------- Strings sortieren
        for (int i = a.length; i > 1; i--) {
            // groessten Wert nach hinten schieben
            for (int j = 0; j < i - 1; ++j) {
                if (a[j].compareTo(a[j + 1]) > 0) {
                    // Werte tauschen
                    String tmp = a[j + 1];
                    a[j + 1] = a[j];
                    a[j] = tmp;
                }
            }
        }

        //--------------------------------------------------- Strings ausgeben
        System.out.println("Sortiertes Feld:");
        StringBuilder s = new StringBuilder();
        s.append(a[0]);
        for (int i = 1; i < a.length; ++i) {
            if (a[i].equals(a[i - 1])) {
                s.append('*');
            } else {
                s.append(' ');
                s.append(a[i]);
            }
        }

        System.out.println(s);
    }
}

