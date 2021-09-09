// Datum.java
package beispielprogramme.vererbung;
import java.util.Calendar;



public final class Datum {
    /** Instanzvariable speichert den Tag im Monat. */
    public final int tag;
    /** Instanzvariable speichert den Monat im Jahr. */
    public final int monat;
    /** Instanzvariable speichert das Jahr. */
    public final int jahr;

    private Datum(int tag, int monat, int jahr) {
        this.tag = tag;
        this.monat = monat;
        this.jahr = jahr;
    }

    /**
     * Fabrikmethode, die ein value object mit dem angegebenen Datum liefert.
     * @param tag ist der Tag im Monat
     * @param monat ist der Monat im Jahr
     * @param jahr ist das Jahr
     * @return Referenz auf das value object
     */
    public static Datum valueOf(int tag, int monat, int jahr) {
        // Datum pruefen (stark vereinfacht)
        if (tag < 1 || tag > 31 || monat < 1 || monat > 12) {
            throw new IllegalArgumentException("ungueltiges Datum");
        }

        // value object erzeugen
        return new Datum(tag, monat, jahr);
    }

    /**
     * Fabrikmethode, die ein value object mit dem aktuellen Datum liefert.
     * @return Referenz auf das value object
     */
    public static Datum heute() {
        // Systemkalender ablesen
        Calendar c = Calendar.getInstance();

        // value object erzeugen
        return new Datum(c.get(Calendar.DAY_OF_MONTH),
                         c.get(Calendar.MONTH) + 1,
                         c.get(Calendar.YEAR));
    }

    @Override
    public String toString() {
        return String.format("%d-%02d-%02d", this.jahr, this.monat, this.tag);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Datum) {
            Datum that = (Datum) o;
            return this.tag == that.tag
                   && this.monat == that.monat
                   && this.jahr == that.jahr;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return (this.jahr << 9) + (this.monat << 5) + this.tag;
    }
}

