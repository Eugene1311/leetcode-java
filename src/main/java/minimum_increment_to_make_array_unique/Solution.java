package minimum_increment_to_make_array_unique;

import java.util.*;
import java.util.stream.IntStream;

public class Solution {
    public int minIncrementForUnique(int[] nums) {
        if (nums == null || nums.length == 1) {
            return 0;
        }

        Set<Integer> unique = new HashSet<>();
        List<Integer> notUnique = new ArrayList<>();
        SortedSet<Integer> spaces = new TreeSet<>();

        Arrays.sort(nums);

        int max = nums[nums.length - 1];
        int result = 0;

        for (int i = 0; i < nums.length; i++) {
            if (!unique.add(nums[i])) {
                notUnique.add(nums[i]);
            }
            if (i > 0 && nums[i] - nums[i - 1] > 1) {
                spaces.addAll(IntStream.range(nums[i - 1] + 1, nums[i])
                        .boxed()
                        .toList());
            }
        }

        for (int num : notUnique) {
            while (!spaces.isEmpty() && num >= spaces.first()) {
                spaces.remove(spaces.first());
            }
            if (!spaces.isEmpty()) {
                int nextVal = spaces.first();
                result += nextVal - num;
                spaces.remove(spaces.first());
            } else {
                result += ++max - num;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minIncrementForUnique(new int[]{1, 2, 2})); // 1
        System.out.println(solution.minIncrementForUnique(new int[]{3, 2, 1, 2, 1, 7})); // 6
        System.out.println(solution.minIncrementForUnique(new int[]{0, 2, 2})); // 1
        System.out.println(solution.minIncrementForUnique(new int[]{0})); // 0
        System.out.println(solution.minIncrementForUnique(new int[]{2, 2, 2, 1})); // 3
        System.out.println(solution.minIncrementForUnique(new int[]{1, 3, 0, 3, 0})); // 3
        System.out.println(solution.minIncrementForUnique(new int[]{7, 2, 7, 2, 1, 4, 3, 1, 4, 8})); // 16
        System.out.println(solution.minIncrementForUnique(new int[]{1, 3, 4, 4, 4, 5, 7, 7, 8, 9})); // 12
        System.out.println(solution.minIncrementForUnique(new int[]{14, 4, 5, 14, 13, 14, 10, 17, 2, 12, 2, 14, 7, 13, 14, 13, 4, 16, 4, 10})); // 41
    }
}
