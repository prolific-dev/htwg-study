package beispielprogramme;

import java.util.Scanner;

public final class DoSchleifeWhile {

    private static final Scanner EINGABE = new Scanner(System.in);

    public static void main(String[] args) {
        int n = 0;
        System.out.println("Zahl zwischen 0 und 255 eingeben: ");
        n = EINGABE.nextInt();
        while (n < 0 || n > 255) {
            System.out.println("Zahl zwischen 0 und 255 eingeben!");
            n = EINGABE.nextInt();
        }

        System.out.println("       ");

        System.out.printf("%d\b\b", n % 2);
        n /= 2;

        while (n > 0) {
            System.out.printf("%d\b\b", n % 2);
            n /= 2;
        }

        System.out.println();
    }
}
