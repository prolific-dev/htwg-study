// ListVar.java
package beispielprogramme.local;
import java.util.Iterator;

/**
 * ListVar legt eine Liste ganzer Zahlen an und gibt sie aus.
 * Beispielprogramm zur Programmiertechnik 1, Teil 5.
 * @author H.Drachenfels
 * @version 5.8.2016
 */
public final class ListVar {
    private ListVar() { }

    /**
     * main ist der Startpunkt des Programms.
     * @param args wird nicht verwendet.
     */
    public static void main(String[] args) {
        int[] anIntArray = {3421, 3442, 3635, 3814};

        //------------------------------------------------- Liste anlegen
        IntList anIntList = new IntList();
        for (int i = anIntArray.length; i > 0; --i) {
            anIntList.insert(anIntArray[i - 1]);
        }

        //------------------------------------------------ Liste ausgeben
        for (int n : anIntList) {
            System.out.println(n);
        }

        Iterator<Integer> i = anIntList.iterator();
        while (i.hasNext()) {
            int n = i.next();
            System.out.println(n);
        }
    }
}

