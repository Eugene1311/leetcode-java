package find_peak_element;

public class Solution {
    public int findPeakElement(int[] nums) {
        int i = nums.length / 2;
        int left = i > 0 ? nums[i - 1] : (int) Float.NEGATIVE_INFINITY;
        int right = i < nums.length - 1 ? nums[i + 1] : (int) Float.NEGATIVE_INFINITY;

        while (left > nums[i] || nums[i] < right) {
            if (left > nums[i]) {
                i = i - 1;
                if (i == 0) {
                    return i;
                }
            } else if (nums[i] < right) {
                i = i + 1;
                if (i == nums.length - 1) {
                    return i;
                }
            }
            left = nums[i - 1];
            right = nums[i + 1];
        }

        return i;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findPeakElement(new int[]{1, 2, 1, 3, 5, 6, 4}));
        System.out.println(solution.findPeakElement(new int[]{1}));
        System.out.println(solution.findPeakElement(new int[]{1, 2}));
        System.out.println(solution.findPeakElement(new int[]{2, 1}));
    }
}
