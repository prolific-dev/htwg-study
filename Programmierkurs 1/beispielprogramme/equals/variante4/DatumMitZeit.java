// DatumMitZeit.java
package beispielprogramme.equals.variante4;

/**
 * DatumMitZeit ist ein Bauplan f&uuml;r Datum-mit-Zeit-Objekte.
 * Beispielprogramm zur Programmiertechnik 1, Teil 5.
 * DatumMitZeit per Objektkomposition statt Vererbung.
 * @author H.Drachenfels
 * @version 9.12.2019
 */
public final class DatumMitZeit { // Varianten 1, 2 und 3: extends Datum
    private final Datum datum;
    private final Zeit zeit;

    /**
     * Fabrikmethode, die ein Objekt mit den angegebenen Werten liefert.
     * @param tag ist der Tag im Monat als Zahl zwischen 1 und 31
     * @param monat ist der Monat im Jahr als Zahl zwischen 1 und 12
     * @param jahr ist das Jahr
     * @param stunde ist die Stunde des Tages als Zahl zwischen 0 und 23
     * @param minute ist die Minute der Stunde als Zahl zwischen 0 und 59
     * @return Referenz auf das Objekt
     */
    public static DatumMitZeit valueOf(int tag, int monat, int jahr,
                                       int stunde, int minute) {
        return new DatumMitZeit(tag, monat, jahr, stunde, minute);
    }

    private DatumMitZeit(int tag, int monat, int jahr,
                         int stunde, int minute) {
        this.datum = Datum.valueOf(tag, monat, jahr);
        this.zeit = Zeit.valueOf(stunde, minute);
    }

    @Override
    public String toString() {
        return String.format("%sT%s", this.datum, this.zeit);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof DatumMitZeit) {
            DatumMitZeit d = (DatumMitZeit) o;
            return this.datum.equals(d.datum)
                   && this.zeit.equals(d.zeit);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return this.datum.hashCode() ^ this.zeit.hashCode();
    }
}

