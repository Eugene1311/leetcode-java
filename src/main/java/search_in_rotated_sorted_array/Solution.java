package search_in_rotated_sorted_array;

public class Solution {
    public int search(int[] nums, int target) {
        int rightLimit = nums.length - 1;
        int leftLimit = 0;
        int currentIndex = rightLimit / 2;
        int result = -1;

        while (target != nums[currentIndex] && currentIndex >= leftLimit && currentIndex <= rightLimit) {
            if (target > nums[currentIndex]) {
                if (nums[currentIndex] < nums[rightLimit] && target > nums[rightLimit]) {
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
                result = currentIndex;
            }
        }

        if (target == nums[currentIndex]) {
            result = currentIndex;
        }

        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
        System.out.println(solution.search(new int[]{6, 7, 0, 1, 2}, 7));
        System.out.println(solution.search(new int[]{3, 1}, 1));
        System.out.println(solution.search(new int[]{2, 5}, 5));
        System.out.println(solution.search(new int[]{5, 1, 3}, 3));
        System.out.println(solution.search(new int[]{4, 5, 6, 7, 8, 1, 2, 3}, 8));
        System.out.println(solution.search(new int[]{7, 8, 1, 2, 3}, 8));
        System.out.println(solution.search(new int[]{5, 1, 2, 3, 4}, 1));
    }
}
