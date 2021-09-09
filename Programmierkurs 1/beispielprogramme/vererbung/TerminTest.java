package beispielprogramme.vererbung;

public final class TerminTest {
    private TerminTest() {}

    public static void main(String[] args) {
        Termin t = new Termin(Datum.heute(), "Programmiertechnik 1");
        System.out.println(t);

        OrtsTermin ot = new OrtsTermin("G151", Datum.valueOf(11, 10, 2016), "Uebung");
        ot.verschieben(Datum.heute());
        System.out.println(ot);
    }
}
