// ClassVar.java

package beispielprogramme;


public final class ClassVar {
    /** Es soll keine Instanzen der Klasse geben. */
    private ClassVar() { }

    /**
     * global ist ein Beispiel fuer eine klassenglobale Variable.
     */
    private static int global = 1;

    /**
     * aClassMethod ist ein Beispiel fuer eine Klassenmethode.
     * @param param ist ein Beispiel fuer einen Methodenparameter.
     * @return eine ganze Zahl.
     */
    private static int aClassMethod(int param) {
        int local = param + 1;
        global = param + 2;
        return local;
    }

    /**
     * main ist der Startpunkt des Programms.
     * @param args wird nicht verwendet.
     */
    public static void main(String[] args) {
        int local = 1;
        local = aClassMethod(local);                     // local wird 2
        global = aClassMethod(local);                    // global wird 3
        local = ClassVar.aClassMethod(ClassVar.global);  // local wird 4
    }
}

