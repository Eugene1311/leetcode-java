package number_of_islands;

import java.util.*;
import java.util.stream.Collectors;

public class SolutionWithQuickFind {
    // Time Limit Exceeded
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int height = grid.length;
        int width = grid[0].length;
        List<Integer> ids = generateIds(grid);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (grid[i][j] == '0') {
                    continue;
                }
                char rightNeighbour = j < width - 1 ? grid[i][j + 1] : '0';
                if (rightNeighbour == '1') {
                    ids = union(i * width + j, i * width + j + 1, ids);
                }
                char downNeighbour = i < height - 1 ? grid[i + 1][j] : '0';
                if (downNeighbour == '1') {
                    ids = union(i * width + j, (i + 1) * width + j, ids);
                }
            }
        }

        return new HashSet<>(
                ids.stream()
                        .filter(id -> id != -1)
                        .collect(Collectors.toList())
        ).size();
    }

    private List<Integer> generateIds(char[][] grid) {
        List<Integer> ids = new ArrayList<>();
        int height = grid.length;
        int width = grid[0].length;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (grid[i][j] == '1') {
                    ids.add(i * width + j);
                } else {
                    ids.add(-1);
                }
            }
        }
        return ids;
    }

    private List<Integer> union(int p, int q, List<Integer> ids) {
        int pId = ids.get(p);
        int qId = ids.get(q);
        return ids.stream()
                .map(id -> {
                    if (id == pId) {
                        return qId;
                    }
                    return id;
                })
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        SolutionWithQuickFind solution = new SolutionWithQuickFind();
        // O((m * n) ^2)
//        System.out.println(solution.numIslands(new char[][]{
//                {'1', '1', '1', '1', '0'},
//                {'1', '1', '0', '1', '0'},
//                {'1', '1', '0', '0', '0'},
//                {'0', '0', '0', '0', '0'}
//        }));
//        System.out.println(solution.numIslands(new char[][]{
//                {'1', '1', '0', '0', '0'},
//                {'1', '1', '0', '0', '0'},
//                {'0', '0', '1', '0', '0'},
//                {'0', '0', '0', '1', '1'}
//        }));
//        System.out.println(solution.numIslands(new char[][]{
//                {'1', '1'}
//        }));
        System.out.println(solution.numIslands(new char[][]{
                {'1','0','1','1','1'},
                {'1','0','1','0','1'},
                {'1','1','1','0','1'}
        }));
    }
}
