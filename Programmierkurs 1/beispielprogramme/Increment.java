package beispielprogramme;

public final class Increment {
    private Increment() {}

    public static void main(String[] args) {
        int i = 1;
        System.out.println(++i);
        System.out.println(i++);
        System.out.println(i);

        i = 1;
        System.out.println(i = i + 1);
        System.out.println(i); i = i + 1;
        System.out.println(i);

        double d = 1.2;
        System.out.println(++d);

        char c = 'a';
        System.out.println(++c);
    }
}
