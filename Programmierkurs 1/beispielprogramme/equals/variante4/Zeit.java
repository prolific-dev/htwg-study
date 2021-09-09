// Zeit.java
package beispielprogramme.equals.variante4;

/**
 * Zeit ist ein Bauplan f&uuml;r Zeit-Objekte.
 * Beispielprogramm zur Programmiertechnik 1, Teil 5.
 * @author H.Drachenfels
 * @version 9.12.2019
 */
public final class Zeit {
    private final int stunde; // [0,23]
    private final int minute; // [0,59]

    /**
     * Fabrikmethode, die ein Objekt mit den angegebenen Werten liefert.
     * @param stunde ist die Stunde des Tages als Zahl zwischen 0 und 23
     * @param minute ist die Minute der Stunde als Zahl zwischen 0 und 59
     * @return Referenz auf das Objekt
     */
    public static Zeit valueOf(int stunde, int minute) {
        return new Zeit(stunde, minute);
    }

    private Zeit(int stunde, int minute) {
        if (stunde < 0 || stunde > 23 || minute < 0 || minute > 59) {
            throw new IllegalArgumentException("falsche Zeit");
        }

        this.stunde = stunde;
        this.minute = minute;
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d", this.stunde, this.minute);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Zeit) {
            Zeit z = (Zeit) o;
            return this.stunde == z.stunde && this.minute == z.minute;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return (this.stunde << 6) + this.minute;
    }
}

