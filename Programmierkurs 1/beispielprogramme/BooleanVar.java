package beispielprogramme;

public final class BooleanVar {
    private BooleanVar() {}

    public static void main(String[] args) {
        boolean aBool = true;

        System.out.printf("%b%n", 1 < 2);
        System.out.printf("%b%n", aBool);
        System.out.printf("%b%n", !aBool);
        System.out.println(aBool && !aBool);
        System.out.println(aBool || !aBool);

        if (aBool) {
            System.out.println("aBool is true");
        } else {
            System.out.println("aBool is false");
        }
    }
}
