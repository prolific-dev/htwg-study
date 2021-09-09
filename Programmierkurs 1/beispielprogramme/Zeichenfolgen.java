package beispielprogramme;

public final class Zeichenfolgen {
    private Zeichenfolgen() {}

    public static void main(String[] args) {
        CharSequence[] beispiele = {"Hallo", new StringBuilder("Hi"), LeereZeichenfolge.VALUE};

        for (CharSequence cs : beispiele) {
            System.out.println(cs.length());
            if (cs.length() > 0) {
                System.out.printf("%c%s%n", cs.charAt(0), cs.subSequence(1, cs.length()));
            }
        }
    }
}

final class LeereZeichenfolge implements CharSequence {
    public static final LeereZeichenfolge VALUE = new LeereZeichenfolge();

    private LeereZeichenfolge() {}

    @Override
    public String toString() {
        return "";
    }

    @Override
    public int length() {
        return 0;
    }

    @Override
    public char charAt(int index) {
        throw new IndexOutOfBoundsException();
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        if (start != 0 || end != 0) {
            throw new IndexOutOfBoundsException();
        }

        return this;
    }
}
