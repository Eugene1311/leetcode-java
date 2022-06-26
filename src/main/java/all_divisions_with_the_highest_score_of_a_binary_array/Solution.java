package all_divisions_with_the_highest_score_of_a_binary_array;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Solution {
    public List<Integer> maxScoreIndices(int[] nums) {
        int zerosCount = 0;
        long onesCount = IntStream.of(nums)
                .filter(x -> x == 1)
                .count();
        long[] scores = new long[nums.length + 1];
        scores[0] = zerosCount + onesCount;
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            int elem = nums[i];
            if (elem == 0) {
                zerosCount += 1;
            }
            if (elem == 1) {
                onesCount -= 1;
            }
            var score = zerosCount + onesCount;
            scores[i + 1] = score;
        }

        long maxScore = LongStream.of(scores)
                .max()
                .orElseThrow();

        for(int i = 0; i < scores.length; i++) {
            if (scores[i] == maxScore) {
                result.add(i);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxScoreIndices(new int[]{0, 0, 1, 0}));
    }
}
