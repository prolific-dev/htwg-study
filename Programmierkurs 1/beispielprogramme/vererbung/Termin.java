package beispielprogramme.vererbung;

import java.util.Objects;

public class Termin {
    private Datum wann;
    private final String was;

    public Termin(Datum wann, String was) {
        this.wann = Objects.requireNonNull(wann, "ungueltiger Termin");
        this.was = Objects.requireNonNull(was, "darf nicht null sein");
    }

    public final void verschieben(Datum wohin) {
        this.wann = Objects.requireNonNull(wohin, "ungueltiger Termin");
    }

    public final Datum getDatum() {
        return this.wann;
    }

    public final String getBeschreibung() {
        return this.was;
    }

    @Override
    public String toString() {
        return String.format("%s, %s", this.wann, this.was);
    }
}
