package valid_anagram;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> sCharToCount = new HashMap<>();
        Map<Character, Integer> tCharToCount = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            sCharToCount.compute(s.charAt(i), (k, v) -> (v == null) ? 1 : v + 1);
            tCharToCount.compute(t.charAt(i), (k, v) -> (v == null) ? 1 : v + 1);
        }

        return sCharToCount.equals(tCharToCount);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isAnagram("cta", "cat"));
    }
}
