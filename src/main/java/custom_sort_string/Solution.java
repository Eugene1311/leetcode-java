package custom_sort_string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Solution {
    public String customSortString(String order, String s) {
        Map<Character, Integer> charToIndex = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            charToIndex.put(order.charAt(i), i);
        }

        List<List<Character>> substrings = new ArrayList<>();
        for (int i = 0; i <= charToIndex.keySet().size(); i++) {
            substrings.add(new ArrayList<>());
        }

        for (int i = 0; i < s.length(); i++) {
            char letter = s.charAt(i);
            if (charToIndex.containsKey(letter)) {
                substrings.get(charToIndex.get(letter)).add(letter);
            } else {
                substrings.get(substrings.size() - 1).add(letter);
            }
        }

        return substrings.stream()
                .flatMap(letters -> letters.stream().map(letter -> Character.toString(letter)))
                .collect(Collectors.joining(""));
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.customSortString("cba", "abcd"));
        System.out.println(solution.customSortString("cbafg", "abcd"));
    }
}
