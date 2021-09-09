package beispielprogramme;

public final class OverloadingAmbiguous {
    private OverloadingAmbiguous() {}

    private static double max(int a, double b) {
        return a > b ? a : b;
    }

    private static double max (double a, int b) {
        return a > b ? a : b;
    }

    public static void main(String[] args) {
        System.out.println(max(1, (double) 2));
        System.out.println(max((double) 1, 2));
    }
}
