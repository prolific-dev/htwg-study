// Termin.java
package beispielprogramme.comparator;

import java.util.Objects;

public class Termin {
    private Datum wann;
    private final String was;

    /**
     * Konstruktor zum Initialisieren einer neuen Terminentit&auml;t.
     * @param wann ist das Datum des Termins (darf nicht null sein)
     * @param was ist die Beschreibung des Termins (darf nicht null sein)
     */
    public Termin(Datum wann, String was) {
        this.wann = Objects.requireNonNull(wann, "ungueltiger Termin");
        this.was = Objects.requireNonNull(was, "ungueltiger Termin");
    }

    /**
     * Instanzmethode zum &Auml;ndern des Datums eines Termins.
     * @param wohin ist das neue Datum des Termins (darf nicht null sein)
     */
    public final void verschieben(Datum wohin) {
        this.wann = Objects.requireNonNull(wohin, "ungueltiger Termin");
    }

    /**
     * Instanzmethode zum Abfragen des Datums eines Termins.
     * @return das Datum des Termins
     */
    public final Datum getDatum() {
        return this.wann;
    }

    /**
     * Instanzmethode zum Abfragen der Beschreibung eines Termins.
     * @return die Beschreibung des Termins
     */
    public final String getBeschreibung() {
        return this.was;
    }

    @Override
    /**
     * Erzeugt einen String "Datum, Beschreibung".
     * @return die Stringdarstellung des Termins
     */
    public String toString() {
        return String.format("%s, %s", this.wann, this.was);
    }
}

