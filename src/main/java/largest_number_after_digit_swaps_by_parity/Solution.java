package largest_number_after_digit_swaps_by_parity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Solution {
    public int largestInteger(int num) {
        Map<Integer, Integer> odds = new HashMap<>();
        Map<Integer, Integer> evens = new HashMap<>();
        String[] digits = String.valueOf(num).split("");
        for (int i = 0; i < digits.length; i++) {
            int number = Integer.parseInt(digits[i]);
            if (number % 2 == 1) {
                odds.put(i, number);
            } else {
                evens.put(i, number);
            }
        }

        List<Integer> sortedOdds = odds.values()
                .stream()
                .sorted((a, b) -> b - a)
                .collect(Collectors.toList());
        List<Integer> sortedEvens = evens.values()
                .stream()
                .sorted((a, b) -> b - a)
                .collect(Collectors.toList());

        Iterator<Integer> oddsIterator = sortedOdds.iterator();
        odds.entrySet()
                .forEach(entry -> entry.setValue(oddsIterator.next()));
        Iterator<Integer> evensIterator = sortedEvens.iterator();
        evens.entrySet()
                .forEach(entry -> entry.setValue(evensIterator.next()));

        String result = "";
        for (int i = 0; i < digits.length; i++) {
            if (odds.containsKey(i)) {
                result = result.concat(String.valueOf(odds.get(i)));
            } else if (evens.containsKey(i)) {
                result = result.concat(String.valueOf(evens.get(i)));
            }
        }

        return Integer.parseInt(result);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.largestInteger(1234));
    }
}
