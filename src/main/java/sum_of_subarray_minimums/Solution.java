package sum_of_subarray_minimums;

import java.util.Arrays;
import java.util.Stack;

public class Solution {
    public static int sumSubarrayMinimums(int[] arr) {
        // initialize previous less element and next less element of
        // each element in the array
        Stack<int[]> previousLess = new Stack<>();
        Stack<int[]> nextLess = new Stack<>();
        // distance to Previous Less Elements
        int[] left = new int[arr.length];
        for(int i = 0; i < arr.length; i++) {
            left[i] =  i + 1;
        }
        // distance to Next Less Elements
        int[] right = new int[arr.length];
        for(int i = 0; i < arr.length; i++) {
            right[i] = arr.length - i;
        }

        for (int i = 0; i < arr.length; i++) {
            while (!previousLess.isEmpty() && previousLess.peek()[0] > arr[i]) {
                previousLess.pop();
            }

            left[i] = previousLess.isEmpty() ? i + 1 : i - previousLess.peek()[1];
            previousLess.push(new int[]{arr[i], i});
        }

        for (int i = 0; i < arr.length; i++) {
            while (!nextLess.isEmpty() && nextLess.peek()[0] > arr[i]) {
                int[] next = nextLess.peek();
                right[next[1]] = i - next[1];
                nextLess.pop();
            }

            nextLess.push(new int[]{arr[i], i});
        }
        System.out.println(Arrays.toString(left));
        System.out.println(Arrays.toString(right));

        long ans = 0;
        int mod = 1_000_000_007;
        for (int i = 0; i < arr.length; i++) {
            ans = (ans + (long) arr[i] * left[i] * right[i]) % mod;
        }
        return (int) ans;
    }

    public static void main(String[] args) {
        System.out.println(sumSubarrayMinimums(new int[]{71,55,82,55}));
    }
}
