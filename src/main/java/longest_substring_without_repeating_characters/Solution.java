package longest_substring_without_repeating_characters;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solution {
    public int lengthOfLongestSubstring(String s) {
        String[] chars = s.split("");
        List<String> temp = new ArrayList<>();
        List<Integer> results = new ArrayList<>();

        for (String ch: chars) {
            if (ch.isEmpty()) {
                continue;
            }
            if (temp.contains(ch)) {
                results.add(temp.size());
                temp = temp.subList(temp.indexOf(ch) + 1, temp.size());
            }
            temp.add(ch);
        }
        results.add(temp.size());

        return results.stream()
                .max(Comparator.comparingInt(a -> a))
                .orElse(0);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.lengthOfLongestSubstring("dvdf"));
        System.out.println(solution.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(solution.lengthOfLongestSubstring("bbbbb"));
        System.out.println(solution.lengthOfLongestSubstring("pwwkew"));
    }
}
