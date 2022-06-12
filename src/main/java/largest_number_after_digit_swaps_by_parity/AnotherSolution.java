package largest_number_after_digit_swaps_by_parity;

import java.util.ArrayList;
import java.util.List;

public class AnotherSolution {
    public int largestInteger(int num) {
        String[] digits = String.valueOf(num).split("");
        List<Integer> odds = new ArrayList<>();
        List<Integer> evens = new ArrayList<>();
        for (String digit : digits) {
            int number = Integer.parseInt(digit);
            if (number % 2 == 1) {
                odds.add(number);
            } else {
                evens.add(number);
            }
        }
        List<Integer> sortedOdds = odds.stream().sorted((a, b) -> b - a).toList();
        int oddsPointer = 0;
        List<Integer> sortedEvens = evens.stream().sorted((a, b) -> b - a).toList();
        int evensPointer = 0;

        String result = "";
        for (int i = 0; i < digits.length; i++) {
            int number = Integer.parseInt(digits[i]);
            if (number % 2 == 1) {
                result = result.concat(String.valueOf(sortedOdds.get(oddsPointer)));
                oddsPointer++;
            } else {
                result = result.concat(String.valueOf(sortedEvens.get(evensPointer)));
                evensPointer++;
            }
        }

        return Integer.parseInt(result);
    }

    public static void main(String[] args) {
        AnotherSolution solution = new AnotherSolution();
        System.out.println(solution.largestInteger(1234));
    }
}
