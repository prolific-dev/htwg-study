// CountLines.java

package beispielprogramme;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.IOException;

public final class CountLines {
    private CountLines() {
    }

    /**
     * Z&auml;hlt die Zeilen in einer Datei oder in der Standardeingabe.
     *
     * @param args optional ein Dateiname
     * @throws IOException bei Lesefehlern
     */
    public static void main(String[] args) throws IOException {
        BufferedReader in;

        if (args.length == 0) {
            in = new BufferedReader(new InputStreamReader(System.in));
        } else {
            in = new BufferedReader(new FileReader(args[0]));
        }

        int total = 0;
        while (in.readLine() != null) {
            ++total;
        }

        System.out.printf("%d Lines%n", total);
    }
}

