package merge_sorted_array;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (m == 0) {
            System.arraycopy(nums2, 0, nums1, 0, nums1.length);
            return;
        }
        if (n == 0) {
            return;
        }

        int j = 0;
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            if (j >= n) {
                if (!queue.isEmpty()) {
                    int current = nums1[i];
                    nums1[i] = queue.poll();
                    queue.add(current);
                }
            } else {
                if (!queue.isEmpty()) {
                    int num2 = nums2[j];
                    int num1 = queue.peek();
                    int current = nums1[i];
                    if (num2 > num1) {
                        nums1[i] = queue.poll();
                    } else {
                        nums1[i] = num2;
                        j++;
                    }
                    queue.add(current);

                } else if (nums1[i] > nums2[j]){
                    int current = nums1[i];
                    nums1[i] = nums2[j];
                    queue.add(current);
                    j++;
                }
            }
        }
        for (int i = m; i < nums1.length; i++) {
            if (j >= n && !queue.isEmpty()) {
                nums1[i] = queue.poll();
            } else if (queue.isEmpty()) {
                nums1[i] = nums2[j];
                j++;
            } else if (nums2[j] <= queue.peek()) {
                nums1[i] = nums2[j];
                j++;
            } else {
                nums1[i] = queue.poll();
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[]{0, 0, 3, 0, 0, 0, 0, 0, 0};
        solution.merge(
                arr,
                3,
                new int[]{-1, 1, 1, 1, 2, 3},
                6
        );
        System.out.println(Arrays.toString(arr));
    }
}
