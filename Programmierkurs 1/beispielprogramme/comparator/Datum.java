package beispielprogramme.comparator;

import java.util.Calendar;

public final class Datum implements Comparable<Datum> {
    public final int tag;
    public final int monat;
    public final int jahr;

    private Datum(int tag, int monat, int jahr) {
        this.tag = tag;
        this.monat = monat;
        this.jahr = jahr;
    }

    public static Datum valueOf(int tag, int monat, int jahr) {
        if (tag < 1 || tag > 31 || monat < 1 || monat > 12) {
            throw new IllegalArgumentException("ungueltiges Datum");
        }

        return new Datum(tag, monat, jahr);
    }

    public static Datum heute() {
        Calendar c = Calendar.getInstance();

        return new Datum(c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR));
    }

    @Override
    public String toString() {
        return String.format("%d-%02d-%02d", this.jahr, this.monat, this.tag);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Datum) {
            Datum that = (Datum) obj;
            return this.tag == that.tag && this.monat == that.monat && this.jahr == that.jahr;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (this.jahr << 9) + (this.monat << 5) + this.tag;
    }

    @Override
    public int compareTo(Datum d) {
        if (this.jahr < d.jahr) {
            return -1;
        }

        if (this.jahr > d.jahr) {
            return 1;
        }

        if (this.monat < d.monat) {
            return -1;
        }

        if (this.monat > d.monat) {
            return 1;
        }

        if (this.tag < d.tag) {
            return -1;
        }

        if (this.tag > d.tag) {
            return 1;
        }

        return 0;
    }
}
