// UnsignedHexFormat.java
package beispielprogramme.format;

public final class UnsignedHexFormat implements Formatter {
    /**
     * format formatiert ganze Zahlen hexadezimal ohne Vorzeichen.
     * @param n eine ganze Zahl.
     * @return ein String mit der hexadezimalen Darstellung von n.
     */
    @Override
    public String format(int n) {
        return Integer.toHexString(n);
    }
}

