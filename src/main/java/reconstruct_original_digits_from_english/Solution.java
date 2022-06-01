package reconstruct_original_digits_from_english;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    private final Map<String, Integer> wordToDigit = Map.of(
           "zero", 0,
           "one", 1,
           "two", 2,
           "three", 3,
           "four", 4,
           "five", 5,
           "six", 6,
           "seven", 7,
           "eight", 8,
           "nine", 9
    );
    private final Map<String, Integer> digitToCount = new HashMap<>();

    private final Map<String, Integer> wordToCount = new HashMap<>();

    public String originalDigits(String s) {
        wordToCount.put("zero", 0);
        wordToCount.put("one", 0);
        wordToCount.put("two", 0);
        wordToCount.put("three", 0);
        wordToCount.put("four", 0);
        wordToCount.put("five", 0);
        wordToCount.put("six", 0);
        wordToCount.put("seven", 0);
        wordToCount.put("eight", 0);
        wordToCount.put("nine", 0);

        digitToCount.put("z", 0);
        digitToCount.put("x", 0);
        digitToCount.put("u", 0);
        digitToCount.put("w", 0);
        digitToCount.put("g", 0);
        digitToCount.put("f", 0);
        digitToCount.put("o", 0);
        digitToCount.put("t", 0);
        digitToCount.put("s", 0);
        digitToCount.put("i", 0);
        digitToCount.put("n", 0);
        digitToCount.put("e", 0);
        digitToCount.put("r", 0);
        digitToCount.put("v", 0);
        digitToCount.put("h", 0);

        for (int i = 0; i < s.length(); i++) {
            String letter = s.substring(i, i + 1);
            digitToCount.compute(letter, (k, v) -> v == null ? 0 : v + 1);
        }

        wordToCount.compute("zero", (k, v) -> digitToCount.get("z"));
        wordToCount.compute("two", (k, v) -> digitToCount.get("w"));
        wordToCount.compute("four", (k, v) -> digitToCount.get("u"));
        wordToCount.compute("six", (k, v) -> digitToCount.get("x"));
        wordToCount.compute("eight", (k, v) -> digitToCount.get("g"));

        wordToCount.compute("one", (k, v) -> digitToCount.get("o") - digitToCount.get("z") - digitToCount.get("w") - digitToCount.get("u"));
        wordToCount.compute("three", (k, v) -> digitToCount.get("h") - digitToCount.get("g"));
        wordToCount.compute("five", (k, v) -> digitToCount.get("f") - digitToCount.get("u"));
        wordToCount.compute("seven", (k, v) -> digitToCount.get("s") - digitToCount.get("x"));
        wordToCount.compute("nine", (k, v) ->
                digitToCount.get("i") - digitToCount.get("x") - digitToCount.get("g") - wordToCount.get("five"));

        return wordToCount.entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 0)
                .flatMap(entry -> Arrays.stream(entry.getKey().concat(" ").repeat(entry.getValue()).split(" ")))
                .map(wordToDigit::get)
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.originalDigits("zerozero"));
    }
}
