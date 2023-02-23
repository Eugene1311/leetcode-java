package divide_two_integers;

public class Solution {
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        boolean isNegative = (dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0);
        int absDividend = Math.abs(dividend);
        int absDivisor = Math.abs(divisor);
        int quotient = 0;
        int subtrahend = 0;

        for (int i = 31; i >= 0; i--) {
            subtrahend = (subtrahend << 1) + ((absDividend >>> i) & 1);
            if (subtrahend - absDivisor < 0) {
                quotient = quotient << 1;
            } else {
                quotient = (quotient << 1) + 1;
                subtrahend -= absDivisor;
            }
        }

        if (isNegative) {
            quotient = ~quotient + 1;
        }
        return quotient;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.divide(10, 3));
        System.out.println(solution.divide(7, -3));
        System.out.println(solution.divide(-2147483648, -1));
        System.out.println(solution.divide(-2147483648, 1));
        System.out.println(solution.divide(-1010369383, -2147483648));
        System.out.println(solution.divide(-2147483648, -1109186033));
    }
}
