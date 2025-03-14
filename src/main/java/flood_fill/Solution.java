package flood_fill;

import java.util.Arrays;

public class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int startCellColor = image[sr][sc];

        if (startCellColor != color) {
            fillCell(image, sr, sc, color, startCellColor);
        }

        return image;
    }

    private void fillCell(int[][] image, int sr, int sc, int color, int startCellColor) {
        if (sr < 0 || sr == image.length || sc < 0 || sc == image[0].length) {
            return;
        }
        if (image[sr][sc] == startCellColor) {
            image[sr][sc] = color;
            fillCell(image, sr, sc - 1, color, startCellColor);
            fillCell(image, sr + 1, sc, color, startCellColor);
            fillCell(image, sr, sc + 1, color, startCellColor);
            fillCell(image, sr - 1, sc, color, startCellColor);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.deepToString(solution.floodFill(new int[][]{{1, 1, 1}, {1, 1, 0}, {1, 0, 1}}, 1, 1, 2)));
    }
}
