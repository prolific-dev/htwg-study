package beispielprogramme;

import java.util.Scanner;

public final class VerzweigungOperator {
    private VerzweigungOperator() {}

    private static final Scanner EINGABE = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Zwei Zahlen eingeben: ");
        int m = EINGABE.nextInt();
        int n = EINGABE.nextInt();

        if (m == n) {
            System.out.println("Beide Zahlen sind gleich!");
        } else {
            System.out.printf("Maximum: %d%n", m > n ? m : n);
        }
    }
}
