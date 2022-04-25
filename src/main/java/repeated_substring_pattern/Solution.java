package repeated_substring_pattern;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    // 	1134 ms	675.3 MB
    public boolean repeatedSubstringPattern(String s) {
        if (s == null) {
            return false;
        }
        if (s.isBlank() || s.length() < 2) {
            return false;
        }

        String firstLetter = s.substring(0, 1);

        if (s.equals(firstLetter.repeat(s.length()))) {
            return true;
        }

        char lastLetter = s.charAt(s.length() - 1);
        List<String> matches = new ArrayList<>();
        String current = firstLetter;

        for (int i = 1; i < s.length() - 1; i++) {
            current = current.concat(s.substring(i, i + 1));
            if (s.charAt(i) == lastLetter) {
                matches.add(current);
            }
        }

        return matches.stream()
                .anyMatch(match -> s.equals(match.repeat(s.length() / match.length())));
    }

    // 74 ms	50 MB
    public boolean repeatedSubstringPatternFast(String s) {
        if (s == null) {
            return false;
        }
        return s.repeat(2).substring(1, s.length() * 2 - 1).contains(s);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.repeatedSubstringPatternFast("ababab"));
    }
}
