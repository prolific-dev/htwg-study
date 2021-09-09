package beispielprogramme;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public final class Ausnahmebehandlung {
    private Ausnahmebehandlung() {}

    private static final Scanner EINGABE = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Monat eingeben [1-12]: ");

        try {
            int month = EINGABE.nextInt();
            if (month < 1 || month > 12) {
                throw new Exception("Fehler: kein Monat");
            }

            switch (month) {
                case 2:
                    System.out.println("28 oder 29 Tage");
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    System.out.println("30 Tage");
                    break;
                default:
                    System.out.println("31 Tage");
            }
        } catch (InputMismatchException x) {
            System.err.println("Fehler: keine Zahl");
        } catch (NoSuchElementException x) {
            System.err.println("Fehler: keine Eingabe");
        } catch (Exception x) {
            System.err.println(x.getMessage());
        } finally {
            EINGABE.close();
        }
    }
}
