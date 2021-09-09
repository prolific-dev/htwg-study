// SerializeObject.java


package beispielprogramme;

import java.io.Serializable;

import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import java.io.ObjectInput;
import java.io.ObjectInputStream;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public final class SerializeObject {
    private SerializeObject() {
    }

    private static final class Beispiel implements Serializable {
    }

    /**
     * Erzeugt ein Objekt der Klasse Beispiel,
     * schreibt es in eine Datei "beispiel.ser"
     * und liest es wieder ein.
     *
     * @param args Daten f&uuml;r das Objekt
     * @throws IOException            bei Dateizugriffsfehlern
     * @throws ClassNotFoundException Klasse des gelesenen Objekts unbekannt
     */
    public static void main(String[] args)
            throws IOException, ClassNotFoundException {

        Path p = Paths.get("beispiel.ser");

        //--------------------------------- serialisierbares Objekt erzeugen
        Beispiel b = new Beispiel();

        //--------------------------------------------- Objekt serialisieren
        ObjectOutput out = new ObjectOutputStream(Files.newOutputStream(p));
        out.writeObject(b);
        out.close();

        //------------------------------------------- Objekt deserialisieren
        ObjectInput in = new ObjectInputStream(Files.newInputStream(p));
        Beispiel bb = (Beispiel) in.readObject();
        in.close();

        System.out.printf("%s%n%s%n", b, bb);
    }
}

