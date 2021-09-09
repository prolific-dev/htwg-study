// WertKlasse.java /* TODO: anderer Klassenname */
package beispielprogramme.klassenvorlage; /* TODO: anderer Paketname */

/* TODO: bei Bedarf import-Deklarationen ... */

/**
 * eine Wertklasse <em>(value-based class)</em> ist mittels Fabrikmethoden
 * instanziierbar (sie ist Bauplan f&uuml;r Wertobjekte),
 * hat keine Unterklassen und alle ihre Instanzvariablen sind konstant
 * (Wertobjekte sind <em>immutable objects</em>).
 * <p>
 * Fabrikmethoden sind Klassenmethoden, die eine Referenz auf ein
 * Wertobjekt der Klasse liefern. Zur Instanziierung verwenden die
 * Fabrikmethoden die privaten Konstruktoren der Klasse.
 * Ein Wertklasse sollte keine &ouml;ffentlichen Konstruktoren haben.</p>
 * <p>
 * Unterklassen werden durch <code>final</code>-Markierung der Klasse
 * verhindert.</p>
 * <p>
 * Eine Wertklasse muss die <code>java.lang.Object</code>-Methoden
 * <code>equals</code>, <code>hashCode</code> und <code>toString</code>
 * &uuml;berschreiben.</p>
 * <p>
 * Eine Wertklasse muss die Schnittstelle <code>java.lang.Comparable</code>
 * implementieren, wenn es eine nat&uuml;rliche Ordnung f&uuml;r die
 * Wertobjekte der Klasse gibt.</p>
 * <p>
 * Eine Wertklasse sollte die Schnittstelle <code>java.util.Formattable</code>
 * implementieren, wenn die String-Darstellung der Wertobjekte nicht durch
 * Formatierung (z.B. in <code>printf</code>) k&uuml;rzbar sein soll.</p>
 */
public final class WertKlasse
implements Comparable<WertKlasse>, java.util.Formattable {

    /* TODO: gekapselte konstante Instanzvariablen ... */
    // (WEGEN GIT AUSGEKLAMMERT) private final TYP NAME;

    /* TODO: Fabrikmethoden ... */
    /**
     * Gegenst&uuml;ck zu <code>toString()</code>.
     * @param s Stringdarstellung eines Wertobjekts
     * @return Referenz auf zugeh&ouml;riges Wertobjekt
     */
    public static WertKlasse valueOf(String s) {
        /* TODO: Referenz auf konsistentes Objekt zurueckgeben oder
                 bei ungueltigem String *IllegalArgumentException* werfen */

        // RETURN WEGEN GIT
        WertKlasse w = new WertKlasse();
        return w;
    }

    /**
     * liefert gem&auml;&szlig; der Parameterliste initialisiertes Wertobjekt.
     * @return Referenz auf Wertobjekt
     */
    public static WertKlasse valueOf(/* Parameterliste */) {
        /* TODO: Referenz auf konsistentes Objekt zurueckgeben oder
                 bei ungueltigen Argumenten *IllegalArgumentException* werfen */

        // RETURN WEGEN GIT
        WertKlasse w = new WertKlasse();
        return w;
    }

    /* TODO: gegebenenfalls Standardwerte ... */
    /**
     * &Uuml;bliche Standardwerte sind neutrale Elemente, Minimum, Maximum.
     */
    public static final WertKlasse STANDARDWERT
        = new WertKlasse(/* Argumente */);


    /* TODO: private Konstruktoren ... */
    /**
     * initialisiert neues Wertobjekt mit den Argumenten der Parameterliste.
     */
    private WertKlasse(/* Parameterliste */) {
        /* TODO: Instanzvariablen initialisieren */
    }

    /* TODO: Methoden aus java.lang.Object ueberschreiben ... */
    @Override
    public boolean equals(Object o) {
        if (o instanceof WertKlasse) {
            WertKlasse that = (WertKlasse) o;
            /* TODO: return true, wenn die Objekte this und that gleich sind */
        }
        return false;
    }

    @Override
    public int hashCode() {
        /* TODO: aus den Werten der Instanzvariablen einen Hashcode berechnen */
        // RETURN WEGEN GIT
        return 0;
    }

    @Override
    public String toString() {
        /* TODO: aus den Werten der Instanzvariablen einen String erzeugen,
                 Gegenstueck zu valueOf(String) */
        // RETURN WEGEN GIT
        return "0";
    }

    /* TODO: gegebenenfalls Comparable<WertKlasse> implementieren ... */
    @Override
    public int compareTo(WertKlasse that) {
        /* TODO: this und that vergleichen ... */

        // RETURN WEGEN GIT
        return 0;
    }

    /* TODO: gegebenenfalls java.util.Formattable implementieren ... */
    @Override
    public void formatTo(java.util.Formatter f,
                         int flags, int width, int precision) {
        /* TODO: formatierte String-Darstellung bauen ... */
    }

    /* TODO: Operationen ... */
    /**
     * verkn&uuml;pft ein Wertobjekt mit den Argumenten der Parameterliste
     * zu einem neuen Wertobjekt.
     * @return Referenz auf das berechnete Wertobjekt
     */
    public WertKlasse OPERATION(/* Parameterliste */) {
        /* aus this und den Argumenten neues Wertobjekt berechnen ... */


        // RETURN WEGEN GIT
        WertKlasse w = new WertKlasse();
        return w;
    }

    /* TODO: bei Bedarf Getter ... */
    /**
     * liefert Bestandteile eines Wertobjekts.
     * @return z.B. Wert einer Instanzvariablen
     */
    /* (WEGEN GIT AUSGEKLAMMERT) public TYP getNAME() {
        return this.NAME;
    }
     */
}

