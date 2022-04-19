package first_missing_positive;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Solution {
    // 22 ms	95.2 MB
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] sortedPositiveNums = IntStream.of(nums)
                .sorted()
                .filter(x -> x >= 0)
                .toArray();

        if ((sortedPositiveNums.length > 0 && sortedPositiveNums[0] > 1) || sortedPositiveNums.length == 0) {
            return 1;
        }

        for (int i = 0; i < sortedPositiveNums.length - 1; i++) {
            if (sortedPositiveNums[i + 1] - sortedPositiveNums[i] > 1) {
                return sortedPositiveNums[i] + 1;
            }
        }
        return sortedPositiveNums[sortedPositiveNums.length - 1] + 1;
    }

    // 69 ms	105.7 MB
    public int firstMissingPositiveWithHashing(int[] nums) {
        Set<Integer> numbers = Arrays.stream(nums)
                .boxed()
                .collect(Collectors.toSet());
        int max = numbers.stream().mapToInt(x -> x).max().orElseThrow();

        long absentNum = LongStream.range(1L, (long) max + 2L)
                .filter(x -> !numbers.contains((int) x))
                .findFirst()
                .orElse(1L);

        return (int) absentNum;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.firstMissingPositiveWithHashing(new int[]{
                2147483647, 2147483646, 2147483645, 3, 2, 1, -1, 0, -2147483648}));
    }
}
