package beispielprogramme;

public final class KlausurArray {
    private KlausurArray() {}

    public static void main(String[] args) {
        int[] punkte = {12, 8 ,10};

        for (int i = 0; i < punkte.length; i++) {
            System.out.printf("Aufgabe %d: %d Punkte%n", i + 1, punkte[i]);
        }
    }
}
