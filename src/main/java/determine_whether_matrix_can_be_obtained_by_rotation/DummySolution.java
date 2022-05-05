package determine_whether_matrix_can_be_obtained_by_rotation;

public class DummySolution {
    public boolean findRotation(int[][] mat, int[][] target) {
        for (int i = 0; i < 4; i++) {
            rotate(mat);
            if (compare(mat, target)) {
                return true;
            }
        }
        return false;
    }

    private boolean compare(int[][] mat, int[][] target) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                if (mat[i][j] != target[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private void rotate(int[][] matrix) {
        int lastIndex = matrix.length - 1;
        for (int i = 0; i < lastIndex; i++) {
            for (int j = i; j < lastIndex - i; j++) {
                int start = matrix[i][j];
                matrix[i][j] = matrix[lastIndex - j][i];
                matrix[lastIndex - j][i] = matrix[lastIndex - i][lastIndex - j];
                matrix[lastIndex - i][lastIndex - j] = matrix[j][lastIndex - i];
                matrix[j][lastIndex - i] = start;
            }
        }
    }
}
