// EqualsTest.java
package beispielprogramme.equals;
import beispielprogramme.equals.variante1.Datum;
import beispielprogramme.equals.variante1.DatumMitZeit;

/**
 * EqualsTest testet equals-Implementierungen auf Reflexivit&auml;t,
 * Symmetrie und Transitivit&auml;t.
 * Beispielprogramm zur Programmiertechnik 1, Teil 5.
 * Testet die Implementierungsvarianten 1, 2, 3 und 4 von equals
 * in den Klassen Datum und DatumMitZeit.
 * @author H.Drachenfels
 * @version 15.6.2020
 */
public final class EqualsTest {
    private EqualsTest() { }

    /**
     * main ist der Startpunkt des Programms.
     * @param args wird nicht verwendet.
     */
    public static void main(String[] args) {
        Object[][] testDaten = {
            {
                Datum.valueOf(16, 6, 2020),
                Datum.valueOf(16, 6, 2020),
                Datum.valueOf(16, 6, 2020),
            },
            {
                Datum.valueOf(16, 6, 2020),
                Datum.valueOf(16, 6, 2020),
                Datum.valueOf(17, 6, 2020)
            },
            {
                DatumMitZeit.valueOf(16, 6, 2020, 9, 45),
                DatumMitZeit.valueOf(16, 6, 2020, 9, 45),
                DatumMitZeit.valueOf(16, 6, 2020, 9, 45),
            },
            {
                DatumMitZeit.valueOf(16, 6, 2020, 9, 45),
                DatumMitZeit.valueOf(16, 6, 2020, 9, 45),
                DatumMitZeit.valueOf(16, 6, 2020, 10, 15)
            },
            {
                DatumMitZeit.valueOf(16, 6, 2020, 9, 45),
                Datum.valueOf(16, 6, 2020),
                DatumMitZeit.valueOf(16, 6, 2020, 10, 15)
            }
        };

        for (Object[] testFall : testDaten) {
            System.out.printf("Testfall %s%n",
                              java.util.Arrays.toString(testFall));

            // reflexiv?
            for (Object o : testFall) {
                if (!o.equals(o)) {
                    throw new AssertionError("equals nicht reflexiv: " + o);
                }
            }

            System.out.println("equals in diesem Testafall reflexiv");

            // symmetrisch?
            for (int i = 0; i < testFall.length - 1; ++i) {
                Object a = testFall[i];
                Object b = testFall[i + 1];
                if (a.equals(b) != b.equals(a)) {
                    // kann bei equals.variante1 passieren
                    throw new AssertionError("equals nicht symmetrisch: "
                                             + a + " " + b);
                }
            }

            System.out.println("equals in diesem Testfall symmetrisch");

            // hashCode kompatible mit equals?
            for (int i = 0; i < testFall.length - 1; ++i) {
                Object a = testFall[i];
                Object b = testFall[i + 1];
                if (a.equals(b) && a.hashCode() != b.hashCode()) {
                    // kann bei equals.variante1 und equals.variante2 passieren
                    System.out.println(
                        "Fehler: equals true, aber Hashcodes verschieden: "
                        + a + " " + b);
                }
            }

            // transitiv?
            Object a = testFall[0];
            Object b = testFall[1];
            Object c = testFall[2];
            if (a.equals(b) && b.equals(c)) {
                if (!a.equals(c)) {
                    // kann bei equals.variante2 passieren
                    throw new AssertionError("equals nicht transistiv: "
                                             + a + " " + b + " " + c);
                }

                System.out.println("equals in diesem Testfall transitiv");
            } else {
                System.out.println("transitiv nicht testbar");
            }

            System.out.println();
        }

        /*
         * Nur die Varianten 3 und 4 setzen die Spezifikation von
         * equals und hashCode richtig um.
         *
         * Variante 3 verletzt allerdings das Substitutionsprinzip der
         * Objektorientierung. Ein Oberklasse-Objekt (hier Datum) soll
         * danach ohne Funktionalitaetsverlust durch ein Unterklasse-Objekt
         * (hier DatumMitZeit) ersetzt werden koennen. Bei Variante 3 geht
         * dabei aber die Vergleichbarkeit mit equals verloren.
         *
         * Konsequenzen:
         * equals und hashCode nur in final oder abstract markierten Klassen
         * ueberschreiben, die keine (abgesehen von Object) oder nur abstrakte
         * Oberklassen haben.
         */
    }
}

