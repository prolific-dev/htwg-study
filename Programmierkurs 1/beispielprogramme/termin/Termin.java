// Termin.java

package beispielprogramme.termin;
import java.util.Objects;
import beispielprogramme.datum.*;


public final class Termin {
    private Datum wann;
    private final String was;

    /**
     * Konstruktor zum Initialisieren einer neuen Terminentit&auml;t.
     * Der heimliche Paramter this verweist auf das neue Objekt.
     * @param wann ist das Datum des Termins (darf nicht null sein)
     * @param was ist die Beschreibung des Termins (darf nicht null sein)
     */
    public Termin(/* final Termin this, */ Datum wann, String was) {
        this.wann = Objects.requireNonNull(wann, "ungueltiger Termin");
        this.was = Objects.requireNonNull(was, "ungueltiger Termin");
    }

    /**
     * Instanzmethode zum &Auml;ndern des Datums eines Termins.
     * Der heimliche Paramter this verweist auf das Objekt des Aufrufs.
     * @param wohin ist das neue Datum des Termins (darf nicht null sein)
     */
    public void verschieben(/* final Termin this, */ Datum wohin) {
        this.wann = Objects.requireNonNull(wohin, "ungueltiger Termin");
    }

    /**
     * Instanzmethode zum Abfragen des Datums eines Termins.
     * Der heimliche Paramter this verweist auf das Objekt des Aufrufs.
     * @return das Datum des Termins
     */
    public Datum getDatum(/* final Termin this */) {
        return this.wann;
    }

    /**
     * Instanzmethode zum Abfragen der Beschreibung eines Termins.
     * Der heimliche Paramter this verweist auf das Objekt des Aufrufs.
     * @return die Beschreibung des Termins
     */
    public String getBeschreibung(/* final Termin this */) {
        return this.was;
    }
}

