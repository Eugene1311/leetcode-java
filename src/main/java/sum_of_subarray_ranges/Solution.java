package sum_of_subarray_ranges;

import java.util.Arrays;
import java.util.Stack;
import java.util.function.BiPredicate;

public class Solution {
    public static int sumSubarrayMinimums(int[] arr) {
        return sumSubarray(arr, (a, b) -> a < b) - sumSubarray(arr, (a, b) -> a > b);
    }

    public static int sumSubarray(int[] arr, BiPredicate<Integer, Integer> compare) {
        // initialize previous less element and next less element of
        // each element in the array
        Stack<int[]> previous = new Stack<>();
        Stack<int[]> next = new Stack<>();
        // distance to Previous Less Elements
        int[] left = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            left[i] = i + 1;
        }
        // distance to Next Less Elements
        int[] right = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            right[i] = arr.length - i;
        }

        for (int i = 0; i < arr.length; i++) {
            while (!previous.isEmpty() && compare.test(previous.peek()[0], arr[i])) {
                previous.pop();
            }

            left[i] = previous.isEmpty() ? i + 1 : i - previous.peek()[1];
            previous.push(new int[]{arr[i], i});
        }

        for (int i = 0; i < arr.length; i++) {
            while (!next.isEmpty() && compare.test(next.peek()[0], arr[i])) {
                int[] nextEl = next.peek();
                right[nextEl[1]] = i - nextEl[1];
                next.pop();
            }

            next.push(new int[]{arr[i], i});
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
        System.out.println(sumSubarrayMinimums(new int[]{4, -2, -3, 4, 1}));
    }
}
