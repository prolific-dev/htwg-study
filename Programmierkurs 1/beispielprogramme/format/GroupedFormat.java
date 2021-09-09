// GroupedFormat.java
package beispielprogramme.format;

import java.text.NumberFormat;


public final class GroupedFormat implements Formatter {
    private static final NumberFormat NF;

    static {
        NF = NumberFormat.getIntegerInstance();
        NF.setGroupingUsed(true);
    }

    /**
     * format formatiert ganze Zahlen dezimal mit Tausenderpunktierung.
     * @param n eine ganze Zahl.
     * @return eine String mit der dezimalen Darstellung von n.
     */
    @Override
    public String format(int n) {
        return NF.format(n);
    }
}

