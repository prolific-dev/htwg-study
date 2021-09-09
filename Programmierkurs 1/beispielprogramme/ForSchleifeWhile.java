package beispielprogramme;

public final class ForSchleifeWhile {
    private ForSchleifeWhile() {}

    public static void main(String[] args) {
        double[] anArray = {3.625, 3.648, 3.853, 4.042};

        { // Lebensdauer der Laufvariablen beschraenken
            int i = 0;                          // Initialisierung
            while (i < anArray.length) {        // Fortsetzungsbedingung
                    System.out.println(anArray[i]);
                    ++i;                            // Fortschaltung
            }
        }

        { // for-each-Schleife braucht mit while ebenfalls Indexlaufvariable
            int i = 0;
            while (i < anArray.length) {
                    double n = anArray[i];
                    System.out.println(n);
                    ++i;
            }
        }
    }
}
