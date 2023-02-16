package container_with_most_water;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    public int maxArea(int[] height) {
        Set<Integer> areas = new HashSet<>();
        for (int i = 0; i < height.length; i++) {
            int j = height.length - 1;
            while (j > i) {
                if (height[j] >= height[i]) {
                    areas.add(height[i] * (j - i));
                    break;
                }
                j--;
            }
            int k = 0;
            while (k < i) {
                if (height[k] >= height[i]) {
                    areas.add(height[i] * (i - k));
                    break;
                }
                k++;
            }
        }
        return areas.stream()
                .mapToInt(x -> x)
                .max()
                .orElseThrow();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
        System.out.println(solution.maxArea(new int[]{1, 1}));
        System.out.println(solution.maxArea(new int[]{1, 2, 1}));
    }
}
