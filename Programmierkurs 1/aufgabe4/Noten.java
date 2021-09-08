// Noten.java

package aufgabe4;

/**
 * Hilfsklasse Noten mit den verschiedenen Hilfsmethoden.
 * @author Dennis Agostinho da Silva
 * @version 11.12.2018
 */
public final class Noten {
    private Noten() { }
    /**BESTE ist die beste Zulässige Note.**/
    public static final double BESTE = 1.0;
    /**SCHLECHTESTE ist die schlechteste Zulässige Note.**/
    public static final double SCHLECHTESTE = 5.0;

    /**
    *istZulässig prüft ob die Eingabe eine Zulässige Note ist.
    *@param s ist die zu prüfende Note als String.
    *@return Boolean ob die Note zulässig ist.
    */
    public static boolean istZulaessig(final String s) {

        String[] htwg = new String[22];

        htwg[0] = "1.0";
        htwg[1] = "1.3";
        htwg[2] = "1.7";
        htwg[3] = "2.0";
        htwg[4] = "2.3";
        htwg[5] = "2.7";
        htwg[6] = "3.0";
        htwg[7] = "3.3";
        htwg[8] = "3.7";
        htwg[9] = "4.0";
        htwg[10] = "5.0";
        htwg[11] = "1,0";
        htwg[12] = "1,3";
        htwg[13] = "1,7";
        htwg[14] = "2,0";
        htwg[15] = "2,3";
        htwg[16] = "2,7";
        htwg[17] = "3,0";
        htwg[18] = "3,3";
        htwg[19] = "3,7";
        htwg[20] = "4,0";
        htwg[21] = "5,0";

        int countmatch = 0;
        int bernote = 0;
        int besnote = 0;
        double notedouble = 0.0;
        double sumbest = 0.0;
        double best = 0.0;
        double worst = 0.0;

        if (s.length() == 3 && Character.isDigit(s.charAt(0))
                && Character.isDigit(s.charAt(2))
                && (s.charAt(1) == ',' || s.charAt(1) == '.')) {

            int vor = Character.getNumericValue(s.charAt(0));
            int nach = Character.getNumericValue(s.charAt(2));
            notedouble = (double) vor + ((double) nach / 10);

            if (vor == 0) {

                return false;

            } else if (vor == 6) {

                return false;

            } else if ((vor == 6 || vor == 7 || vor == 8 || vor == 9)
                       && (nach ==  3 || nach == 7)) {

                return false;

            } else if ((vor == 1 || vor == 2 || vor == 3)
                       && (nach == 1 || nach == 2 || nach == 4 || nach == 5
                           || nach == 6 || nach == 8 || nach == 9)) {

                return false;

            } else if ((vor == 6 || vor == 7 || vor == 8 || vor == 9)
                       && (nach == 0)) {

                return false;

            } else if ((vor == 4 || vor == 5)
                       && (nach == 1 || nach == 2 || nach == 3 || nach == 4
                           || nach == 5 || nach == 6 || nach == 7 || nach == 8
                           || nach == 9)) {

                return false;

            } else {

                if (notedouble <= 4.0) {

                }

                return true;
            }
        } else {

            for (int i = 0; i < htwg.length; ++i) {
                boolean match = htwg[i].contains(s);

                if (match) {

                    countmatch++;
                }
            }

            if (countmatch != 1) {

                return false;
            }

            return false;
        }
    } //istZulaessig

    /**
    *toDouble wandelt eine als String eingegebene Note in einen Double.
    *@param note ist die Note als String.
    *bei unzulässigem Notenstring kommt IllegalAgumentException.
    *@return Note als Double.
    **/
    public static double toDouble(final String note) {

        if (istZulaessig(note)) {
            int vor = Character.getNumericValue(note.charAt(0));
            int nach = Character.getNumericValue(note.charAt(2));
            double notedouble = (double) vor + ((double) nach / 10);
            return notedouble;
        } else {
            throw new IllegalArgumentException();
        }

    } //toDouble
    /**toString wandelt eine Double Note in einen String.
    *@param notedouble Note als Double.
    *bei unzulässigem String wirft toString eine IllegalArgumentException.
    *@return Note als String.
    **/
    public static String toString(final double notedouble) {

        String notestring = String.format("%.1f", notedouble);
        if (istZulaessig(notestring)) {
            return notestring;
        } else {
            throw new IllegalArgumentException();
        }

    } //toString
    /**istBestanden prüft ob eine Note als Bestanden gewertet wird.
    *@param note Note als Double.
    *@return Boolean ob Note als bestanden gewertet wird.
    **/
    public static boolean istBestanden(final double note) {
        return note <= 4.0;
    } // istBestanden

    /**bessere gibt die bessere Note von zwei Double Noten wieder.
    *@param a ist eine der zu prüfenden Noten.
    *@param b ist andere zu prüfende Note.
    *@return die bessere Note.
    **/
    public static double bessere(final double a, final double b) {
        if (a <= b) {
            return a;
        } else {
            return b;
        }
    } //bessere

    /**schlechtere gibt die schlechtere Note von zwei Double Noten wieder.
    *@param a ist eine der zu prüfenden Noten.
    *@param b ist die andere zu prüfende Note.
    *@return die schlechtere Note.
    **/
    public static double schlechtere(final double a, final double b) {
        if (a >= b) {
            return a;
        } else {
            return b;
        }
    } //schlechtere
}
