package beispielprogramme;

import java.util.Scanner;

public final class WhileSchleife {
    private WhileSchleife() {}

    private static final Scanner EINGABE = new Scanner(System.in);

    public static void main(String[] args) {
        int sum = 0;

        System.out.println("Ganze Zahlen eingeben (Ende mit Strg-Z):");

        while (EINGABE.hasNextInt()) {
            sum += EINGABE.nextInt();
        }

        System.out.printf("Summe: %d%n", sum);
    }
}
