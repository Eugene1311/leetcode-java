package roman_to_integer;

import java.util.Map;

public class Solution {
    Map<Character, Integer> romanToArabic = Map.of(
            'I', 1,
            'V', 5,
            'X', 10,
            'L', 50,
            'C', 100,
            'D', 500,
            'M', 1000
    );

    public int romanToInt(String s) {
        if (s.length() == 1) {
            return romanToArabic.get(s.charAt(0));
        }

        int result = 0;

        for (int i = 0; i < s.length() - 1; i++) {
            char currentRomanLetter = s.charAt(i);
            char nextRomanLetter = s.charAt(i + 1);
            if (romanToArabic.get(currentRomanLetter) < romanToArabic.get(nextRomanLetter)) {
                result += romanToArabic.get(nextRomanLetter) - romanToArabic.get(currentRomanLetter);
                i++;
            } else {
                result += romanToArabic.get(currentRomanLetter);
            }
        }

        char currentRomanLetter = s.charAt(s.length() - 1);
        char previousRomanLetter = s.charAt(s.length() - 2);
        if (romanToArabic.get(currentRomanLetter) <= romanToArabic.get(previousRomanLetter)) {
            result += romanToArabic.get(currentRomanLetter);
        }

        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.romanToInt("LVIII"));
        System.out.println(solution.romanToInt("MCMXCIV"));
    }
}
