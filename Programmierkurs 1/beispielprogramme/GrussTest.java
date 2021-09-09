// GrussTest.java

package beispielprogramme;

import beispielprogramme.gruss.Gruss;
import static beispielprogramme.gruss.Gruss.hallo;

public final class GrussTest {
    private GrussTest() { }

    /**
     * main ist der Startpunkt des Programms.
     * @param args wird nicht verwendet.
     */
    public static void main(String[] args) {
        beispielprogramme.gruss.Gruss.hallo();
        Gruss.hallo(); // moeglich wegen import
        hallo(); // moeglich wegen import static
    }
}

