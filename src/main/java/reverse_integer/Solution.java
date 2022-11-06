package reverse_integer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// Beats 5.34% runtime
public class Solution {
    public int reverse(int x) {
        List<String> reversed = Arrays.asList(String.valueOf(x).replace("-", "").split(""));
        Collections.reverse(reversed);
        long reversedUnsigned = Long.parseLong(String.join("", reversed));
        int shortened = Integer.MAX_VALUE < reversedUnsigned ? 0 : (int) reversedUnsigned;
        return x > 0 ? shortened : -1 * shortened;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.reverse(-123));
        System.out.println(solution.reverse(123));
    }
}
