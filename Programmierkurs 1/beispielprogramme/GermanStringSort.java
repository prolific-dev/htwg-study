// GermanStringSort.java

package beispielprogramme;
import java.text.Collator;
import java.util.Locale;

public final class GermanStringSort {
    private GermanStringSort() { }

    /**
     * main ist der Startpunkt des Programms.
     * @param args wird nicht verwendet.
     */
    public static void main(String[] args) {
        java.util.Arrays.sort(args, Collator.getInstance(Locale.GERMAN));

        for (String s : args) {
            System.out.println(s);
        }
    }
}

