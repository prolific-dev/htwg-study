package beispielprogramme;

public final class IntWrapper {
    private IntWrapper() {}

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Aufruf: java IntWrapper <Zahl>");
            System.exit(1);
        }

        int zahl = Integer.parseInt(args[0]);

        {
            int i1 = zahl;
            int i2 = zahl * 2;
            int i3 = i1 + i1;
            if (i3 == i2) {
                System.out.printf("%d == %d%n", i3, i2);
            } else {
                throw new RuntimeException(i3 + " != " + i2);
            }
        }

        {
            Integer i1 = zahl;
            Integer i2 = zahl * 2;
            Integer i3 = i1 + i1;
            if (i3 == i2) {
                System.out.printf("%d == %d%n", i3, i2);
            } else {
                throw new RuntimeException(i3 + " != " + i2);
            }
        }
    }
}
