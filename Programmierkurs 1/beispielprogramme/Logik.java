package beispielprogramme;

public final class Logik {
    private Logik() {}

        public static void main(String[] args) {
            int a = 0;
            int b = 3;
            System.out.printf("a = %d%nb = %d%n", a, b);

            System.out.print("a < b | ++a > 0 = ");
            System.out.println(a < b | ++a > 0);
            System.out.printf("a = %d%nb = %d%n", a, b);

            System.out.print("a < b || ++a > 0 = ");
            System.out.println(a < b || ++a > 0);
            System.out.printf("a = %d%nb = %d%n", a, b);

            System.out.print("a > b & ++a > 0 = ");
            System.out.println(a > b & ++a > 0);
            System.out.printf("a = %d%nb = %d%n", a, b);

            System.out.print("a > b && ++a > 0 = ");
            System.out.println(a > b && ++a > 0);
            System.out.printf("a = %d%nb = %d%n", a, b);
    }
}

