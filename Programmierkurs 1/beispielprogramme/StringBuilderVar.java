package beispielprogramme;

public final class StringBuilderVar {
    private StringBuilderVar() {}

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < args.length ; i++) {
            if (i > 0) {
                sb.append(',');
            }

            sb.append(i).append(":\"").append(args[i]).append('\"');
        }

        System.out.println(sb.toString());
    }
}
