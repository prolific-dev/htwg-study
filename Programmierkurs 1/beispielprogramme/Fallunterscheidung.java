package beispielprogramme;

import java.util.Scanner;

public final class Fallunterscheidung {
    private Fallunterscheidung() {}

    private static final Scanner EINGABE = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Monat eingeben [1-12]: ");
        int month = EINGABE.hasNextInt() ? EINGABE.nextInt() : 0;

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
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                System.out.println("31 Tage");
                break;
            default:
                System.err.println("Eingabefehler!");
        }
    }
}
