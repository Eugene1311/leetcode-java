package buddy_strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TooComplexSolution {
    public boolean buddyStrings(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }
        Map<Character, Integer> charToCount = new HashMap<>();

        List<Character> sUnmatched = new ArrayList<>();
        List<Character> goalUnmatched = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            char sLetter = s.charAt(i);
            char goalLetter = goal.charAt(i);
            if (sLetter != goalLetter) {
                sUnmatched.add(sLetter);
                goalUnmatched.add(goalLetter);
            } else {
                charToCount.compute(sLetter, (k, v) -> (v == null) ? 1 : v + 1);
            }
        }

        boolean doesNotContainSameLetters = charToCount.values().stream()
                .filter(value -> value >= 2)
                .toList()
                .isEmpty();
        if (s.equals(goal) && !doesNotContainSameLetters) {
            return true;
        }

        return compareUnmatched(sUnmatched, goalUnmatched);
    }

    private boolean compareUnmatched(List<Character> sUnmatched, List<Character> goalUnmatched) {
        if (sUnmatched.size() != 2) {
            return false;
        }

        return sUnmatched.get(0).equals(goalUnmatched.get(1)) && sUnmatched.get(1).equals(goalUnmatched.get(0));
    }

    public static void main(String[] args) {
        TooComplexSolution solution = new TooComplexSolution();
        System.out.println(solution.buddyStrings("abcaa", "abcbb"));
        System.out.println(solution.buddyStrings("ab", "ca"));
        System.out.println(solution.buddyStrings("abab", "baba"));
    }
}
