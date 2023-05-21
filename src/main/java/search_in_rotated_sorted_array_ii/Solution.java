package search_in_rotated_sorted_array_ii;

import java.util.Arrays;

public class Solution {
    public boolean search(int[] nums, int target) {
        int rightLimit = nums.length - 1;
        int leftLimit = 0;

        return checkIfContains(nums, target, leftLimit, rightLimit);
    }

    private boolean checkIfContains(int[] nums, int target, int leftLimit, int rightLimit) {
//        System.out.println(Arrays.toString(nums) + ", " + leftLimit + ", " + rightLimit);
        if (leftLimit == rightLimit) {
            return nums[leftLimit] == target;
        }
        int currentIndex = (leftLimit + rightLimit) / 2;
        boolean result = false;

        while (target != nums[currentIndex] && currentIndex >= leftLimit && currentIndex <= rightLimit) {
            if (nums[currentIndex] == nums[leftLimit] && nums[currentIndex] == nums[rightLimit]) {
                return checkIfContains(nums, target, leftLimit, currentIndex - 1) || checkIfContains(nums, target, currentIndex + 1, rightLimit);
            }
            if (target > nums[currentIndex]) {
                if (nums[currentIndex] <= nums[rightLimit] && target > nums[rightLimit]) {
                    rightLimit = currentIndex - 1;
                } else {
                    leftLimit = currentIndex + 1;
                }
                currentIndex = (rightLimit + leftLimit) / 2;
            } else if (target < nums[currentIndex]) {
                if (nums[currentIndex] >= nums[leftLimit] && target < nums[leftLimit]) {
                    leftLimit = currentIndex + 1;
                } else {
                    rightLimit = currentIndex - 1;
                }

                currentIndex = (rightLimit + leftLimit) / 2;
            } else {
                result = true;
            }
        }

        if (target == nums[currentIndex]) {
            result = true;
        }

        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.search(new int[]{2, 5, 6, 0, 0, 1, 2}, 0));
        System.out.println(solution.search(new int[]{2, 5, 6, 0, 0, 1, 2}, 3));
        System.out.println(solution.search(new int[]{1, 0, 1, 1, 1}, 0));
        System.out.println(solution.search(new int[]{3, 1}, 1));
        System.out.println(solution.search(new int[]{2, 5}, 5));
        System.out.println(solution.search(new int[]{5, 1, 3}, 3));
        System.out.println(solution.search(new int[]{4, 5, 6, 7, 8, 1, 2, 3}, 8));
        System.out.println(solution.search(new int[]{7, 8, 1, 2, 3}, 8));
        System.out.println(solution.search(new int[]{5, 1, 2, 3, 4}, 1));
        System.out.println(solution.search(new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1}, 2));
        System.out.println(solution.search(new int[]{3, 1, 1}, 3));
    }
}
