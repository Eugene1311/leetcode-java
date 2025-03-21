package rotting_oranges;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public int orangesRotting(int[][] grid) {
        List<List<Integer>> rotten = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    rotten.add(List.of(i, j));
                }
            }
        }

        int count = markRotten(grid, rotten, 0);

        return checkFresh(grid) ? -1 : count;
    }

    private boolean checkFresh(int[][] grid) {
        for (int[] row : grid) {
            for (int j = 0; j < grid[0].length; j++) {
                if (row[j] == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    private int markRotten(int[][] grid, List<List<Integer>> rotten, int count) {
        List<List<Integer>> nextRotten = new ArrayList<>();
        rotten.forEach(cell -> {
            int y = cell.get(0);
            int x = cell.get(1);

            if (y > 0 && grid[y - 1][x] == 1) {
                grid[y - 1][x] = 2;
                nextRotten.add(List.of(y - 1, x));
            }
            if (x < grid[0].length - 1 && grid[y][x + 1] == 1) {
                grid[y][x + 1] = 2;
                nextRotten.add(List.of(y, x + 1));
            }
            if (y < grid.length - 1 && grid[y + 1][x] == 1) {
                grid[y + 1][x] = 2;
                nextRotten.add(List.of(y + 1, x));
            }
            if (x > 0 && grid[y][x - 1] == 1) {
                grid[y][x - 1] = 2;
                nextRotten.add(List.of(y, x - 1));
            }
        });

        if (nextRotten.isEmpty()) {
            return count;
        }

        return markRotten(grid, nextRotten, ++count);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.orangesRotting(new int[][]{
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}
        }));
    }
}
