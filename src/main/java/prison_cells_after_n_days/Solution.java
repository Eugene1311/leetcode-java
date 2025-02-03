package prison_cells_after_n_days;

import java.util.*;

public class Solution {
    public int[] prisonAfterNDays(int[] cells, int n) {
        Map<Integer, int[]> numToCells = new HashMap<>();
        List<int[]> possibleResults = new ArrayList<>();
        int[] nextCells = new int[cells.length];

        int i = 0;
        int prevNum = -1;
        boolean done = false;
        while (i < n) {
            for (int j = 1; j < cells.length - 1; j++) {
                if (cells[j - 1] == cells[j + 1]) {
                    nextCells[j] = 1;
                } else {
                    nextCells[j] = 0;
                }
            }

            int num = 0;
            for (int j = 1; j < nextCells.length; j++) {
                num += nextCells[j] << (nextCells.length - j);
            }

            if (prevNum == num) {
                done = true;
                break;
            }
            prevNum = num;

            if (numToCells.containsKey(num)) {
                break;
            }

            int[] copy = Arrays.copyOf(nextCells, nextCells.length);
            cells = copy;
            numToCells.put(num, copy);
            possibleResults.add(copy);

            i++;
        }

        if (done) {
            return possibleResults.get(i - 1);
        } else if (n > possibleResults.size()) {
            int shift = n % possibleResults.size();
            return possibleResults.get(shift > 0 ? shift - 1 : possibleResults.size() - 1);
        } else {
            return possibleResults.get(n - 1);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // 0,0,1,1,0,0,0,0
        System.out.println(Arrays.toString(solution.prisonAfterNDays(new int[]{0, 1, 0, 1, 1, 0, 0, 1}, 7)));
        // 0, 1, 1, 0, 0, 0, 1, 0
        System.out.println(Arrays.toString(solution.prisonAfterNDays(new int[]{0, 1, 0, 1, 1, 0, 0, 0}, 1)));

//        Input: cells = [1,0,0,1,0,0,1,0], n = 1000000000
//        Output: [0,0,1,1,1,1,1,0]
        System.out.println(Arrays.toString(solution.prisonAfterNDays(new int[]{1, 0, 0, 1, 0, 0, 1, 0}, 1000000000)));

        // Input: cells = [1,1,0,1,1,0,0,0] , n = 1
        // Output: [0, 0, 1, 0, 0, 0, 1, 0]
        System.out.println(Arrays.toString(solution.prisonAfterNDays(new int[]{1, 1, 0, 1, 1, 0, 0, 0}, 1)));

        // Input: cells = [1,1,0,1,1,0,1,1] n = 6
        // Output: [0, 0, 1, 0, 0, 1, 0, 0]
        System.out.println(Arrays.toString(solution.prisonAfterNDays(new int[]{1, 1, 0, 1, 1, 0, 1, 1}, 6)));

        // Input: cells = [1,1,0,1,1,0,0,1] n = 300663720
        System.out.println(Arrays.toString(solution.prisonAfterNDays(new int[]{1, 1, 0, 1, 1, 0, 0, 1}, 300663720)));
    }
}
