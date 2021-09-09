// NumberTest.java

package beispielprogramme;
import java.util.Scanner;

/**
 * NumberTest zeigt den Umgang mit der Klasse Number.
 * Beispielprogramm zur Programmiertechnik 1, Teil 5.
 * @author H.Drachenfels
 * @version 30.5.2018
 */
public final class NumberTest {
    private NumberTest() { }

    /**
     * main ist der Startpunkt des Programms.
     * @param args Feld mit Dezimalzahlen
     */
    public static void main(String[] args) {
        for (String z : args) {
            Scanner s = new Scanner(z);
            Number n;

            if (s.hasNextByte()) {
                n = Byte.valueOf(s.nextByte());
            } else if (s.hasNextShort()) {
                n = Short.valueOf(s.nextShort());
            } else if (s.hasNextInt()) {
                n = Integer.valueOf(s.nextInt());
            } else if (s.hasNextLong()) {
                n = Long.valueOf(s.nextLong());
            } else if (s.hasNextFloat()) {
                if (z.length() < 9) {
                    n = Float.valueOf(s.nextFloat());
                } else {
                    n = Double.valueOf(s.nextDouble());
                }
            } else {
                System.err.printf("Not a number: %s%n", z);
                continue;
            }

            System.out.printf("%s(%s):%n", n.getClass().getSimpleName(), n);
            System.out.println(n.byteValue());
            System.out.println(n.shortValue());
            System.out.println(n.intValue());
            System.out.println(n.longValue());
            System.out.println(n.floatValue());
            System.out.println(n.doubleValue());
        }
    }
}

