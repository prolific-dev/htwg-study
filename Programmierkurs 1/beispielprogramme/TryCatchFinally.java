package beispielprogramme;

import java.util.Scanner;

public final class TryCatchFinally {
    private TryCatchFinally() {}

    private static final Scanner EINGABE = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            System.out.print("Eingabe: ");
            int n = EINGABE.nextInt();
            if (n >= 0) {
                System.out.println(1);
            } else {
                System.out.println(2);
                return;
            }
        } catch (Exception x) {
            if (EINGABE.hasNext()) {
                System.out.println(3);
            } else {
                System.out.println(4);
                return;
            }
        } finally {
            System.out.println(5);
        }

        System.out.println(6);
    }
}
