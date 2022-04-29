package add_binary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
    public String addBinary(String a, String b) {
        int carry = 0;
        List<Integer> result = new ArrayList<>();
        List<Integer> aDigits = Arrays.stream(a.split(""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        Collections.reverse(aDigits);

        List<Integer> bDigits = Arrays.stream(b.split(""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        Collections.reverse(bDigits);

        List<Integer> longList = aDigits.size() >= bDigits.size() ? aDigits : bDigits;
        List<Integer> shortList = aDigits.size() < bDigits.size() ? aDigits : bDigits;

        for (int i = 0; i < longList.size(); i++) {
            int bValue = i < shortList.size() ? shortList.get(i) : 0;
            int sum = longList.get(i) + bValue + carry;
            result.add(sum % 2);
            carry = sum / 2;
        }

        if (carry > 0) {
            result.add(carry);
        }

        Collections.reverse(result);
        return result.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(""));
    }
}
