// RadixFormat.java
package beispielprogramme.format;


public final class RadixFormat implements Formatter {
    private final int radix;

    /**
     * Konstruktor legt die Basis f&uuml;r die Zahldarstellung fest.
     * @param r die Basis zwischen Character.MIN_RADIX und Character.MAX_RADIX.
     */
    public RadixFormat(int r) {
        if (r < Character.MIN_RADIX || r > Character.MAX_RADIX) {
            throw new IllegalArgumentException("invalid radix");
        }

        this.radix = r;
    }

    /**
     * format formatiert ganze Zahlen zur Basis radix.
     * @param n eine ganze Zahl.
     * @return ein String mit der Darstellung zu Basis radix von n.
     */
    @Override
    public String format(int n) {
        return Integer.toString(n, this.radix);
    }
}

