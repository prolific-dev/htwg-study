// CopyFile.java

package beispielprogramme;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class CopyFile {
    private CopyFile() {
    }

    /**
     * Kopiert eine Datei.
     *
     * @param args Quell- und Zieldateinamen
     * @throws IOException bei Dateizugriffsfehlern
     */
    public static void main(String[] args) throws IOException {
        try (
                InputStream in = Files.newInputStream(Paths.get(args[0]));
                OutputStream out = Files.newOutputStream(Paths.get(args[1]));
        ) {
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
        } // automatische Aufrufe in.close() und out.close()
    }
}

