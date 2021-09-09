// Dennis Agostinho da Silva
// 22.01.2020

package Aufgabe1;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DictionaryTUI {

    public static Scanner scanner = new Scanner(System.in);
    public static Dictionary<String, String> dict;
    public static JFileChooser fileChooser;

    public static void main(String[] args) {
        dict = new SortedArrayDictionary<>();
        System.out.println("Einlesen für Dictionary, h für help");
        String s = scanner.next();

        while (s.equals("exit") == false) {
            switch (s) {
                case "create": create(); break;
                case "read":
                    String arguments = scanner.nextLine();
                    if (arguments.equals(""))
                        read(0);
                    else
                        read(Integer.parseInt(arguments));
                    break;
                case "p": print(); break;
                case "s": search(); break;
                case "i": insert(); break;
                case "r": remove(); break;
                case "z": PerformanceTest.printPerformanceTest(); break;
                case "h": help(); break;
                default:
                    System.out.println("Falsche Eingabe.");
                    scanner.nextLine();

            }
            s = scanner.next();
        }
    }

    private static void create() {
        System.out.println("Welches Dictionary möchten sie erstellen?");
        String s = scanner.next();

        switch (s) {
            case "SAD":
            case "sad":
            case "SortedArrayDictionary":
                dict = new SortedArrayDictionary();
                System.out.println("SortedArrayDictionary wurde erstellt.");
                break;
            case "HD":
            case "hd":
            case "HashDictionary":
                dict = new HashDictionary<>(3);
                System.out.println("HashDictionary wurde erstellt.");
                break;
            case "BTD":
            case "btd":
            case "BinaryTreeDictionary":
                dict = new BinaryTreeDictionary<>();
                System.out.println("BinaryTreeDictionary wurde erstellt.");
                break;
            default:
                dict = new SortedArrayDictionary<>();
                System.out.println("Falsche Eingabe. SortedArrayDictionary wurde default erstellt.");
                break;
        }
    }

    private static long read(int eintraege) {
        System.out.println("Welche Datei möchten sie einlesen?");
        /*
        fileChooser = new JFileChooser("C:\\Users\\HP\\AlgoDat\\AlgoDat_20\\src\\Aufgabe1\\");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int tmp = fileChooser.showOpenDialog(fileChooser.getParent());

        if (tmp == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
        */
            File file = new File("C:\\Users\\HP\\AlgoDat\\AlgoDat_20\\src\\Aufgabe1\\dtengl.txt");
            System.out.println("Datei wurde ausgewählt.");
            long time = 0;
            int n = 0;

            try {
                Scanner scannerFile = new Scanner(file);
                if (eintraege > 0) {
                    n = eintraege;
                    for (int i = 0; i < n; i++) {
                        if (!scannerFile.hasNext()) break;
                        String k = scannerFile.next();
                        String v = scannerFile.next();
                        final long timeStart = System.nanoTime();
                        dict.insert(k, v);
                        final long timeEnd = System.nanoTime();
                        time += (timeEnd - timeStart);

                    }
                } else {
                    while (scannerFile.hasNextLine()) {
                        if (!scannerFile.hasNext()) break;
                        String k = scannerFile.next();
                        String v = scannerFile.next();
                        final long timeStart = System.nanoTime();
                        dict.insert(k, v);
                        final long timeEnd = System.nanoTime();
                        time += (timeEnd - timeStart);
                        n++;
                    }
                }

                System.out.println(n + " Einträge wurden erfolgreich eingelesen.");

            } catch (FileNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            }
        // }

        return time;
    }

    private static void print() {
        System.out.println("Dictionary wird gedruckt.");
        System.out.println();

        if (dict.size() != 0) {
            for (Dictionary.Entry<String, String> e : dict) {
                System.out.println(e.getKey() + " : " + e.getValue());
            }
        } else {
            System.out.println("Dictionary ist leer.");
        }
    }

    private static void search() {
        System.out.println("Suche nach englischer Übersetzung.");
        String s = scanner.next();
        String x = dict.search(s);
        if (x == null)
            System.out.println("Suche nicht erfolgreich.");
        else
            System.out.println(s + ": " + x + " wurde gefunden.");
    }

    private static void insert() {
        System.out.println("Insert");
        System.out.println("Deutsches Wort: ");
        String s = scanner.next();
        System.out.println(s);
        System.out.println("Englisches Wort: ");
        String s1 = scanner.next();
        System.out.println(s1);
        dict.insert(s, s1);
        System.out.println(s + ": " + s1 + " wurde hinzugefügt.");
    }

    private static void remove() {
        System.out.println("Remove");
        String s = scanner.next();
        String s1 = dict.remove(s);
        if (s1 != null)
            System.out.println(s + ": " + s1 + " wurde entfernt.");
        else
            System.out.println(s + " wurde nicht gefunden.");
    }

    private static void help() {
        System.out.println("create: Legt ein Dictionary an. SortedArrayDictionary ist voreingestellt.");
        System.out.println("read [n]: Liest die ersten n Einträge derDatei in das Dictionary ein. "
                + "Wird n weggelassen, dann werden alle Einträge eingelesen.");
        System.out.println("p: Gibt alle Einträge des Dictionary in der Konsole aus (print).");
        System.out.println("s: Gibt das entsprechende englische Wort aus (search).");
        System.out.println("i: Fügt ein neues Wortpaar in das Dictionary ein (insert).");
        System.out.println("r: Löscht einen Eintrag (remove).");
        System.out.println("z: Performance-Untersuchung.");
        System.out.println("exit: Beendet das Programm.");
    }

}
