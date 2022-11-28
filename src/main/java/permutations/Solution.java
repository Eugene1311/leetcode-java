package permutations;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();
        process(nums, tempList, results, nums.length);

        return results;
    }

    private void process(int[] nums, List<Integer> tempList, List<List<Integer>> results, int resultSize) {
        if (tempList.size() == resultSize) {
            results.add(new ArrayList<>(tempList));
        } else {
            for (int num : nums) {
                if (tempList.contains(num)) {
                    continue;
                }
                tempList.add(num);
                process(nums, tempList, results, resultSize);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.permute(new int[]{1, 2, 3}));
    }
}
