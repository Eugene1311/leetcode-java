package two_sum;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

public class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, List<Integer>> numToIndexes = IntStream.range(0, nums.length)
                .mapToObj(i -> List.of(nums[i], i))
                .collect(Collectors.groupingBy(List::getFirst, mapping(l -> l.get(1), toList())));

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num * 2 == target) {
                if (numToIndexes.get(num).size() == 2) {
                    return numToIndexes.get(num).stream()
                            .mapToInt(x -> x)
                            .toArray();
                }
            } else if (numToIndexes.containsKey(target - num)) {
                return new int[]{i, numToIndexes.get(target - num).getFirst()};
            }
        }

        return new int[]{};
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.twoSum(new int[]{3, 2, 4}, 6)));
    }
}
