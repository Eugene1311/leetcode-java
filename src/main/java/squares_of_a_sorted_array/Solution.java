package squares_of_a_sorted_array;

import java.util.Arrays;

public class Solution {
    public int[] sortedSquares(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int[] result = new int[nums.length];
        int resultIndex = nums.length - 1;

        while (left <= right) {
            int leftValue = nums[left];
            int rightValue = nums[right];
            if (Math.abs(leftValue) > Math.abs(rightValue)) {
                result[resultIndex] = leftValue * leftValue;
                left++;
            } else {
                result[resultIndex] = rightValue * rightValue;
                right--;
            }
            resultIndex--;
        }

        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.sortedSquares(new int[]{-4, -1, 0, 3, 10})));
    }
}
