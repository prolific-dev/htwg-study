package beispielprogramme;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.util.Locale;

public final class NumberFormatTest {
    private NumberFormatTest() {}

    public static void main(String[] args) {

        NumberFormat[] zahlFormat = {
                NumberFormat.getInstance(),
                NumberFormat.getIntegerInstance(),
                NumberFormat.getPercentInstance(),
                NumberFormat.getCurrencyInstance(),
                NumberFormat.getInstance(Locale.GERMAN),
                NumberFormat.getIntegerInstance(Locale.GERMAN),
                NumberFormat.getPercentInstance(Locale.GERMAN),
                //NumberFormat.getCurrencyInstance(Locale.GERMANY),
                NumberFormat.getInstance(Locale.ENGLISH),
                NumberFormat.getIntegerInstance(Locale.ENGLISH),
                NumberFormat.getPercentInstance(Locale.ENGLISH),
                NumberFormat.getCurrencyInstance(Locale.ENGLISH),
                //NumberFormat.getCurrencyInstance(Locale.UK),
                //NumberFormat.getCurrencyInstance(Locale.US),
        };

        PrintWriter out = null;
        try {
            out = new PrintWriter("numberformat-utf8.txt", "utf-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        for (NumberFormat nf: zahlFormat) {
            System.out.printf("%s %s%n", nf.format(12.34), nf.format(5.678));
            out.printf("%s %s%n", nf.format(12.34), nf.format(5.678));
        }

        out.close();
    }
}
