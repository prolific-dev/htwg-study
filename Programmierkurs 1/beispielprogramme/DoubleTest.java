package beispielprogramme;

public final class DoubleTest {
    private DoubleTest() {}

    public static void main(String[] args) {
        double undefiniert = 0.0 / 0.0;
        double unendlich = 1.0 / 0.0;

        System.out.printf("0.0 / 0.0 = %f%n", undefiniert);
        System.out.printf("1.0 / 0.0 = %f%n", unendlich);
        System.out.printf("1.0 / undefiniert = %f%n", 1.0 / undefiniert);
        System.out.printf("1.0 / unendlich = %f%n", 1.0 / unendlich);
    }
}
