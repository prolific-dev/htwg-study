// MainKlasse.java /* TODO: anderer Klassenname */
package beispielprogramme.klassenvorlagen; /* TODO: anderer Paketname */

/* TODO: bei Bedarf import-Deklarationen ... */

/**
 * eine Main-Klasse ist nicht instanziierbar, hat keine Unterklassen und
 * enth&auml;lt als einziges &ouml;ffentliches Element die Klassenmethode
 * <code>void main(String[])</code>.
 * <p>
 * Bei Bedarf kann eine Main-Klasse weitere private Klassenmethoden und
 * private Klassenvariablen enthalten.</p>
 * <p>
 * Einziger Konstruktor einer Main-Klasse ist ein privater Standardkonstruktor
 * mit leerer Implementierung. Er verhindert, dass (au&szlig;erhalb der Klasse)
 * mit <code>new</code> Instanzen erzeugt werden k&ouml;nnen.
 * Instanzen (= Objekte) von Main-Klassen sind sinnlos, weil es keine
 * Instanzmethoden und -variablen gibt.</p>
 * <p>
 * Unterklassen werden durch <code>final</code>-Markierung der Klasse
 * verhindert.</p>
 */
public final class MainKlasse {
    private MainKlasse() { }

    /* TODO: bei Bedarf private Klassenvariablen ... */

    /**
     * <code>main</code> ist der Startpunkt einer Java-Anwendung.
     * @param args die Kommandozeilenargumente
     */
    public static void main(String[] args) {
        /* TODO: Anweisungen ... */
    }

    /* TODO: bei Bedarf private Klassenmethoden ... */
}

