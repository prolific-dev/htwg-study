package beispielprogramme;

import java.util.Scanner;

public final class IntArithmetik {
    private IntArithmetik() {}

    private static final Scanner EINGABE = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Zwei ganze Zahlen eingeben: ");

        int a = EINGABE.nextInt();
        int b = EINGABE.nextInt();

        System.out.printf("-(%d): %d%n", a, -a);
        System.out.printf("~%d: %d%n", a, ~a);
        System.out.printf("~%d: %d%n", b, ~b);
        System.out.printf("%d + %d: %d%n", a, b, a + b);
        System.out.printf("%d - %d: %d%n", a, b, a - b);
        System.out.printf("%d * %d: %d%n", a, b, a * b);
        System.out.printf("%d / %d: %d%n", a, b, a / b);
        System.out.printf("%d %% %d: %d%n", a, b, a % b);
        System.out.printf("%d / %d: %d%n", b, a, b / a);
        System.out.printf("%d %% %d: %d%n", b, a, b % a);
        System.out.printf("%d & %d: %d%n", a, b, a & b);
        System.out.printf("%d | %d: %d%n", a, b, a | b);
        System.out.printf("%d ^ %d: %d%n", a, b, a ^ b);
        System.out.printf("%d << %d: %d%n", a, b, a << b);
        System.out.printf("%d >> %d: %d%n", a, b, a >> b);
        System.out.printf("%d >>> %d: %d%n", a, b, a >>> b);
        System.out.printf("-(%d) >> %d: %d%n", a, b, -a >> b);
        System.out.printf("-(%d) >>> %d: %d%n", a, b, -a >>> b);
    }
}
