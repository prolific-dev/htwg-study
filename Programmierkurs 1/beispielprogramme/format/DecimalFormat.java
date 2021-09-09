// DecimalFormat.java
package beispielprogramme.format;


public final class DecimalFormat implements Formatter {
    /**
     * format formatiert ganze Zahlen dezimal.
     * @param n eine ganze Zahl.
     * @return ein String mit der dezimalen Darstellung von n.
     */
    @Override
    public String format(int n) {
        return Integer.toString(n);
    }
}

