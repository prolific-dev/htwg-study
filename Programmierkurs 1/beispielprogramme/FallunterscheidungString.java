package beispielprogramme;

import java.util.Scanner;

public final class FallunterscheidungString {
    private FallunterscheidungString() {}

    public static void main(String[] args) {
        System.out.println("Monat eingeben: ");
        String month = new Scanner(System.in).next();

        switch (month) {
            case "Februar":
                System.out.println("28 oder 29 Tage");
            case "April":
            case "Juni":
            case "September":
            case "November":
                System.out.println("30 Tage");
                break;
            case "Januar":
            case "März":
            case "Mai":
            case "Juli":
            case "August":
            case "Oktober":
            case "Dezember":
                System.out.println("31 Tage");
                break;
            default:
                System.err.println("Eingabefehler!");
        }
    }
}
