package find_peak_element;

public class TrueLogNSolution {
    public int findPeakElement(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }

        int left = 0;
        int right = nums.length - 1;
        while (right - left > 1) {
            int mid = (right + left) / 2;
            if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        if (nums[left] > nums[right]) {
            return left;
        } else {
            return right;
        }
    }

    public static void main(String[] args) {
        TrueLogNSolution solution = new TrueLogNSolution();
        System.out.println(solution.findPeakElement(new int[]{1, 2, 1, 3, 5, 6, 4}));
        System.out.println(solution.findPeakElement(new int[]{1}));
        System.out.println(solution.findPeakElement(new int[]{1, 2}));
        System.out.println(solution.findPeakElement(new int[]{2, 1}));
    }
}
