// Histogramm.java
package aufgabe2;

import java.util.Scanner;

/**
 * Histogramm liest ganze Zahlen zwischen 1 und 6 ein und
 * gibt deren H&auml;ufigkeitsverteilung als Histogramm aus.
 * @author Dennis Agostinho da Silva
 * @version 30. Oktober 2018
 */
public final class Histogramm {
    private Histogramm() { }

    private static final Scanner EINGABE = new Scanner(System.in);

    /**
     * main ist der Startpunkt des Programms.
     * @param args wird nicht verwendet.
     */
    public static void main(final String[] args) {

        /*(1) hier ein Feld von Zaehlern definieren */
        final int zahl = 6;
        int[] zahlen = new int[zahl];
        for (int z = 0; z <= zahl - 1; z++) {
            zahlen[z] = 0;
        }



        //-------------------------------------------------- Zahlen einlesen
        System.out.println("Ganze Zahlen zwischen 1 und 6 eingeben "
                           + "(Ende mit Strg-D):");

        while (EINGABE.hasNext()) {
            int number = EINGABE.nextInt();

            /*(2) hier Anweisungen fuer das
                         Pruefen und Zaehlen der Eingabe schreiben */
            if (number >= 1 && number <= zahl) {

                zahlen[number - 1] += 1;
            } else    {

                System.out.printf(
                    "Falsche Eingabe wird ignoriert: %d%n", number);

            }
        }

        //---------------------------------------------- Histogramm ausgeben

        /*(3) hier Anweisungen fuer die Histogrammausgabe schreiben */
        System.out.println("Histogramm:");
        for (int i = 0; i <= zahl - 1; i++) {

            for (int a = 1; a < zahlen[i] + 1; a++) {

                if (a % (zahl - 1) == 0) {
                    System.out.printf("$");
                } else {
                    System.out.printf("%d", i + 1);
                }

            }

            System.out.printf("(%d)%n", zahlen[i]);




        }
    }

}


