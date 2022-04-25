package plus_one;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    public int[] plusOne(int[] digits) {
        List<Integer> result = new ArrayList<>();
        boolean isBiggerThanTen = false;

        for (int i = digits.length - 1; i >= 0; i--) {
            int newDigit;
            if (i == digits.length - 1) {
                newDigit = digits[i] + 1;
            } else {
                newDigit = isBiggerThanTen ? digits[i] + 1 : digits[i];
            }
            isBiggerThanTen = newDigit > 9;
            result.add(newDigit % 10);
        }

        if (isBiggerThanTen) {
            result.add(1);
        }

        Collections.reverse(result);
        return result.stream().mapToInt(x -> x).toArray();
    }
}
