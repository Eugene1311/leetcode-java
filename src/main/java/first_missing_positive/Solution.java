package first_missing_positive;

import java.util.stream.IntStream;

public class Solution {
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] sortedPositiveNums = IntStream.of(nums)
                .sorted()
                .filter(x -> x >= 0)
                .toArray();

        if ((sortedPositiveNums.length > 0 && sortedPositiveNums[0] > 1) || sortedPositiveNums.length == 0) {
            return 1;
        }

        for (int i = 0; i < sortedPositiveNums.length - 1; i++) {
            if (sortedPositiveNums[i + 1] - sortedPositiveNums[i] > 1) {
                return sortedPositiveNums[i] + 1;
            }
        }
        return sortedPositiveNums[sortedPositiveNums.length - 1] + 1;
    }
}
