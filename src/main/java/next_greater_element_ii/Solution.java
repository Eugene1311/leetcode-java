package next_greater_element_ii;

import java.util.*;

public class Solution {
    public int[] nextGreaterElements(int[] nums) {
        Stack<Integer> descendingIndexes = new Stack<>();
        Deque<Integer> ascendingDeque = new LinkedList<>();
        int[] result = new int[nums.length];
        Arrays.fill(result, -1);

        ascendingDeque.add(nums[0]);

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                result[i - 1] = nums[i];
                while (!descendingIndexes.isEmpty() && nums[descendingIndexes.peek()] < nums[i]) {
                    int index = descendingIndexes.pop();
                    result[index] = nums[i];
                }
            } else {
                descendingIndexes.push(i - 1);
            }
            if (nums[i] > ascendingDeque.getLast()) {
                ascendingDeque.add(nums[i]);
            }
        }
        descendingIndexes.push(nums.length - 1);

        while (!descendingIndexes.isEmpty() && !ascendingDeque.isEmpty()) {
            if (nums[descendingIndexes.peek()] < ascendingDeque.peek()) {
                int index = descendingIndexes.pop();
                result[index] = ascendingDeque.peek();
            } else {
                ascendingDeque.pop();
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.nextGreaterElements(new int[]{100, 1, 11, 1, 120, 111, 123, 1, -1, -100})));
    }
}
