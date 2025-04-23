package make_array_non_decreasing;

public class Solution {
    public int maximumPossibleSize(int[] nums) {
        int result = 0;
        int maxNum = 0;

        for (int num : nums) {
            if (num >= maxNum) {
                maxNum = num;
                result++;
            }
        }

        return result;
    }
}
