package beispielprogramme;

public final class PrintInt {
    private PrintInt() {}

    public static void main(String[] args) {
        System.out.printf("%10d%n", 1);
        System.out.printf("%10d%n", 123);
        System.out.printf("%10d%n", 1234567890);


        {
            int n = 1;
            System.out.printf("%10d%n", n);
        }
        {
            int n = 123;
            System.out.printf("%10d%n", n);
        }
        {
            int n =1234567890;
            System.out.printf("%10d%n", n);
        }

        printInt(1);
        printInt(123);
        printInt(1234567890);
    }

    private static void printInt(int n) {
        System.out.printf("%10d%n", n);
    }

}
