package multiply_strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
    public String multiply(String num1, String num2) {
        List<Integer> result = new ArrayList<>();
        List<Integer> reversedNum1 = Arrays.stream(num1.split(""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        Collections.reverse(reversedNum1);

        List<Integer> reversedNum2 = Arrays.stream(num2.split(""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        Collections.reverse(reversedNum2);

        for (int i = 0; i < reversedNum2.size(); i++) {
            List<Integer> productDigits = new ArrayList<>();
            for (int k = 0; k < i; k++) {
                productDigits.add(0);
            }
            int cash = 0;
            for (Integer num : reversedNum1) {
                int product = num * reversedNum2.get(i) + cash;
                productDigits.add(product % 10);
                cash = product / 10;
            }
            if (cash > 0) {
                productDigits.add(cash);
            }
            result = plusStrings(result, productDigits);
        }

        if (result.stream().allMatch(x -> x == 0)) {
            result = List.of(0);
        }

        Collections.reverse(result);
        return result.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(""));
    }

    private List<Integer> plusStrings(List<Integer> num1, List<Integer> num2) {
        int cash = 0;
        List<Integer> result = new ArrayList<>();
        List<Integer> longestStr = num1.size() > num2.size() ? num1: num2;
        List<Integer> shortestStr = num1.size() <= num2.size() ? num1: num2;

        for (int i = 0; i < longestStr.size(); i++) {
            int num2Value = i < shortestStr.size() ? shortestStr.get(i) : 0;
            int sum = longestStr.get(i) + num2Value + cash;
            result.add(sum % 10);
            cash = sum / 10;
        }
        if (cash > 0) {
            result.add(cash);
        }

        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.multiply("237", "284"));
    }
}
