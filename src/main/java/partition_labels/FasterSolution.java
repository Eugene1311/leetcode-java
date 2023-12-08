package partition_labels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FasterSolution {
    public List<Integer> partitionLabels(String s) {
        Map<String, Integer> letterToLastOccurrence = new HashMap<>();
        String[] letters = s.split("");

        for (int i = 0; i < s.length(); i++) {
            letterToLastOccurrence.put(letters[i], i);
        }

        List<Integer> partitionLabels = new ArrayList<>();

        int left = 0;
        int right = 0;
        for (int i = 0; i < s.length(); i++) {
            Integer lastOccurrence = letterToLastOccurrence.get(letters[i]);
            if (i <= right) {
                right = Math.max(right, lastOccurrence);
            } else {
                partitionLabels.add(right - left + 1);
                left = i;
                right = lastOccurrence;
            }
        }
        partitionLabels.add(right - left + 1);

        return partitionLabels;
    }

    public static void main(String[] args) {
        FasterSolution solution = new FasterSolution();
        System.out.println(solution.partitionLabels("ababcbacadefegdehijhklij"));
        System.out.println(solution.partitionLabels("eccbbbbdec"));
    }
}
