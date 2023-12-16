package partition_labels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public List<Integer> partitionLabels(String s) {
        Map<String, Integer> letterToLastOccurrence = new HashMap<>();
        String[] letters = s.split("");

        for (int i = 0; i < s.length(); i++) {
            letterToLastOccurrence.put(letters[i], i);
        }

        List<List<Integer>> ranges = new ArrayList<>();
        ranges.add(Arrays.asList(0, 0));
        for (int i = 0; i < s.length(); i++) {
            List<Integer> lastRange = ranges.get(ranges.size() - 1);
            Integer lastOccurrence = letterToLastOccurrence.get(letters[i]);
            if (i <= lastRange.get(1)) {
                lastRange.set(1, Math.max(lastRange.get(1), lastOccurrence));
            } else {
                ranges.add(Arrays.asList(i, lastOccurrence));
            }
        }

        return ranges.stream()
                .map(range -> range.get(1) - range.get(0) + 1)
                .toList();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.partitionLabels("ababcbacadefegdehijhklij"));
    }
}
