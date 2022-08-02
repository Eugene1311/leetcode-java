package count_all_valid_pickup_and_delivery_options;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    private final Map<Integer, Integer> sumsOfSequentialNumbers = new HashMap<>();
    public int countOrders(int n) {
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= getSumOfSequentialNumbers(2 * i - 1);
            result = (long) (result % (Math.pow(10, 9) + 7));
        }
        return (int) (result % (Math.pow(10, 9) + 7));
    }

    public int getSumOfSequentialNumbers(int x) {
        if (sumsOfSequentialNumbers.containsKey(x)) {
            return sumsOfSequentialNumbers.get(x);
        }
        if (x == 0 || x == 1) {
            return x;
        }

        int result = x + getSumOfSequentialNumbers(x - 1);
        sumsOfSequentialNumbers.put(x, result);
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println(solution.countOrders(2));
//        System.out.println(solution.countOrders(3)); // 1 * 6 * 15
//        System.out.println(solution.factorial(9));
//        System.out.println(solution.factorial(11));
//        System.out.println(solution.factorial(13));
        System.out.println(Math.pow(10, 9) + 7);
//        System.out.println((6 * 15 * 28 * 45 * 66 * 91 * 120) / (Math.pow(10, 9) + 7));
        System.out.println(solution.countOrders(18)); // 1 * 6 * 15 * 28 * 45 * 66 * 91 * 120
    }
}
