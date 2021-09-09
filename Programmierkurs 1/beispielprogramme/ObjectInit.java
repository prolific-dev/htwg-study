package beispielprogramme;

public final class ObjectInit {
    private ObjectInit() {
    }

    public static void main(String[] args) {
        Beispiel x = new Beispiel();
        Beispiel y = new Beispiel(4);
    }
}

final class Beispiel {
    public int a;
    public int b = a + 1;
    public int c;
    public int d;

    {
        c = b + 1;
    }

    public Beispiel() {
        // super();
        // a = 0;
        // b = a + 1;
        // c = 0;
        // d = 0;
        // c = b + 1;
        d = c + 1;
    }

    public Beispiel(int i) {
        // super();
        // a = 0;
        // b = a + 1;
        // c = 0;
        // d = 0;
        // c = b + 1;
        a = i;
        d = a + 1;
    }
}

