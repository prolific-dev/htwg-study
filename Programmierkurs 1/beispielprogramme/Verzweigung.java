package beispielprogramme;

import java.util.Scanner;

public final class Verzweigung {
    private Verzweigung() {}

    private static final Scanner EINGABE = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Zwei Zahlen eingeben: ");
        int m = EINGABE.nextInt();
        int n = EINGABE.nextInt();

        if (m == n) {
            System.out.println("Beide Zahlen sind gleich!");
        } else if (m > n) {
            System.out.printf("Maximum: %d%n", m);
        } else {
            System.out.printf("Maximum: %d%n", n);
        }
    }
}
