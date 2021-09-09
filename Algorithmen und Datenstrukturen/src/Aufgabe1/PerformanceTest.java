// Dennis Agostinho da Silva
// 22.01.2020

package Aufgabe1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PerformanceTest {
    private static Dictionary<String, String> dict;
    static List<String> listErfolgreiche8000 = new ArrayList<>();
    static List<String> listErfolgreiche16000 = new ArrayList<>();
    static List<String> listErfolglose8000 = new ArrayList<>();
    static List<String> listErfolglose16000 = new ArrayList<>();
    static List<TimeValues> list = new ArrayList<>();




    private static TimeValues buildTime(int eintraege) {
        dict = new SortedArrayDictionary<>();
        long sad = read(eintraege);
        dict = new HashDictionary<>(3);
        long hd = read(eintraege);
        dict = new BinaryTreeDictionary<>();
        long btd = read(eintraege);

        int stringInt;
        if (eintraege == 0)
            stringInt = 16000;
        else
            stringInt = 8000;

        TimeValues tv = new TimeValues("Aufbau " + stringInt, sad, hd, btd);

        return tv;


    }

    private static TimeValues searchTime(int eintraege, List<String> list) {
        dict = new SortedArrayDictionary<>();
        read(eintraege);
        long sad = 0;
        for (String s : list) {
            long timeStart = System.nanoTime();
            dict.search(s);
            long timeEnd = System.nanoTime();
            sad += (timeEnd - timeStart);
        }
        dict = new HashDictionary<>(3);
        read(eintraege);
        long hd = 0;
        for (String s : list) {
            long timeStart = System.nanoTime();
            dict.search(s);
            long timeEnd = System.nanoTime();
            hd += (timeEnd - timeStart);
        }
        dict = new BinaryTreeDictionary<>();
        read(eintraege);
        long btd = 0;
        for (String s : list) {
            long timeStart = System.nanoTime();
            dict.search(s);
            long timeEnd = System.nanoTime();
            btd += (timeEnd - timeStart);
        }

        
        int stringInt;
        if (eintraege == 0)
            stringInt = 16000;
        else
            stringInt = 8000;



        TimeValues tv = new TimeValues("Erfolgreiche Suche " + stringInt, sad, hd, btd);

        return tv;
    }

    private static void initializeTestLists() {
        dict = new SortedArrayDictionary<>();
        read(8000);
        for (Dictionary.Entry<String, String> e : dict) {
            listErfolgreiche8000.add(e.getKey());
            listErfolglose8000.add(e.getValue());
        }

        dict = new SortedArrayDictionary<>();
        read(16000);
        for (Dictionary.Entry<String, String> e : dict) {
            listErfolgreiche16000.add(e.getKey());
            listErfolglose16000.add(e.getValue());
        }
    }

    private static void performanceTest() {

        initializeTestLists();

        // Aufbau 8000
        list.add(buildTime(8000));
        // Aufbau 16000
        list.add(buildTime(0));
        // Erfolgreiche Suche 8000
        list.add(searchTime(8000, listErfolgreiche8000));
        // Erfolgreiche Suche 16000
        list.add(searchTime(0, listErfolgreiche16000));
        // Erfolglose Suche 8000
        list.add(searchTime(8000, listErfolglose8000));
        // Erfolglose Suche 16000
        list.add(searchTime(0, listErfolglose16000));
    }

    public static void printPerformanceTest() {

        performanceTest();

        System.out.printf("%-27s | %-30s | %-25s | %-20s%n", "", "SortedArrayDictionary", "HashDictionary", "BinaryTreeDictionary");
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
        for (TimeValues tv : list) {
            String sad = (tv.getSad() / 1e+6) + " ms";
            String hd = (tv.getHd() / 1e+6) + " ms";
            String btd = (tv.getBtd() / 1e+6) + " ms";
            System.out.printf("%-27s | %-30s | %-25s | %-20s%n", tv.getString(), sad, hd, btd);

        }

        System.out.println();

    }

    private static long read(int eintraege) {
        File file = new File("C:\\Users\\HP\\AlgoDat\\AlgoDat_20\\src\\Aufgabe1\\dtengl.txt");
        long time = 0;
        try {
            Scanner scannerFile = new Scanner(file);
            if (eintraege > 0) {
                for (int i = 0; i < eintraege; i++) {
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
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return time;
    }

    private static class TimeValues {
        String s;
        long sad;
        long hd;
        long btd;

        private TimeValues(String s, long sad, long hd, long btd) {
            this.s = s;
            this.sad = sad;
            this.hd = hd;
            this.btd = btd;
        }

        public String getString() {
            return s;
        }

        public long getSad() {
            return sad;
        }

        public long getHd() { return hd; }

        public long getBtd() {
            return btd;
        }
    }
}
