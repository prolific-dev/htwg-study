package beispielprogramme;

import java.util.Scanner;

public final class DoSchleife {
    private DoSchleife() {}

    private static final Scanner EINGABE = new Scanner(System.in);

    public static void main(String[] args) {
        int n = 0;

        do {
            System.out.println("Zahl zwischen 0 und 255 eingeben: ");
            n = EINGABE.nextInt();
        } while (n < 0 || n > 255);

        System.out.println("       ");

        do {
            System.out.printf("%d\b\b", n % 2);
            n /= 2;
        } while (n > 0);

        System.out.println();
    }
}
