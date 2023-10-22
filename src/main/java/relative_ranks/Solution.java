package relative_ranks;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {
    public String[] findRelativeRanks(int[] score) {
        Map<Integer, String> firstPlaces = Map.of(
                1, "Gold Medal",
                2, "Silver Medal",
                3, "Bronze Medal"
        );
        Queue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>(
                Comparator.comparing(entry -> -entry.getValue())
        );
        Map<Integer, Integer> indexToValue = new HashMap<>();
        for (int i = 0; i < score.length; i++) {
            indexToValue.put(i, score[i]);
        }
        queue.addAll(indexToValue.entrySet());

        String[] answers = new String[score.length];
        int place = 1;
        while (!queue.isEmpty()) {
            Map.Entry<Integer, Integer> entry = queue.poll();
            System.out.println(entry);
            int index = entry.getKey();
            if (place < 4) {
                answers[index] = firstPlaces.get(place);
            } else {
                answers[index] = String.valueOf(place);
            }
            place++;
        }

        return answers;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.findRelativeRanks(new int[]{10, 3, 8, 9, 4})));
    }
}
