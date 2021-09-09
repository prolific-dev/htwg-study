// ListVar.java
package beispielprogramme.nested;

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
        IntList.Iterator i = anIntList.new Iterator();
        while (i.hasNext()) {
            System.out.println(i.next());
        }
    }
}

