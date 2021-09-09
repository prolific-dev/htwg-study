package beispielprogramme;

public final class EnumVar {
    private EnumVar() {}

    public static void main(String[] args) {
        Jahreszeit fruehling = Jahreszeit.FRUEHLING;
        Jahreszeit sommer = Jahreszeit.valueOf("SOMMER");
        System.out.printf("%s%n%s%n", String.valueOf(fruehling), sommer.toString());

        Jahreszeit[] jahreszeiten = Jahreszeit.values();
        for (int i = 0; i < jahreszeiten.length ; i++) {
            if (jahreszeiten[i] != fruehling && jahreszeiten[i] != sommer) {
                System.out.println(jahreszeiten[i]);
            }
        }
    }
}
