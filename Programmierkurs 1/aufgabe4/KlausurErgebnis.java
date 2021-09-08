// KlausurErgebnis.java
package aufgabe4;

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
 * @version 11.12.2018
 */
public final class KlausurErgebnis {
    private KlausurErgebnis() { }

    private static final Scanner EINGABE = new Scanner(System.in);

    /**
     * main ist der Startpunkt des Programms.
     * @param args wird nicht verwendet.
     */
    public static void main(final String[] args) {
        Locale.setDefault(Locale.GERMANY);

        //--------------------------------------------------- Noten einlesen
        System.out.println("Noten im Format Ganze,Zehntel "
                           + "oder Ganze.Zehntel eingeben (Ende mit Strg-D):");

        int countmatch = 0;
        int bernote = 0;
        int besnote = 0;
        double notedouble = 0.0;
        double sumbest = 0.0;
        double best = Noten.SCHLECHTESTE;
        double worst = Noten.BESTE;


        while (EINGABE.hasNext()) {
            String note = EINGABE.next();

            //Eingabe pruefen und Note erfassen
            if (Noten.istZulaessig(note)) {
                bernote += 1;
                notedouble = Noten.toDouble(note);
                best = Noten.bessere(best, notedouble);
                worst = Noten.schlechtere(worst, notedouble);
                if (Noten.istBestanden(notedouble)) {
                    besnote += 1;
                    sumbest += notedouble;

                }
            } else {
                System.out.printf("Unzulaessige Note %s wird ignoriert!%n",
                                  note);
            }

        } // while

        //------------------------------------------ Notenstatistik ausgeben

        /*Durchschnitt und Durchfallquote berechnen
                     und dann die gesamte Statistik ausgeben ... */
        double danzber = bernote;
        double danzbes = besnote;
        double durchbest = sumbest / besnote;
        double durchfall = 0.0;
        final double hunnid = 100.0;
        durchfall = (1.0 - (danzbes / danzber)) * hunnid;

        System.out.printf("%nAnzahl beruecksichtigter Noten: %d%n", bernote);
        System.out.printf("Anzahl Bestandene: %d%n", besnote);

        if (bernote != 0) {

            System.out.printf("Beste Note: %s%n", Noten.toString(best));
            System.out.printf("Schlechteste Note: %s%n", Noten.toString(worst));
            System.out.printf("Durchschnitt Bestandene: %.1f%n", durchbest);
            System.out.printf("Durchfallquote: %.1f%%%n", durchfall);

        }
    } // main
}

