// IntegerMethods.java

package beispielprogramme.maximum;

/**
 * Integers enth&auml;lt Methoden f&uuml;r den Umgang mit ganzen Zahlen.
 * Beispielprogramm zur Programmiertechnik 1, Teil 4.
 * @author H.Drachenfels
 * @version 17.2.2011
 */
public final class IntegerMethods {
    /** Es soll keine Instanzen der Klasse geben. */
    private IntegerMethods() { }

    /**
     * max bildet das Maximum zweier ganzer Zahlen.
     * @param m ist die eine ganze Zahl.
     * @param n ist die andere ganze Zahl.
     * @return das Maximum der beiden Argumente.
     */
    public static int max(int m, int n) {
        return m > n ? m : n;
    }

    /**
     * min bildet das Minimum zweier ganzer Zahlen.
     * @param m ist die eine ganze Zahl.
     * @param n ist die andere ganze Zahl.
     * @return das Minimum der beiden Argumente.
     */
    public static int min(int m, int n) {
        return m < n ? m : n;
    }
}

