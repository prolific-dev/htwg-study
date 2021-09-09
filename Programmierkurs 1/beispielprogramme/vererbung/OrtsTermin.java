package beispielprogramme.vererbung;

public final class OrtsTermin extends Termin {
    private final String wo;

    public OrtsTermin(String wo, Datum wann, String was) {
        super(wann, was);
        if (wo == null || wo.length() == 0) {
            throw new IllegalArgumentException("ungueltiger Termin");
        }
        this.wo = wo;
    }

    public String getOrt() {
        return this.wo;
    }

    @Override
    public String toString() {
        return String.format("%s, %s", this.wo, super.toString());
    }


}
