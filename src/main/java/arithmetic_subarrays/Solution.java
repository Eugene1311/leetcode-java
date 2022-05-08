package arithmetic_subarrays;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        List<Integer> integers = Arrays.stream(nums)
                .boxed()
                .collect(Collectors.toList());
        List<Boolean> result = new ArrayList<>();

        for (int i = 0; i < l.length; i++) {
            List<Integer> sortedSubarray = integers.subList(l[i], r[i] + 1).stream()
                    .sorted()
                    .collect(Collectors.toList());
            int diff = sortedSubarray.get(1) - sortedSubarray.get(0);
            result.add(
                IntStream.range(1, sortedSubarray.size())
                    .allMatch(index -> sortedSubarray.get(index) - sortedSubarray.get(index - 1) == diff)
            );
        }

        return result;
    }
}
