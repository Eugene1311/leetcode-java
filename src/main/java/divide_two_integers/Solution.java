package divide_two_integers;

public class Solution {
    public int divide(int dividend, int divisor) {
        boolean isNegative = (dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0);
        long longDividend = Math.abs((long) dividend);
        long longDivisor = Math.abs((long) divisor);
        long quotient = 0;
        long subtrahend = 0;

        for (int i = 31; i >= 0; i--) {
            subtrahend = (subtrahend << 1) + ((longDividend >> i) & 1);
            if (subtrahend < longDivisor) {
                quotient = quotient << 1;
            } else {
                quotient = (quotient << 1) + 1;
                subtrahend -= longDivisor;
            }
        }

        if (isNegative) {
            quotient = ~quotient + 1;
        }
        long result = quotient;
        result = Math.min(Integer.MAX_VALUE, result);
        result = Math.max(Integer.MIN_VALUE, result);
        return (int) result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.divide(10, 3));
        System.out.println(solution.divide(7, -3));
        System.out.println(solution.divide(-2147483648, -1));
        System.out.println(solution.divide(-1010369383, -2147483648));
        System.out.println(solution.divide(-2147483648, -1109186033));
    }
}
