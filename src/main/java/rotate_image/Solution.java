package rotate_image;

import java.util.Arrays;

public class Solution {
    public void rotate(int[][] matrix) {
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

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] matrix = new int[][]{{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        solution.rotate(matrix);
        // [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
        System.out.println(Arrays.deepToString(matrix));
    }
}
