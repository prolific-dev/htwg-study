package beispielprogramme;

public final class BooleanWrapper {
    private BooleanWrapper() {}

    public static void main(String[] args) {
        Boolean reference;
        reference = new Boolean(true);
        System.out.println(reference);
        reference = new Boolean(true);
        System.out.println(reference);
        reference = Boolean.TRUE;
        System.out.println(reference);
        reference = true;
        System.out.println(reference);
        reference = Boolean.valueOf("true");
        System.out.println(reference);

        boolean value;
        value = reference;
        System.out.println(value);
        value = reference.booleanValue();
        System.out.println(value);
        value = Boolean.parseBoolean("true");
        System.out.println(value);
        value = reference.compareTo(Boolean.TRUE) == 0;
        System.out.println(value);

        String s;
        s = Boolean.toString(true);
        System.out.println(s);
        s = reference.toString();
        System.out.println(s);
    }
}
