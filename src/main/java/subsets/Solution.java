package subsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(List.of());

        for (int i = 0; i < nums.length; i++) {
            List<Integer> subset = new ArrayList<>();
            subset.add(nums[i]);
            List<Integer> subList = Arrays.stream(nums).skip(i + 1).boxed().toList();
            result.addAll(getSubSets(subset, subList));
        }

        return result;
    }

    private List<List<Integer>> getSubSets(List<Integer> nums, List<Integer> rest) {
        if (rest.isEmpty()) {
            List<List<Integer>> result = new ArrayList<>();
            result.add(nums);
            return result;
        }

        List<List<Integer>> result = new ArrayList<>();
        result.add(nums);

        for (int i = 0; i < rest.size(); i++) {
            List<Integer> subset = new ArrayList<>(nums);
            subset.add(rest.get(i));
            List<List<Integer>> sets = getSubSets(subset, rest.subList(i + 1, rest.size()));
            result.addAll(sets);
        }

        return result;
    }

    public static void main(String[] args) {
//        int[] nums = {1, 2, 3, 4, 5};
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 10, 0};

        Solution solution = new Solution();
        System.out.println(solution.subsets(nums));
    }
}
