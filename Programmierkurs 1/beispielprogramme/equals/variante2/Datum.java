// Datum.java
package beispielprogramme.equals.variante2;

/**
 * Datum ist ein Bauplan f&uuml;r Datumsobjekte.
 * Beispielprogramm zur Programmiertechnik 1, Teil 5.
 * Als Oberklasse verwendbar (nicht nachahmenswert).
 * @author H.Drachenfels
 * @version 9.12.2019
 */
public class Datum {
    private final int tag;
    private final int monat;
    private final int jahr;

    /**
     * Fabrikmethode, die ein Objekt mit den angegebenen Werten liefert.
     * @param tag ist der Tag im Monat als Zahl zwischen 1 und 31
     * @param monat ist der Monat im Jahr als Zahl zwischen 1 und 12
     * @param jahr ist das Jahr
     * @return Referenz auf das Objekt
     */
    public static Datum valueOf(int tag, int monat, int jahr) {
        return new Datum(tag, monat, jahr);
    }

    protected Datum(int tag, int monat, int jahr) {
        // Datum pruefen
        int maxTag;
        switch (monat) {
        case 2:
            if (jahr % 4 == 0 && (jahr % 100 != 0 || jahr % 400 == 0)) {
                maxTag = 29; // Schaltjahr
            } else {
                maxTag = 28; // kein Schaltjahr
            }
            break;
        case 4:
        case 6:
        case 9:
        case 11:
            maxTag = 30;
            break;
        default:
            maxTag = 31;
            break;
        }

        if (tag < 1 || tag > maxTag || monat < 1 || monat > 12) {
            throw new IllegalArgumentException("ungueltiges Datum");
        }

        this.tag = tag;
        this.monat = monat;
        this.jahr = jahr;
    }

    @Override
    public String toString() {
        return String.format("%04d-%02d-%02d", this.jahr, this.monat, this.tag);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Datum) {
        // Variante 3: if (o != null && this.getClass() == o.getClass()) {
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

