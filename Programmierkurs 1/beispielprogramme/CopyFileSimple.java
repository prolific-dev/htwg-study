// CopyFileSimple.java
package beispielprogramme;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public final class CopyFileSimple {
    private CopyFileSimple() {
    }

    /**
     * Kopiert eine Datei.
     *
     * @param args Quell- und Zieldateinamen
     * @throws IOException bei Dateizugriffsfehlern
     */
    public static void main(String[] args) throws IOException {
        Path in = Paths.get(args[0]);
        Path out = Paths.get(args[1]);
        Files.copy(in, out);
    }
}

