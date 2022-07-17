package binary_search;

public class Solution {
    public int search(int[] nums, int target) {
        int rightLimit = nums.length - 1;
        int leftLimit = 0;
        int currentIndex = rightLimit / 2;
        int result = -1;

        while (target != nums[currentIndex] && currentIndex >= leftLimit && currentIndex <= rightLimit) {
            if (target > nums[currentIndex]) {
                leftLimit = currentIndex + 1;
                currentIndex = (rightLimit + leftLimit) / 2;
            } else if (target < nums[currentIndex]) {
                rightLimit = currentIndex - 1;
                currentIndex = (rightLimit + leftLimit) / 2;
            } else {
                result = currentIndex;
            }
        }

        if (target == nums[currentIndex]) {
            result = currentIndex;
        }

        return result;
    }
}
