// UtilityKlasse.java /* TODO: anderer Klassenname */
package beispielprogramme.klassenvorlagen; /* TODO: anderer Paketname */

/* TODO: bei Bedarf import-Deklarationen ... */

/**
 * eine Utility-Klasse ist nicht instanziierbar, hat keine Unterklassen
 * und enth&auml;lt nur Klassenvariablen und Klassenmethoden
 * (aber nicht <code>void main(String[])</code>).
 * <p>
 * Die Klassenvariablen sollten in der Regel <code>final</code> sein
 * (der Name wird dann komplett in Gro&szlig;buchstaben geschrieben).</p>
 * <p>
 * Einziger Konstruktor einer Utility-Klasse ist ein privater
 * Standardkonstruktor mit leerer Implementierung. Er verhindert,
 * dass (au&szlig;erhalb der Klasse) mit <code>new</code> Instanzen
 * erzeugt werden k&ouml;nnen. Instanzen (= Objekte) von Utility-Klassen
 * sind sinnlos, weil es keine Instanzmethoden und -variablen gibt.</p>
 * <p>
 * Unterklassen werden durch <code>final</code>-Markierung der Klasse
 * verhindert.</p>
 */
public final class UtilityKlasse {
    private UtilityKlasse() { }

    /* TODO: bei Bedarf Klassenvariablen ... */

    /* TODO: Klassenmethoden ... */
}

