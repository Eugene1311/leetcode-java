package remove_k_digits;

import java.util.Stack;

public class SolutionWithStack {
    public String removeKdigits(String num, int k) {
        String result = ""; // works faster without it, if using only stack
        Stack<Character> prevNums = new Stack<>();
        String numWithTailZero = num.concat("0");

        for (int i = 0; i < numWithTailZero.length() - 1; i++) {
            if (k == 0) {
                result = result.concat(numWithTailZero.substring(i));
                break;
            }
            char currentVal = numWithTailZero.charAt(i);
            int nextVal = numWithTailZero.charAt(i + 1);
            if (currentVal > nextVal) {
                k--;
                while (!prevNums.isEmpty() && prevNums.peek() > nextVal && k > 0) {
                    prevNums.pop();
                    result = result.substring(0, result.length() - 1);
                    k--;
                }
            } else {
                prevNums.push(currentVal);
                result = result.concat(String.valueOf(currentVal));
            }
        }

        result = result.replaceAll("(^0*|0$)", "");
        return result.isBlank() ? "0" : result;
    }

    public static void main(String[] args) {
        SolutionWithStack solution = new SolutionWithStack();
        System.out.println(solution.removeKdigits(
                "121",
                1
        ));
    }
}
