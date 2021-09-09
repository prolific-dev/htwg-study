// DatumMitZeit.java
package beispielprogramme.equals.variante2;

/**
 * DatumMitZeit ist ein Bauplan f&uuml;r Datums-mit-Zeit-Objekte.
 * Beispielprogramm zur Programmiertechnik 1, Teil 5.
 * DatumMitZeit als Unterklasse (nicht nachahmenswert).
 * @author H.Drachenfels
 * @version 9.12.2019
 */
public class DatumMitZeit extends Datum {
    private final int stunde; // [0,23]
    private final int minute; // [0,59]

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

    protected DatumMitZeit(int tag, int monat, int jahr,
                           int stunde, int minute) {
        super(tag, monat, jahr);

        if (stunde < 0 || stunde > 23 || minute < 0 || minute > 59) {
            throw new IllegalArgumentException("falsche Zeit");
        }

        this.stunde = stunde;
        this.minute = minute;
    }

    @Override
    public String toString() {
        return String.format("%sT%02d:%02d",
                             super.toString(), this.stunde, this.minute);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof DatumMitZeit) {
        // Variante 3: if (o != null && this.getClass() == o.getClass()) {
            DatumMitZeit d = (DatumMitZeit) o;
            return super.equals(o)
                   && this.stunde == d.stunde
                   && this.minute == d.minute;
        }

        // Variante 1 und 3: return false;
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode() ^ (this.stunde << 6) + this.minute;
    }
}

