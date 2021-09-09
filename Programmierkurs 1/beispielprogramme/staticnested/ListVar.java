// ListVar.java
package beispielprogramme.staticnested;

/**
 * ListVar legt eine Liste ganzer Zahlen an und gibt sie aus.
 * Beispielprogramm zur Programmiertechnik 1, Teil 4.
 * @author H.Drachenfels
 * @version 4.8.2016 Variante mit statisch eingebetteter Iterator-Klasse
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
        IntList.Iterator i = new IntList.Iterator(anIntList);
        while (i.hasNext()) {
            System.out.println(i.next());
        }
    }
}

