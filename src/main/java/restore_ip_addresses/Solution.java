package restore_ip_addresses;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        String acc = "";
        makeIpAddress(acc, s, 4, result);
        return result;
    }

    private void makeIpAddress(String acc, String tail, int stepsLeft, List<String> result) {
        if (tail.length() < stepsLeft || tail.length() > stepsLeft * 3) {
            return;
        }
        if (stepsLeft == 0 && tail.isEmpty()) {
            result.add(acc.replaceFirst("\\.", ""));
            return;
        }

        if (tail.charAt(0) == '0') {
            makeIpAddress(acc.concat(".0"), tail.substring(1), stepsLeft - 1, result);
            return;
        }

        makeIpAddress(acc.concat(".").concat(tail.substring(0, 1)), tail.substring(1), stepsLeft - 1, result);

        if (tail.length() >= 2) {
            makeIpAddress(acc.concat(".").concat(tail.substring(0, 2)), tail.substring(2), stepsLeft - 1, result);
        }

        if (tail.length() >= 3 && Integer.parseInt(tail.substring(0, 3)) <= 255) {
            makeIpAddress(acc.concat(".").concat(tail.substring(0, 3)), tail.substring(3), stepsLeft - 1, result);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.restoreIpAddresses("25525511135"));
        System.out.println(solution.restoreIpAddresses("1101"));
    }
}
