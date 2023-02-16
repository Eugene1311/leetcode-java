package minimize_xor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
    public int minimizeXor(int num1, int num2) {
        int numberOfBits = Arrays.stream(Integer.toBinaryString(num2).split(""))
                .filter(num -> num.equals("1"))
                .toList()
                .size();

        String num1Bits = Integer.toBinaryString(num1);
        Character[] result = new Character[num1Bits.length()];
        Arrays.fill(result, '0');
        for (int i = 0; i < num1Bits.length(); i++) {
            char bit = num1Bits.charAt(i);
            if (bit == '1' && numberOfBits > 0) {
                result[i] = '1';
                numberOfBits--;
            }
        }

        if (numberOfBits > 0) {
            for (int i = num1Bits.length() - 1; i >= 0 ; i--) {
                char bit = num1Bits.charAt(i);
                if (bit == '0' && numberOfBits > 0) {
                    result[i] = '1';
                    numberOfBits--;
                }
            }
        }

        Character[] addition = new Character[numberOfBits];
        Arrays.fill(addition, '1');
        List<Character> resultList = new ArrayList<>(addition.length + result.length);
        Collections.addAll(resultList, result);
        Collections.addAll(resultList, addition);

        return Integer.parseInt(resultList.stream().map(String::valueOf).collect(Collectors.joining("")), 2);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minimizeXor(3, 5));
        System.out.println(solution.minimizeXor(1, 12));
        System.out.println(solution.minimizeXor(65, 84));
    }
}
