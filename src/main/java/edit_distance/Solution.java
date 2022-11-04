package edit_distance;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution {
    public int minDistance(String word1, String word2) {
        List<String> firstLetters = Arrays.asList(word1.split(""));

        List<String> secondLetters = Arrays.asList(word2.split(""));

        var index = firstLetters.size() - 1;
        var matchesCount = 0;
        var lastFirstIndex = 0;
        var lastSecondIndex = 0;
        for (int i = secondLetters.size() - 1; i >= 0; i--) {
            while (index >= 0 && !firstLetters.get(index).equals(secondLetters.get(i))) {
                index--;
            }

            if (index >= 0) {
                lastFirstIndex = index;
                lastSecondIndex = i;
                matchesCount++;
            }
        }

        int toChange = firstLetters.size() - matchesCount;

        return toChange + lastSecondIndex - lastFirstIndex;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minDistance("sea", "ate"));
        System.out.println(solution.minDistance("horse", "ros"));
        System.out.println(solution.minDistance("intention", "execution"));
    }
}
