package longest_increasing_path_in_a_matrix;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        Map<Integer, Integer> cellToPathLength = new HashMap<>();

        int height = matrix.length;
        int width = matrix[0].length;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                computePathLength(cellToPathLength, i, j, matrix, width, height);
            }
        }
        for (int i = height - 1; i >= 0; i--) {
            for (int j = width - 1; j >= 0; j--) {
                computePathLength(cellToPathLength, i, j, matrix, width, height);
            }
        }
        return cellToPathLength.values().stream()
                .mapToInt(integer -> integer)
                .max()
                .orElseThrow();
    }

    private void computePathLength(Map<Integer, Integer> cellToPathLength, int i, int j, int[][] matrix, int width, int height) {
        int cell = j + i * width;
        int cellValue = matrix[i][j];
        int pathLength = 1;
        if (i > 0 && matrix[i - 1][j] < cellValue) {
            int neighbourCell = j + (i - 1) * width;
            pathLength = Math.max(pathLength, cellToPathLength.get(neighbourCell) + 1);
        }
        if (j < width - 1 && matrix[i][j + 1] < cellValue) {
            int neighbourCell = (j + 1) + i * width;
            pathLength = Math.max(pathLength, cellToPathLength.getOrDefault(neighbourCell, 1) + 1);
        }
        if (i < height - 1 && matrix[i + 1][j] < cellValue) {
            int neighbourCell = j + (i + 1) * width;
            pathLength = Math.max(pathLength, cellToPathLength.getOrDefault(neighbourCell, 1) + 1);
        }
        if (j > 0 && matrix[i][j - 1] < cellValue) {
            int neighbourCell = (j - 1) + i * width;
            pathLength = Math.max(pathLength, cellToPathLength.get(neighbourCell) + 1);
        }
        int finalPathLength = pathLength;
        cellToPathLength.put(cell, Math.max(cellToPathLength.getOrDefault(cell, 1), finalPathLength));
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println(solution.longestIncreasingPath(new int[][]{{9, 9, 4}, {6, 6, 8}, {2, 1, 1}}));
//        System.out.println(solution.longestIncreasingPath(new int[][]{{3, 4, 5}, {3, 2, 6}, {2, 2, 1}}));
        System.out.println(solution.longestIncreasingPath(new int[][]{{7, 8, 9}, {9, 7, 6}, {7, 2, 3}}));
//        System.out.println(solution.longestIncreasingPath(new int[][]{{1, 2}}));
    }
}
