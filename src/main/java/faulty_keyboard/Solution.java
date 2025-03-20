package faulty_keyboard;

public class Solution {
    public String finalString(String s) {
        char faultyCh = 'i';
        int rightIdx = 0;
        char[] chars = s.toCharArray();
        char[] result = new char[chars.length];
        for (int i = 0; i < chars.length; i++) {
            char currentCh = s.charAt(i);
            if (currentCh != faultyCh) {
                result[rightIdx] = currentCh;
                rightIdx++;
            } else {
                reverseChars(result, rightIdx - 1);
            }
        }

        return new String(result, 0, rightIdx);
    }

    private void reverseChars(char[] chars, int end) {
        int pointer = 0;
        for (int i = 0; i <= end - pointer; i++) {
            char leftChar = chars[i];
            char rightChar = chars[end - pointer];
            chars[i] = rightChar;
            chars[end - pointer] = leftChar;
            pointer++;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.finalString("string"));
        System.out.println(solution.finalString("poiinter"));
        System.out.println(solution.finalString("viwif"));
    }
}
