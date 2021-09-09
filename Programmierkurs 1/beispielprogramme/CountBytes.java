// CountBytes.java
package beispielprogramme;

import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;

public final class CountBytes {
    private CountBytes() {
    }

    /**
     * Z&auml;hlt die Bytes in einer Datei oder in der Standardeingabe.
     *
     * @param args optional ein Dateiname
     * @throws IOException bei Lesefehlern
     */
    public static void main(String[] args) throws IOException {
        InputStream in;

        if (args.length == 0) {
            in = System.in;
        } else {
            in = new FileInputStream(args[0]);
        }

        int total = 0;
        while (in.read() != -1) {
            ++total;
        }

        System.out.printf("%d Byte%n", total);
    }
}

