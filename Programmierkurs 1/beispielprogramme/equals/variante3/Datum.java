package beispielprogramme.equals.variante3;

import java.awt.image.DataBufferUShort;

public class Datum {
    private final int tag;
    private final int monat;
    private final int jahr;

    public static Datum valueOf(int tag, int monat, int jahr) {
        return new Datum(tag, monat, jahr);
    }

    protected Datum(int tag, int monat, int jahr) {
        int maxTag;
        switch (monat) {
            case 2:
                if (jahr % 4 == 0 && (jahr % 100 != 0 || jahr % 400 == 0)) {
                    maxTag = 29;
                } else {
                    maxTag = 28;
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

        if (tag < 1 ||tag > maxTag || monat < 1 || monat > 12) {
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
            if (o != null && this.getClass() == o.getClass()) {
                Datum that = (Datum) o;
                return this.tag == that.tag && this.monat == that.monat && this.jahr == that.jahr;
            }
        }

        return false;
    }

    @Override
    public int hashCode() {
        return (this.jahr << 9) + (this.monat << 5) + this.tag;
    }
}
