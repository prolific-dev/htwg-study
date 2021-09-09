package beispielprogramme;

public final class MatrixVar {
    private MatrixVar() {}

    public static void main(String[] args) {
       int[][] matrix = new int[][] {new int[] {10, 11, 12}, new int[] {20, 21, 22}};
        //int[][] matrix = {{10, 11, 12}, {20, 21, 22}};

        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[i].length; ++j) {
                System.out.printf("%3d", matrix[i][j]);
            }

            System.out.println();
            }

        // fuer Kopie clone() pro Speicherbereich erforderlich
        int[][] anotherMatrix = matrix.clone();
        for (int i = 0; i < matrix.length; ++i) {
            anotherMatrix[i] = matrix[i].clone();
        }

        matrix[0][0] = 0;
        matrix[1][0] = 0;

        for (int i = 0; i < anotherMatrix.length; ++i) {
            for (int j = 0; j < anotherMatrix[i].length; ++j) {
                System.out.printf("%3d", anotherMatrix[i][j]);
            }

            System.out.println();
        }
    }
}
