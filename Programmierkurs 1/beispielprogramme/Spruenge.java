package beispielprogramme;

import java.util.Scanner;

public final class Spruenge {
    private Spruenge() {}

    private static final Scanner EINGABE = new Scanner(System.in);

    public static void main(String[] args) {
        int sum = 0;

        System.out.println("Ganze Zahlen eingeben (Ende mit Strg-D oder =):");

        while (EINGABE.hasNext()) {
            if (!EINGABE.hasNextInt()) {
                String s = EINGABE.next();
                if (s.equals("=")) {
                    break;
                }

                System.err.printf("Folgende Eingabe wird ignoriert: %s%n", s);
                continue;
            }

            int n = EINGABE.nextInt();

            sum += n;
        }

        System.out.printf("Summe: %d%n", sum);
        return;
    }
}
