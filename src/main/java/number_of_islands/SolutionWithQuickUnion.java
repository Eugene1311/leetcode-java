package number_of_islands;

import java.util.*;
import java.util.stream.Collectors;

public class SolutionWithQuickUnion {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int height = grid.length;
        int width = grid[0].length;
        List<Integer> ids = generateIds(grid);
        Set<Integer> roots = new HashSet<>(ids);
        List<Integer> sizes = ids.stream()
                .map(id -> 1)
                .collect(Collectors.toList());

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (grid[i][j] == '0') {
                    continue;
                }
                char rightNeighbour = j < width - 1 ? grid[i][j + 1] : '0';
                if (rightNeighbour == '1') {
                    union(i * width + j, i * width + j + 1, ids, roots, sizes);
                }
                char downNeighbour = i < height - 1 ? grid[i + 1][j] : '0';
                if (downNeighbour == '1') {
                    union(i * width + j, (i + 1) * width + j, ids, roots, sizes);
                }
            }
        }

        return (int) roots.stream()
                .filter(id -> id != -1).count();
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

    private void union(int p, int q, List<Integer> ids, Set<Integer> roots, List<Integer> sizes) {
        int pRoot = findRoot(ids, p);
        int qRoot = findRoot(ids, q);
        if (pRoot == qRoot) {
            return;
        } else if (sizes.get(pRoot) < sizes.get(qRoot)){
            ids.set(pRoot, qRoot);
            sizes.set(qRoot, sizes.get(qRoot) + sizes.get(pRoot));
            roots.remove(pRoot);
        } else {
            ids.set(qRoot, pRoot);
            sizes.set(pRoot, sizes.get(qRoot) + sizes.get(pRoot));
            roots.remove(qRoot);
        }
    }

    private int findRoot(List<Integer> ids, int i) {
        while (i != ids.get(i)) {
            ids.set(i, ids.get(ids.get(i)));
            i = ids.get(i);
        }
        return i;
    }

    public static void main(String[] args) {
        SolutionWithQuickUnion solution = new SolutionWithQuickUnion();
        System.out.println(solution.numIslands(new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        }));
//        System.out.println(solution.numIslands(new char[][]{
//                {'1', '1', '0', '0', '0'},
//                {'1', '1', '0', '0', '0'},
//                {'0', '0', '1', '0', '0'},
//                {'0', '0', '0', '1', '1'}
//        }));
//        System.out.println(solution.numIslands(new char[][]{
//                {'1', '1'}
//        }));
//        System.out.println(solution.numIslands(new char[][]{
//                {'1','0','1','1','1'},
//                {'1','0','1','0','1'},
//                {'1','1','1','0','1'}
//        }));
    }
}
