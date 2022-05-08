package spiral_matrix;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix[0].length - 1;
        int n = matrix.length - 1;
        int middleHeight = (int) Math.ceil(matrix.length / 2d);
        int middleWidth = (int) Math.ceil(matrix[0].length / 2d);
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < middleHeight; i++) {
            if (i >= middleWidth) {
                break;
            }
            System.out.println("right");
            for (int j = i; j <= m - i; j++) {
                result.add(matrix[i][j]);
            }
            if (matrix.length % 2 == 1 && i == middleHeight - 1) {
                break;
            }
            System.out.println("down");
            for (int j = i + 1; j < n - i; j++) {
                result.add(matrix[j][m - i]);
            }
            System.out.println("left");
            for (int j = m - i; j >= i; j--) {
                result.add(matrix[n - i][j]);
            }
            System.out.println("up");
            for (int j = n - i - 1; j > i; j--) {
                result.add(matrix[j][i]);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.spiralOrder(new int[][]{
                {1, 11},
                {2, 12},
                {3, 13},
                {4, 14},
                {5, 15},
                {6, 16},
                {7, 17},
                {8, 18},
                {9, 19},
                {10, 20}
        }));
//        System.out.println(solution.spiralOrder(new int[][]{
//                {1, 2, 3, 4, 5},
//                {6, 7, 8, 9, 10},
//                {11, 12, 13, 14, 15},
//                {16, 17, 18, 19, 20},
//                {21, 22, 23, 24, 25}
//        }));
    }
}
