package reverse_string_ii;

public class Solution {
    public String reverseStr(String s, int k) {
        char[] letters = s.toCharArray();

        for (int i = 0; i < letters.length; i += 2 * k) {
            int limit = Math.min(k, s.length() - i);
            for (int j = 0; j < limit / 2; j++) {
                char letter = letters[i + j];
                int lastIndex = i + limit - 1 - j;
                letters[i + j] = letters[lastIndex];
                letters[lastIndex] = letter;
            }
        }

        return new String(letters);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.reverseStr("abcdefg", 2));
        System.out.println(solution.reverseStr("abcdefg", 3));
        System.out.println(solution.reverseStr("abcdefg", 8));
        System.out.println(solution.reverseStr("abcd", 4));
        System.out.println(solution.reverseStr("a", 4));
        System.out.println(solution.reverseStr("hyzqyljrnigxvdtneasepfahmtyhlohwxmkqcdfehybknvdmfrfvtbsovjbdhevlfxpdaovjgunjqlimjkfnqcqnajmebeddqsgl", 39));
        System.out.println(solution.reverseStr("imjkfnqcqnajmebeddqsgl", 39));
    }
}
