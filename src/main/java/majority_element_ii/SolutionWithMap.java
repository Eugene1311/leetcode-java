package majority_element_ii;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SolutionWithMap {
    public List<Integer> majorityElement(int[] nums) {
        if (nums.length < 3) {
            return Arrays.stream(nums)
                    .boxed()
                    .toList();
        }

        int size = nums.length / 3;
        Map<Integer, Integer> numToOccurrence = new HashMap<>();

        for (int num : nums) {
            numToOccurrence.compute(num, (k, v) -> v == null ? 1 : v + 1);
        }

        return numToOccurrence.entrySet().stream()
                .filter(entry -> entry.getValue() > size)
                .map(Map.Entry::getKey)
                .toList();
    }

    public static void main(String[] args) {
        SolutionWithMap solution = new SolutionWithMap();
    }
}
