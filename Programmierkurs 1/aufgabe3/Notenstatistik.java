// Notenstatistik.java
package aufgabe3;

import java.util.Locale;
import java.util.Scanner;

/**
 * erstellt eine Notenstatistik.
 * <p>
 * Das Programm erwartet Pr&uuml;fungsnoten im Format
 * <code>Ganze,Zehntel</code> oder <code>Ganze.Zehntel</code>,
 * wobei <code>Ganze</code> und <code>Zehntel</code> nur aus
 * je einer Dezimalziffer bestehen d&uuml;rfen.
 * Andere Eingaben werden wegen Formatfehler ignoriert.
 * </p>
 * <p>
 * Das Programm gibt die folgende Statistik aus:
 * </p>
 * <ul>
 * <li>die Anzahl der ber&uuml;cksichtigten Noten</li>
 * <li>die Anzahl der Bestandenen</li>
 * <li>die beste Note</li>
 * <li>die schlechteste Note</li>
 * <li>den Durchschnitt der Bestandenen</li>
 * <li>die Durchfallquote in Prozent</li>
 * </ul>
 * <p>
 * Es werden in der Statistik nur die nach HTWG-Pr&uuml;fungsordnung
 * zul&auml;ssigen Noten (1,0 1,3 1,7 2,0 2,3 2,7 3,0 3,3 3,7 4,0 5,0)
 * ber&uuml;cksichtigt.
 * Andere Eingaben werden wegen falscher Vorkommastelle oder
 * falscher Nachkommastelle ignoriert.
 * Alle Noten bis 4,0 gelten als bestanden, nur die 5,0 als durchgefallen.
 * </p>
 *
 * @author Dennis Agostinho da Silva
 * @version 29.11.2018
 */
public final class Notenstatistik {
    private Notenstatistik() { }

    private static final Scanner EINGABE = new Scanner(System.in);

    /**
     * main ist der Startpunkt des Programms.
     * @param args wird nicht verwendet.
     */
    public static void main(final String[] args) {
        Locale.setDefault(Locale.GERMANY);

        System.out.println("Noten im Format Ganze,Zehntel "
                           + "oder Ganze.Zehntel eingeben (Ende mit Strg-D):");

        String[] htwg = new String[22];

        htwg[0] = "1.0";
        htwg[1] = "1.3";
        htwg[2] = "1.7";
        htwg[3] = "2.0";
        htwg[4] = "2.3";
        htwg[5] = "2.7";
        htwg[6] = "3.0";
        htwg[7] = "3.3";
        htwg[8] = "3.7";
        htwg[9] = "4.0";
        htwg[10] = "5.0";
        htwg[11] = "1,0";
        htwg[12] = "1,3";
        htwg[13] = "1,7";
        htwg[14] = "2,0";
        htwg[15] = "2,3";
        htwg[16] = "2,7";
        htwg[17] = "3,0";
        htwg[18] = "3,3";
        htwg[19] = "3,7";
        htwg[20] = "4,0";
        htwg[21] = "5,0";

        int countmatch = 0;
        int bernote = 0;
        int besnote = 0;
        double notedouble = 0.0;
        double sumbest = 0.0;
        double best = 0.0;
        double worst = 0.0;

        while (EINGABE.hasNext()) {
            String note = EINGABE.next();

            if (note.length() == 3 && Character.isDigit(note.charAt(0))
                    && Character.isDigit(note.charAt(2))
                    && (note.charAt(1) == ',' || note.charAt(1) == '.')) {

                int vor = Character.getNumericValue(note.charAt(0));
                int nach = Character.getNumericValue(note.charAt(2));
                char vorchar = note.charAt(1);
                notedouble = (double) vor + ((double) nach / 10);

                if (vor == 0) {

                    System.out.printf("Note %s wird wegen Vorkommastelle %d"
                            + " ignoriert!%n", note, vor);

                } else if (vor == 6) {

                    System.out.printf("Note %s wird wegen Vorkommastelle %d "
                            + "ignoriert!%n", note, vor);

                } else if ((vor == 6 || vor == 7 || vor == 8 || vor == 9)
                        && (nach ==  3 || nach == 7)) {

                    System.out.printf("Note %s wird wegen Vorkommastelle %d "
                            + "ignoriert!%n", note, vor);

                } else if ((vor == 1 || vor == 2 || vor == 3)
                        && (nach == 1 || nach == 2 || nach == 4 || nach == 5
                        || nach == 6 || nach == 8 || nach == 9)) {

                    System.out.printf("Note %s wird wegen Nachkommastelle %d "
                            + "ignoriert!%n", note, nach);

                } else if ((vor == 6 || vor == 7 || vor == 8 || vor == 9)
                        && (nach == 0)) {

                    System.out.printf("Note %s wird wegen Vorkommastelle %d "
                            + "ignoriert!%n", note, vor);

                } else if ((vor == 4 || vor == 5)
                        && (nach == 1 || nach == 2 || nach == 3 || nach == 4
                        || nach == 5 || nach == 6 || nach == 7 || nach == 8
                        || nach == 9)) {

                    System.out.printf("Note %s wird wegen Nachkommastelle %d "
                            + "ignoriert!%n", note, nach);

                } else {

                    bernote += 1;

                    if (notedouble <= 4.0) {

                        besnote += 1;
                        sumbest = sumbest + notedouble;
                    }
                }
            } else {

                for (int i = 0; i < htwg.length; ++i) {
                    boolean match = htwg[i].contains(note);

                    if (match) {

                        countmatch++;
                    }
                }

                if (countmatch != 1) {

                    System.out.printf("Note %s wird wegen Formatfehler "
                            + "ignoriert!%n", note);
                }
            }

            if (best != 0.0) {
                if (best > notedouble) {
                    best = notedouble;
                }
            } else {
                best = notedouble;
            }

            if (worst < notedouble) {
                worst = notedouble;
            } else {
                worst = 5.0;
            }

        } // while

        double danzber = bernote;
        double danzbes = besnote;
        double durchbest = sumbest / besnote;
        double durchfall = 0.0;
        durchfall = (1.0 - (danzbes / danzber)) * 100;

        System.out.printf("%nAnzahl beruecksichtigter Noten: %d%n", bernote);
        System.out.printf("Anzahl Bestandene: %d%n", besnote);

        if (bernote != 0) {

            System.out.printf("Beste Note: %.1f%n", best);
            System.out.printf("Schlechteste Note: %.1f%n", worst);
            System.out.printf("Durchschnitt Bestandene: %.1f%n", durchbest);
            System.out.printf("Durchfallquote: %.1f%%%n", durchfall);

        }
    } // main
}

