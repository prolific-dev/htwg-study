package beispielprogramme;

public final class Scopes {
    private Scopes() {}

    private static int i = 1;

    static {
        System.out.println(i);
    }

    public static void main(String[] args) {
        int i = 2;

        for (int ii = 3; ii <= 3; ++ii) {

            {
                int iii = 4;
                System.out.printf("%d %d %d %d%n", iii, ii, i, Scopes.i);
            }

            System.out.printf("%d %d %d%n", ii, i, Scopes.i);
        }

        System.out.printf("%d %d%n", i, Scopes.i);

        m(5);
        m();
    }

    private static void m(int i) {
        System.out.printf("%d %d%n", i, Scopes.i);
    }

    private static void m() {
        System.out.println(i);
    }
}
