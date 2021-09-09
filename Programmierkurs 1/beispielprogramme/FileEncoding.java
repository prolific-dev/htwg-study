// FileEncoding.java
package beispielprogramme;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;


public final class FileEncoding {
    private FileEncoding() {
    }

    /**
     * main schreibt die Zeichen
     * &auml;&ouml;&uuml;&szlig;&euro;&frac12;&sup2;&radic;&sum;
     * in eine Datei.
     *
     * @param args leer oder Name eines character sets
     * @throws IOException bei Ausgabefehlern
     */
    public static void main(String[] args) throws IOException {
        String charsetName = Charset.defaultCharset().name();
        if (args.length > 0) {
            charsetName = args[0];
        } else {
            System.out.printf("Using charset \"%s\" out of:%n", charsetName);
            for (Charset cs : Charset.availableCharsets().values()) {
                System.out.println(cs.name());
                for (String s : cs.aliases()) {
                    System.out.printf("    %s%n", s);
                }
            }
        }

        String umlaute = "\u00E4\u00F6\u00FC\u00DF";
        String euro = "\u20AC";
        String symbole = "\u00BD\u00B2\u221A\u2211";

        String fileName = "charset-" + charsetName + ".txt";

        PrintWriter pw = new PrintWriter(fileName, charsetName);
        pw.println(umlaute + euro + symbole);
        pw.close();
    }
}

