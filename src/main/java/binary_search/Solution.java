package binary_search;

public class Solution {
    public int search(int[] nums, int target) {
        int rightLimit = nums.length;
        int leftLimit = -1;
        int currentIndex = rightLimit / 2;
        int result = target == nums[currentIndex] ? currentIndex : -1;

        while(target != nums[currentIndex] && currentIndex >= leftLimit + 1 && currentIndex <= rightLimit - 1) {
            if (target > nums[currentIndex]) {
                leftLimit = currentIndex;
                currentIndex += (rightLimit - currentIndex) / 2;
            } else if (target < nums[currentIndex]) {
                rightLimit = currentIndex;
                currentIndex -= (currentIndex - leftLimit) / 2;
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
