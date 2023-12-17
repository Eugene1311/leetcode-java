package find_common_characters;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public List<String> commonChars(String[] words) {
        if (words.length == 1) {
            return Arrays.asList(words[0].split(""));
        }
        Map<String, Integer> charToCount = new HashMap<>();
        String fistWord = words[0];
        for (int i = 0; i < fistWord.length(); i++) {
            charToCount.compute(fistWord.substring(i, i+1), (k, v) -> v == null ? 1 : v + 1);
        }

        for (int i = 1; i < words.length; i++) {
            String word = words[i];
            Map<String, Integer> matchedCharToCount = new HashMap<>();
            for (int j = 0; j < word.length(); j++) {
                String letter = word.substring(j, j + 1);
                if (charToCount.containsKey(letter) && charToCount.get(letter) > 0) {
                    matchedCharToCount.compute(letter, (k, v) -> v == null ? 1 : v + 1);
                    charToCount.compute(letter, (k, v) -> v - 1);
                }
            }
            charToCount = matchedCharToCount;
        }

        return charToCount.entrySet().stream()
                .flatMap(entry -> Arrays.stream(entry.getKey().repeat(entry.getValue()).split("")))
                .toList();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.commonChars(new String[]{"bella", "label", "roller"}));
        System.out.println(solution.commonChars(new String[]{"cool", "lock", "cook"}));
    }
}
