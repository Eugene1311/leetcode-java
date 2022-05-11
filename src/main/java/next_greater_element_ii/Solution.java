package next_greater_element_ii;

import java.util.*;

public class Solution {
    public int[] nextGreaterElements(int[] nums) {
        Stack<Integer> descendingStack = new Stack<>();
        Deque<Integer> ascendingDeque = new LinkedList<>();
        int[] result = new int[nums.length];
        Arrays.fill(result, -1);

        ascendingDeque.add(nums[0]);

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                result[i - 1] = nums[i];
                while (!descendingStack.isEmpty() && nums[descendingStack.peek()] < nums[i]) {
                    int index = descendingStack.pop();
                    result[index] = nums[i];
                }
            } else {
                descendingStack.push(i - 1);
            }
            if (nums[i] > ascendingDeque.getLast()) {
                ascendingDeque.add(nums[i]);
            }
        }
        descendingStack.push(nums.length - 1);

        while (!descendingStack.isEmpty() && !ascendingDeque.isEmpty()) {
            if (nums[descendingStack.peek()] < ascendingDeque.peek()) {
                int index = descendingStack.pop();
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
