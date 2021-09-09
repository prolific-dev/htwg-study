package beispielprogramme;

public final class FallunterscheidungEnum {
    private FallunterscheidungEnum() {}

    private enum Month {
        JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC
    }

    public static void main(String[] args) {
        Month m = Month.valueOf(new java.util.Scanner(System.in).next());

        switch (m) {
            case FEB:
                System.out.println("28 oder 29 Tage");
                break;
            case APR:
            case JUN:
            case SEP:
            case NOV:
                System.out.println("30 Tage");
                break;
            default:
                System.out.println("31 Tage");
        }
    }
}
