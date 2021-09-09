package beispielprogramme;

public final class ForSchleife {
    private ForSchleife() {}

    public static void main(String[] args) {
        double[] anArray = {3.625, 3.648, 3.853, 4.042};

        for (int i = 0; i < anArray.length; ++i) {
                System.out.println(anArray[i]);
        }

        // das gleiche mit for-each-Schleife
        for (double n : anArray) {
                System.out.println(n);
        }
    }
}
