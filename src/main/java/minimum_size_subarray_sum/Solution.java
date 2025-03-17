package minimum_size_subarray_sum;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int[] prefixSum = new int[nums.length + 1];
        prefixSum[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            prefixSum[i + 1] = nums[i] + prefixSum[i];
        }

        int leftPointer = 0;
        Set<Integer> lengths = new HashSet<>();
        for (int i = 0; i < prefixSum.length; i++) {
            if (prefixSum[i] - prefixSum[leftPointer] >= target) {
                lengths.add(i - leftPointer);
                leftPointer++;

                while (prefixSum[i] - prefixSum[leftPointer] >= target) {
                    lengths.add(i - leftPointer);
                    leftPointer++;
                }
            }
        }

        return lengths.stream()
                .mapToInt(x -> x)
                .min()
                .orElse(0);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
    }
}
