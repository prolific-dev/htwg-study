// DatumTest.java

package beispielprogramme.datum;

import java.util.Scanner;

public final class DatumTest {
    private DatumTest() { }

    private static final Scanner IN = new Scanner(System.in);

    /**
     * main ist der Startpunkt des Programms.
     * @param args wird nicht verwendet.
     */
    public static void main(String[] args) {
        System.out.println("Welches Datum ist heute? (Tag Monat Jahr) ");
        Datum d = Datum.valueOf(IN.nextInt(), IN.nextInt(), IN.nextInt());

        Datum heute = Datum.heute();
        if (d.equals(heute)) {
            System.out.printf("Richtig, %s ist das heutige Datum!", d);
        } else {
            System.out.printf("Falsch, %s ist das heutige Datum, nicht %s!",
                              heute, d);
        }
    }
}

