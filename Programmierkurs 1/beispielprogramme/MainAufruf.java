// MainAufruf.java

package beispielprogramme;

public final class MainAufruf {
    private MainAufruf() { }

    /**
     * main ist der Startpunkt des Programms.
     * @param args wird nicht verwendet.
     */
    public static void main(String[] args) {
        Programm.main(new String[] {"mit", "drei", "Argumenten"});
    }
}

