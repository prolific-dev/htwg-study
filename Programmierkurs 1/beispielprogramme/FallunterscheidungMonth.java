package beispielprogramme;

import java.util.NoSuchElementException;
import java.util.Scanner;

public final class FallunterscheidungMonth {
    private FallunterscheidungMonth() {}

    private enum Month{
        JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC
    }

    private static final Scanner EINGABE = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Monat eingeben [ ");
        for (Month m : Month.values()) {
            System.out.printf("%s ", m);
        }
        System.out.print("]: ");

        try {
            Month m = Month.valueOf(EINGABE.next());

            switch (m) {
                case FEB:
                    System.out.println("28 oder 29 Tage");
                    break;
                case APR:
                case JUN:
                case SEP:
                case NOV:
                    System.out.println("30 Tage");
                    break;
                default:
                    System.out.println("31 Tage");
            }
        } catch (NoSuchElementException x) {
            System.err.println("Fehler: = keine Eingabe");
        } catch (IllegalArgumentException x) {
            System.err.println("Fehler: kein Monat");
        }
    }
}
