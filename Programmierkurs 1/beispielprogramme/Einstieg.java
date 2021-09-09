// Einstieg.java

package beispielprogramme;

import java.util.Scanner;

public final class Einstieg {
    private Einstieg() { }

    static {
        try {
            System.out.print("Einstieg.class wird geladen ");

            for (int i = 0; i < 10; ++i) {
                System.out.print('.');
                System.out.flush();
                Thread.sleep(1000);
            }

            System.out.println();
        } catch (InterruptedException x) {
            System.out.println(" Abbruch!");
            System.exit(1);
        }
    }

    private static final Scanner EINGABE = new Scanner(System.in);

    /**
     * main ist der Startpunkt des Programms.
     * main verwendet die Methoden print und printf zum ausgeben von Text
     * sowie die Methoden next und nextInt zum einlesen von Text
     * und einer ganzen Zahl.
     * @param args wird nicht verwendet
     */
    public static void main(String[] args) {
        System.out.print("Vorname: ");
        String vorname = EINGABE.next();

        System.out.print("Anzahl bisher geschriebener Java-Programme: ");
        int anzahl = EINGABE.nextInt();

        System.out.printf("%ss %d. Java-Programm funktioniert!%n",
                          vorname, anzahl + 1);
    }
}

