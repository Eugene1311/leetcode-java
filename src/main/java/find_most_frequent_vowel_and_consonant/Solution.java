package find_most_frequent_vowel_and_consonant;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Solution {
    Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u');
    public int maxFreqSum(String s) {
        Map<Character, Integer> vowelToCount = new HashMap<>();
        Map<Character, Integer> consonantToCount = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (vowels.contains(c)) {
                vowelToCount.compute(c, (k, v) -> (v == null) ? 1 : v + 1);
            } else {
                consonantToCount.compute(c, (k, v) -> (v == null) ? 1 : v + 1);
            }
        }

        int maxVowelCount = vowelToCount.values().stream()
                .mapToInt(x -> x)
                .max()
                .orElse(0);
        int maxConsonantCount = consonantToCount.values().stream()
                .mapToInt(x -> x)
                .max()
                .orElse(0);

        return maxVowelCount + maxConsonantCount;
    }
}
