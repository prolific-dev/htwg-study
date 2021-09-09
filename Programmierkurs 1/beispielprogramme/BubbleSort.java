package beispielprogramme;

import java.util.Scanner;

public final class BubbleSort {
    private BubbleSort() {}

    private static final Scanner EINGABE = new Scanner(System.in);

    public static void main(String[] args) {
        int n;
        System.out.print("Anzahl zu sortierender Werte eingeben: ");
        n = EINGABE.nextInt();

        int[] a = new int[n];
        System.out.printf("%d ganze Zahlen eingeben: ", n);

        for (int i = 0; i < a.length; i++) {
            a[i] = EINGABE.nextInt();
        }

        for (int i = a.length; i > 1; i--) {
            for (int j = 0; j < i - 1 ; j++) {
                if (a[j] > a[j + 1]) {
                    int tmp = a[j + 1];
                    a[j + 1] = a[j];
                    a[j] = tmp;
                }
            }
        }

        System.out.print("Sortierte Zahlenfolge: ");

        for (int i = 0; i < a.length ; i++) {
            System.out.printf("%d ", a[i]);
        }

        System.out.println();
    }
}
