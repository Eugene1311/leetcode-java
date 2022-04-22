package first_missing_positive;

/*
    A O(n) time and O(1) extra space solution
    https://www.geeksforgeeks.org/find-the-smallest-positive-number-missing-from-an-unsorted-array/
 */
public class SolutionON {
    /* Find the smallest positive missing
       number in an array that contains
       both positive and negative integers */
    public int firstMissingPositive(int[] nums) {
        // First separate positive and
        // negative numbers
        int shift = segregate(nums);
        int[] arr2 = new int[nums.length - shift];
        int j = 0;
        for (int i = shift; i < nums.length; i++) {
            arr2[j] = nums[i];
            j++;
        }
        // Shift the array and call
        // findMissingPositive for
        // positive part
        return findMissingPositive(arr2, j);
    }

    /* Utility function that puts all non-positive
       (0 and negative) numbers on left side of
       arr[] and return count of such numbers */
    private int segregate(int[] arr) {
        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= 0) {
                int temp;
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                // increment count of non-positive
                // integers
                j++;
            }
        }

        return j;
    }

    /* Find the smallest positive missing
       number in an array that contains
       all positive integers */
    private int findMissingPositive(int[] arr, int size) {
        // Mark arr[i] as visited by making
        // arr[arr[i] - 1] negative. Note that
        // 1 is subtracted because index start
        // from 0 and positive numbers start from 1
        for (int i = 0; i < size; i++) {
            int x = Math.abs(arr[i]);
            if (x - 1 < size && arr[x - 1] > 0) {
                arr[x - 1] = -arr[x - 1];
            }
        }

        // Return the first index value at which
        // is positive
        for (int i = 0; i < size; i++) {
            if (arr[i] > 0) {
                return i + 1; // 1 is added because indexes start from 0
            }
        }

        return size + 1;
    }

    // Driver code
    public static void main(String[] args) {
        SolutionON solution = new SolutionON();
        int[] arr = {3, 4, -1, 1};
        int missing = solution.firstMissingPositive(arr);
        System.out.println("The smallest positive missing number is " + missing);
    }
}
