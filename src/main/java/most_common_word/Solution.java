package most_common_word;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        Set<String> bannedSet = new HashSet<>(Arrays.asList(banned));

        Map<String, Long> wordToCount = Arrays.stream(paragraph.toLowerCase().split("[ !?',;.]"))
                .map(String::trim)
                .filter(word -> !word.isEmpty())
                .filter(word -> !bannedSet.contains(word))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return wordToCount.entrySet().stream()
                .max(Comparator.comparingLong(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElseThrow();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.mostCommonWord("a, a, a, a, b,b,b,c, c", new String[]{"a"}));
        System.out.println(solution.mostCommonWord("Bob hit a ball, the hit BALL flew far after it was hit.", new String[]{"hit"}));
    }
}
