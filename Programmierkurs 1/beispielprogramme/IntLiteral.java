package beispielprogramme;

public final class IntLiteral {
    private IntLiteral() { }

    public static void main(String[] args) {

        System.out.println(12);
        System.out.println(012);
        System.out.println(0x12);
        System.out.printf("%x%n", 12);
        System.out.printf("%d%n", 012);
        System.out.printf("%o%n", 12);
    }

}
