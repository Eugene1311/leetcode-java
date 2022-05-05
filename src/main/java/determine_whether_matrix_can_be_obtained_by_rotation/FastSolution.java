package determine_whether_matrix_can_be_obtained_by_rotation;

public class FastSolution {
    public boolean findRotation(int[][] matrix, int[][] target) {
        boolean[] matches = new boolean[]{true, true, true, true};
        int lastIndex = matrix.length - 1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matches[0] && matrix[i][j] != target[i][j]) {
                    matches[0] = false;
                }
                if (matches[1] && matrix[i][j] != target[j][lastIndex - i]) {
                    matches[1] = false;
                }
                if (matches[2] && matrix[i][j] != target[lastIndex - i][lastIndex - j]) {
                    matches[2] = false;
                }
                if (matches[3] && matrix[i][j] != target[lastIndex - j][i]) {
                    matches[3] = false;
                }
            }
        }
        return matches[0] || matches[1] || matches[2] || matches[3];
    }
}
