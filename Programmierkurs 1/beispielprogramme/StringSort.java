package beispielprogramme;

public final class StringSort {
    private StringSort() {}

    public static void main(String[] args) {
        java.util.Arrays.sort(args);

        for (String s : args) {
            System.out.println(s);
        }
    }
}
