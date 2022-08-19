package longest_substring_without_repeating_characters;

import java.util.*;

public class FasterSolution {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> visited = new HashSet<>();
        int leftPointer = 0;
        int result = 0;

        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            while (visited.contains(current)) {
                visited.remove(s.charAt(leftPointer));
                leftPointer++;
            }
            visited.add(current);
            result = Math.max(result, i - leftPointer + 1);
        }

        return result;
    }

    public static void main(String[] args) {
        FasterSolution solution = new FasterSolution();
        System.out.println(solution.lengthOfLongestSubstring("dvdf"));
        System.out.println(solution.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(solution.lengthOfLongestSubstring("bbbbb"));
        System.out.println(solution.lengthOfLongestSubstring("pwwkew"));
    }
}
