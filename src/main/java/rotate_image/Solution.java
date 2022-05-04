package rotate_image;

import java.util.Arrays;

public class Solution {
    public void rotate(int[][] matrix) {
        for (int i = 0; i < matrix.length - 1; i++) {
            for (int j = i; j < matrix.length - i - 1; j++) {
                int start = matrix[i][j];
                matrix[i][j] = matrix[matrix.length - 1 - j - i][i];
                matrix[matrix.length - 1 - j - i][i] = matrix[matrix.length - 1 - i][matrix.length - 1 - j - i];
                matrix[matrix.length - 1 - i][matrix.length - 1 - j - i] = matrix[i + j][matrix.length - 1 - i];
                matrix[i + j][matrix.length - 1] = start;
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] matrix = new int[][]{{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        solution.rotate(matrix);
        // [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
        System.out.println(Arrays.deepToString(matrix));
    }
}
