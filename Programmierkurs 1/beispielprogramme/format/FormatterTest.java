// FormatterTest.java
package beispielprogramme.format;

public final class FormatterTest {
    private FormatterTest() { }

    /**
     * main ist der Startpunkt des Programms.
     * @param args soll zwei ganze Zahlen enthalten
     */
    public static void main(String[] args) {
        int radix = Integer.parseInt(args[0]);
        int n = Integer.parseInt(args[1]);

        Formatter[] alle = {
            new DecimalFormat(),
            new GroupedFormat(),
            new RadixFormat(radix),
            new UnsignedHexFormat()
        };

        for (Formatter f : alle) {
            String s = f.format(n);
            System.out.println(s);
        }
    }
}

