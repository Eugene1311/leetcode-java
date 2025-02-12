package three_sum;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> passedTriples = new HashSet<>();

        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int startPosition = i + 1;
            int endPosition = nums.length - 1;
            int target = -nums[i];
            while (startPosition < endPosition) {
                int sum = nums[startPosition] + nums[endPosition];
                if (sum == target) {
                    passedTriples.add(List.of(nums[i], nums[startPosition], nums[endPosition]));
                }
                if (sum > target) {
                    endPosition--;
                } else {
                    startPosition++;
                }
            }
        }

        return passedTriples.stream()
                .toList();
    }
}
