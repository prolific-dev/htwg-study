// TerminTest.java
package beispielprogramme.termin;
import java.util.Collection;
import java.util.LinkedList;
import beispielprogramme.datum.*;

/**
 * TerminTest ist eine Main-Klasse, die Terminentit&auml;ten verwendet.
 * Beispielprogramm zur Programmiertechnik 1, Teil 4.
 * @author H.Drachenfels
 * @version 4.8.2016
 */
public final class TerminTest {
    private TerminTest() { }

    /**
     * main ist der Startpunkt des Programms.
     * @param args wird nicht verwendet.
     */
    public static void main(String[] args) {
        Collection<Termin> prueferKalender = new LinkedList<Termin>();
        Collection<Termin> kandidatenKalender = new LinkedList<Termin>();

        Termin pruefung = new Termin(Datum.heute(),
                                     "Pruefung Programmiertechnik 1");
        prueferKalender.add(pruefung);
        kandidatenKalender.add(pruefung);

        pruefung.verschieben(Datum.valueOf(1, 4, 2040));

        for (Termin t: prueferKalender) {
            System.out.printf("Pruefer: %s, %s%n",
                              t.getDatum(), t.getBeschreibung());
        }

        for (Termin t: kandidatenKalender) {
            System.out.printf("Kandidat: %s, %s%n",
                              t.getDatum(), t.getBeschreibung());
        }
    }
}

