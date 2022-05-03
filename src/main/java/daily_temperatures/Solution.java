package daily_temperatures;

import java.util.Stack;

public class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> nextGreater = new Stack<>();
        // distance to Next Greater Elements
        int[] right = new int[temperatures.length];

        for (int i = 0; i < temperatures.length - 1; i++) {
            if (temperatures[i] >= temperatures[i + 1]) {
                nextGreater.push(i);
            } else {
                right[i] = 1;
                while (!nextGreater.isEmpty() && temperatures[nextGreater.peek()] < temperatures[i + 1]) {
                    int lessIndex = nextGreater.pop();
                    right[lessIndex] = i - lessIndex + 1;
                }
            }
        }

        return right;
    }
}
