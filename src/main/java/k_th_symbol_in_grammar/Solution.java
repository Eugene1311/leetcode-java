package k_th_symbol_in_grammar;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Solution {
    public int kthGrammar(int n, int k) {
        long nthRow = getRow(n);
        System.out.println(nthRow);
        return (int) (nthRow >> (n - k - 1)) % 2; // todo shift is incorrect
    }

    private long getRow(int n) {
        long row = 0;
        for (int i = 1; i < n; i++) {
            long nextRow = row;
            for (long j = (long) Math.pow(2, i - 1) - 1; j >= 0; j--) {
                if ((nextRow >> j) == 0) {
                    nextRow += Math.pow(2, j - 1);
                } else {
                    long left = nextRow >> j << j;
                    long right = nextRow - left;
                    left = (left << 2) + (long) Math.pow(2, j);
                    nextRow = left + right;
                }
            }
            row = nextRow;
        }
        return row;
    }

    private String getRow(int n, String row) {
        if (n == 1) {
            return row;
        }
        String nextRow = Arrays.stream(row.split(""))
                .map(letter -> {
                    if ("0".equals(letter)) {
                        return "01";
                    } else {
                        return "10";
                    }
                })
                .collect(Collectors.joining(""));
        return getRow(n - 1, nextRow);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println(solution.kthGrammar(1, 1));
//        System.out.println(solution.kthGrammar(2, 1));
        System.out.println(solution.kthGrammar(3, 3));
//        System.out.println(solution.kthGrammar(30, 434991989));
    }
}
